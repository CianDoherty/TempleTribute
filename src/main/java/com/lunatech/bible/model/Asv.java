package com.lunatech.bible.model;
import javax.persistence.*;

@Entity
@Table( name = "t_asv" )
public class Asv {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;
    @Column(name="b")
    private Integer b;
    @Column(name="c")
    private Integer c;
    @Column(name="v")
    private Integer v;
    @Column(name="t")
    private Integer t;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }
}
