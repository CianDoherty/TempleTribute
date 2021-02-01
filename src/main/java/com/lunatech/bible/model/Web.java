package com.lunatech.bible.model;
import javax.persistence.*;


@Table( name = "t_web" )
public class Web {
    @Id
    @GeneratedValue
    @Column(name="id")
    private String id;
    @Column(name="table")
    private String table;
}
