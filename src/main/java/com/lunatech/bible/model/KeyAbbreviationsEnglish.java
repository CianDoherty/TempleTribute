package com.lunatech.bible.model;

import javax.persistence.*;

@Entity
@Table( name = "key_abbreviations_english" )
public class KeyAbbreviationsEnglish {
    @Id
    @GeneratedValue
    // Abbreviation ID
    @Column(name="id")
    private Integer id;
    // Abbreviation
    @Column(name="a")
    private String a;
    // Book ID this is refers to
    @Column(name="b")
    private Integer b;
    // If this is the desired abbreviation
    @Column(name="p")
    private Integer p;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }
}
