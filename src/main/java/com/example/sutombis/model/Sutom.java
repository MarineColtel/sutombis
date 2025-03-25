package com.example.sutombis.model;

import org.springframework.web.client.RestTemplate;

public class Sutom {

    private long id;
    public String name;
    private String categorie;
    public String anonymizedWord;

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

    public String getCategorie() {
        return categorie;
    }

    public String getAnonymizedWord() {
        return anonymizedWord;
    }

    public void setAnonymizedWord(String anonymizedWord) {
        this.anonymizedWord = anonymizedWord;
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
    }

    // Anonymize the daily word and let only the first letter visible
    public void anonymizeWord(String proposedWord) {
        Integer length = this.name.length();

        if (proposedWord.isEmpty()) {
            this.anonymizedWord = String.format("%-" + length + "s", this.name.charAt(0)).replace(" ", " _");
        }

        for (Integer i=0; i <= length; i++) {
            if (this.name.charAt(i) == proposedWord.charAt(i)) {
                this.anonymizedWord += proposedWord.charAt(i);
            } else {
                this.anonymizedWord += " _";
            }
        }
    }

    // Check if the word provide by the user is equal to the world of the day
    public Boolean checkWord(String proposedWord) {
        // Logic to check each letter
        Integer length = this.name.length();

        for (Integer i=0; i < length; i++) {
            if (this.name.charAt(i) != proposedWord.charAt(i)) {
                return false;
            }
        }

        return true;
        
    }

}
