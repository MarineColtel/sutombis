package com.example.sutombis.model;

import java.util.Arrays;

import org.springframework.web.client.RestTemplate;

public class Sutom {

    private long id;
    public String name;
    public Integer length;
    public String categorie;
    public String hiddenWord;
    public Boolean[] goodLetters;

    public long getId() {
        return id;
      }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Boolean[] getGoodLetters() {
        return goodLetters;
    }

    public void setGoodLetters(Boolean[] goodLetters) {
        this.goodLetters = goodLetters;
    }

    // Call trouve-mot api
    public void getDailyWord() {
        String url = "https://trouve-mot.fr/api";
        RestTemplate restTemplate = new RestTemplate();
        
        // Call the api
        Sutom response = restTemplate.getForObject(url + "/daily", Sutom.class);
        
        // Fetch only the name in the api response
        this.name = response.getName();
        this.length = this.name.length();

        // Initialize goodLetters table
        this.goodLetters = new Boolean[this.name.length()];
        Arrays.fill(this.goodLetters, false);
        this.goodLetters[0] = true;
    }

    // Hide the daily word and let only the first letter visible
    public void hideWord() {;
        this.hiddenWord = String.format("%-" + this.length + "s", this.name.charAt(0)).replace(" ", "_");
    }

    // Check which proposedWord's letters are good
    public void hideLettersNotFound(String proposedWord) {

        Boolean[] table = this.goodLetters;

        for (Integer i=0; i < this.length ; i++) {
            
            if (proposedWord.charAt(i) == this.name.charAt(i)) {
                table[i] = true;
            }
        }

        this.goodLetters = table;
        newHiddenWord(proposedWord);
    }

    // Update hiddenWord
    private void newHiddenWord(String proposedWord) {

        String newHiddenWord = "";

        for (Integer i=0; i < this.length ; i++) {

            if (this.goodLetters[i]) {
                newHiddenWord += this.name.charAt(i);
            } else {
                newHiddenWord += "_";
            }
        }
        this.hiddenWord = newHiddenWord;
    }

    // Check if the word provide by the user is equal to the world of the day
    public Boolean checkWord(String proposedWord) {
        // Logic to check each letter

        for (Integer i=0; i < this.length; i++) {
            if (this.name.charAt(i) != proposedWord.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
