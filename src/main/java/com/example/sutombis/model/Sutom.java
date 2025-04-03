package com.example.sutombis.model;

import java.util.Set;

import org.springframework.web.client.RestTemplate;

public class Sutom {

    private long id;
    public String name;
    public Integer length;
    public String categorie;
    public String hiddenWord;

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

    // Call trouve-mot api
    public void getDailyWord() {
        String url = "https://trouve-mot.fr/api";
        RestTemplate restTemplate = new RestTemplate();
        
        // Call the api
        Sutom response = restTemplate.getForObject(url + "/daily", Sutom.class);
        
        // Fetch only the name in the api response
        this.name = response.getName();
        this.length = this.name.length();
    }

    // Hide the daily word and let only the first letter visible
    public void hideWord() {;
        this.hiddenWord = String.format("%-" + this.length + "s", this.name.charAt(0)).replace(" ", "_");
    }

    // Hide the daily word's letters that are not found yet by the user
    public void hideLettersNotFound(String proposedWord) {
        // si charAt() != " _" alors on garde la lettre
        // this.hiddenWord = ""; // plus besoin de ça

        Set<Character> listOfLetters = Set.of();

        for (Integer i=0; i < this.length ; i++) {
            Character hiddenLetter = this.hiddenWord.charAt(i);
            String stringLetter = hiddenLetter.toString();
            if (this.name.charAt(i) == proposedWord.charAt(i) && stringLetter == "_") {
                hiddenLetter = proposedWord.charAt(i);
                listOfLetters.add(hiddenLetter);
            }
        }
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
