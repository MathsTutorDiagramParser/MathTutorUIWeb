package com.mathTutor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class TreeDiagramController {

    @Autowired
    ServletContext sct ;
    Logger logger = LoggerFactory.getLogger(TreeDiagramController.class);

    @RequestMapping(value = "/saveTreeDiagram" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public void storeInput (@RequestParam("answer") String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        logger.info("===================Start writing================");
        String decodedStr = java.net.URLDecoder.decode(inputStr, "UTF-8");
        String xmlString = decodedStr.substring(decodedStr.indexOf("=")+1);
        logger.info("===================Decoded the String============");

        byte[] data = xmlString.toString().getBytes();

        try{
            String path = "G:\\tomcat8\\apache-tomcat-8.0.44\\webapps\\MathsTutorUI\\WEB-INF\\Files\\treediagram\\answer"+System.currentTimeMillis()+".svg";
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        }catch (Exception e){
            logger.info(e.toString());
        }




    }

}
