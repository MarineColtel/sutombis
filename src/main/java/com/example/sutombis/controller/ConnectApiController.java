package com.example.sutombis.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.sutombis.model.CatFact;
import com.example.sutombis.model.Sutom;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ConnectApiController {

    // Connecting to Catfact ninja API
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


    // Connecting to WordsAPI api

    @GetMapping("/get_sutom_word")
    public String getRandomWord(Model model) {
        String url = "https://trouve-mot.fr/api";
        RestTemplate restTemplate = new RestTemplate();
        
        Sutom response = restTemplate.getForObject(url + "/daily", Sutom.class);
        
        return response.getName();
    }



    //  @GetMapping("/get_sutom_word")
    // public String getRandomWord(Model model) throws IOException {

    //     String url = "https://wordsapiv1.p.rapidapi.com/words/lovely/synonyms";

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add("x-rapidapi-host", "wordsapiv1.p.rapidapi.com");
    //     headers.add("x-api-key", "66415cdb44msh4c25cde97367049p176f34jsnd9aca8060cfd");
        
    //     HttpEntity<Object> entity = new HttpEntity<Object>(headers);
    //     RestTemplate restTemplate = new RestTemplate();
    //     ResponseEntity<Sutom> response = restTemplate.exchange(url, HttpMethod.GET, entity, Sutom.class);
        
    //     return response.toString();
    // }
    
    
}
