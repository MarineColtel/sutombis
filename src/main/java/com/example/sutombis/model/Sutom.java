package com.example.sutombis.model;

public class Sutom {

    private long id;
    private String name;
    private String categorie;
    private String anonymizedWord;

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

    // Anonymize the daily word and let only the first letter visible
    public String anonymizeWord(String word) {
        Integer length = word.length();

        String anonymizedWord = String.format("%-" + length + "s", word.charAt(0)).replace(" ", " _");

        this.anonymizedWord = anonymizedWord;

        return this.anonymizedWord;
    }

}
