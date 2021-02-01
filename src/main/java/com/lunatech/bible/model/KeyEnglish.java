package com.lunatech.bible.model;

import javax.persistence.*;
@NamedNativeQuery(
        name="findAllQry",
        query = "SELECT * FROM key_english",
        resultClass = KeyEnglish.class)
@Entity
@Table( name = "key_english" )
public class KeyEnglish {
    @Id
    @GeneratedValue
    // Book #
    @Column(name="b")
    private Integer b;
    // Book Name
    @Column(name="n")
    private String n;
    // Testament (OT or NT)
    @Column(name="t")
    private String t;
    // Genre ID
    @Column(name="g")
    private Integer g;

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }
}
