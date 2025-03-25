package com.example.sutombis.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.sutombis.model.CatFact;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ConnectApiController {

    @GetMapping("/catfact")
    public String getRandomCatFact(Model model) {
        String url = "https://catfact.ninja";
        RestTemplate restTemplate = new RestTemplate();
        
        CatFact response = restTemplate.getForObject(url + "/fact", CatFact.class);
        
        return response.getFact();
    }
    
    // @PostMapping("/catfact")
    // public String postRandomCatFact(@ModelAttribute CatFact catFact, Model model) {
    //     model.addAttribute("catfact", catFact);
    //     return "CatFact";
    // }
     
}
