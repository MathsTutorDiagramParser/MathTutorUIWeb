package com.mathTutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Madhavi Ruwandika on 8/9/2017.
 */

@Controller
public class mainController {

     @GetMapping("/numberLine")
     public String numberLineUI(Model model){
         return "NumberLine";
     }

    @GetMapping("/histogram")
    public String histogramUI(Model model){
        return "bargraph";
    }


    @GetMapping("/treeDiagram")
    public String treeDiagramUI(Model model){
        return "venn_tree";
    }

    @GetMapping("/TrignometricDiagram")
    public String trignoUI(Model model){
        return "Trigno";
    }





}
