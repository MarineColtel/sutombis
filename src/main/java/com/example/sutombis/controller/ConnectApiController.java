package com.example.sutombis.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.sutombis.model.CatFact;
import com.example.sutombis.model.Sutom;
import com.example.sutombis.model.SutomInput;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ConnectApiController {

    // Connecting to Catfact ninja API
    @GetMapping("/catfact")
    public String getRandomCatFact(Model model) {
        String url = "https://catfact.ninja";
        RestTemplate restTemplate = new RestTemplate();
        
        CatFact response = restTemplate.getForObject(url + "/fact", CatFact.class);
        
        return response.getFact();
    }


    // Connecting to Trouve-mot api

    // Create new Sutom object and get the word of the day
    Sutom sutomOfTheDay = new Sutom();

    @GetMapping("/sutom")
    public String getRandomWord(Model model) {

        // Get the word of the day
        sutomOfTheDay.getDailyWord();
        sutomOfTheDay.anonymizeWord();

        // 
        model.addAttribute("hiddenWord", sutomOfTheDay.anonymizedWord);

        // Allow the user to enter a word
        model.addAttribute("SutomInput", new SutomInput());

        // Return the html page (with the name of the html file)
        return "get_sutom_word";

    }

    @PostMapping("/sutom")
    public String sutomSubmit(@ModelAttribute SutomInput SutomInput, Model model) {
        model.addAttribute("SutomInput", SutomInput);

        Boolean check = sutomOfTheDay.checkWord("proposedWord");

        if (check = false) {
            sutomOfTheDay.anonymizeWord();
        }

        return "post_sutom_word";
    }
    
}
