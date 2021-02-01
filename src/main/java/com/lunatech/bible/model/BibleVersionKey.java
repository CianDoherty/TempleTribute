package com.lunatech.bible.model;

import javax.persistence.*;

@Entity
@Table( name = "bible_version_key" )
public class BibleVersionKey {

    @Id
    @GeneratedValue
    @Column(name="id")
    private String id;

    // Corresponding Bible version table name.
    @Column(name="table")
    private String table;

    // Bible version abbreviation.
    @Column(name="abbreviation")
    private String abbreviation;

    //Language translation.
    @Column(name="language")
    private String language;

    // Version name.
    @Column(name="version")
    private String version;

    // Extra info.
    @Column(name="info_text")
    private String infoText;

    // Wiki info or similar URL
    @Column(name="info_url")
    private String infoUrl;

    // Who published.
    @Column(name="publisher")
    private String publisher;

    // Copyright
    @Column(name="copyright")
    private String copyright;

    // More info on copyright.
    @Column(name="copyright_info")
    private String copyrightInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCopyrightInfo() {
        return copyrightInfo;
    }

    public void setCopyrightInfo(String copyrightInfo) {
        this.copyrightInfo = copyrightInfo;
    }

}
