package com.mathTutor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by Madhavi Ruwandika on 8/9/2017.
 */

@Controller
public class NumberLineController {

    @Autowired
    ServletContext sct ;
    Logger logger = LoggerFactory.getLogger(NumberLineController.class);

    @RequestMapping(value = "/saveNumberLine" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView storeInput (@RequestParam("answer") String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        ModelAndView model = new ModelAndView("NumberLine");
        logger.info("===================Start writing================");
        String decodedStr = java.net.URLDecoder.decode(inputStr, "UTF-8");
        String xmlString = decodedStr.substring(decodedStr.indexOf("=")+1);
        logger.info("===================Decoded the String============");

        byte[] data = xmlString.toString().getBytes();

        try{
            String path = "\\var\\lib\\tomcat7\\webapps\\mathsTutor\\WEB-INF\\Files\\numberline\\answer"+System.currentTimeMillis()+".svg";
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            logger.info("===================File saved============");
            model.addObject("msg" , "Data Saved Successfully.");
        }catch (Exception e){
            logger.info(e.toString());
            model.addObject("msg" , "Failed to Save.");
        }

       return model;

    }


}
