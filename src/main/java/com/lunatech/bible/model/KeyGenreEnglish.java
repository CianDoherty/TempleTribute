package com.lunatech.bible.model;

import javax.persistence.*;

@Entity
@Table( name = "key_genre_english" )
public class KeyGenreEnglish {
    @Id
    @GeneratedValue
    // Genre ID
    @Column(name="g")
    private Integer g;
    // Genre name
    @Column(name="n")
    private String n;

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
}
