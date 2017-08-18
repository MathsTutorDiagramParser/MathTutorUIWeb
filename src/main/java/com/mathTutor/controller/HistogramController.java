package com.mathTutor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Madhavi Ruwandika on 8/9/2017.
 */
public class HistogramController {

    @Autowired
    ServletContext sct ;
    Logger logger = LoggerFactory.getLogger(HistogramController.class);


    @RequestMapping(value = "/saveHistogram" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView storeInput (@RequestParam("answer") String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        ModelAndView model = new ModelAndView("venn_tree");
        logger.info("===================Start writing================");
        String decodedStr = java.net.URLDecoder.decode(inputStr, "UTF-8");
        String xmlString = decodedStr.substring(decodedStr.indexOf("=")+1);
        logger.info("===================Decoded the String============");

        byte[] data = xmlString.toString().getBytes();

        try{
            String path = "\\var\\lib\\tomcat7\\webapps\\mathsTutor\\WEB-INF\\Files\\histogram\\answer"+System.currentTimeMillis()+".svg";
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            model.addObject("msg" , "Data Saved Successfully.");
        }catch (Exception e){
            logger.info(e.toString());
            model.addObject("msg" , "Failed to Save.");
        }
        return model;
    }


}
