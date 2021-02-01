package com.lunatech.bible.model;
import javax.persistence.*;
// DAILY QUERIES START
@NamedNativeQuery(
        name="dailyVerseQry",
        query = "SELECT id, b, c, v, t FROM t_kjv WHERE (b = :bookId) AND (c = :chapter)  AND (v BETWEEN :verseStart AND :verseEnd)",
        resultClass = Kjv.class)
@NamedNativeQuery(
        name="randomVerseQry",
        query = "select * from t_kjv order by rand()",
        resultClass = Kjv.class)
@NamedNativeQuery(
        name="singleVerseQry",
        query = "select * from t_kjv WHERE (b = :bookId) AND (c = :chapter)  AND (v = :verse)",
        resultClass = Kjv.class)
@NamedNativeQuery(
        name="betweenVerse1Qry",
        query = "SELECT * FROM t_kjv WHERE (b = :bookId) " +
                " AND c = :chapterStart "+
                " AND (v BETWEEN :verseStart AND (Select MAX(v) from Kjv where b = :bookId AND c = :chapterStart))",
        resultClass = Kjv.class)
@NamedNativeQuery(
        name="betweenVerse2Qry",
        query = " SELECT id, t FROM t_kjv WHERE (b = :bookId) "+
                " AND c = :chapterEnd "+
                " AND (v BETWEEN (Select MIN(v) from Kjv where b = :bookId AND c = :chapterEnd) AND :verseEnd)",
        resultClass = Kjv.class)
// DAILY QUERIES END
@Entity
@Table( name = "t_kjv" )
public class Kjv {
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
    private String t;

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

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

}
