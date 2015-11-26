package com.thoughtworks.lhli.lovelive_rock.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IdolModel implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("japanese_name")
    private String japaneseName;

    @SerializedName("main")
    private Boolean main;

    @SerializedName("age")
    private Integer age;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("astrological_sign")
    private String astrologicalSign;

    @SerializedName("blood")
    private String blood;

    @SerializedName("height")
    private Integer height;

    @SerializedName("measurements")
    private String measurements;

    @SerializedName("favorite_food")
    private String favoriteFood;

    @SerializedName("least_favorite_food")
    private String leastFavoriteFood;

    @SerializedName("hobbies")
    private String hobbies;

    @SerializedName("attribute")
    private String attribute;

    @SerializedName("year")
    private String year;

    @SerializedName("sub_unit")
    private String subUnit;

    @SerializedName("cv")
    private CvModel cvModel;

    @SerializedName("summary")
    private String summary;

    @SerializedName("website_url")
    private String websiteUrl;

    @SerializedName("wiki_url")
    private String wikiUrl;

    @SerializedName("wikia_url")
    private String wikiaUrl;

    @SerializedName("official_url")
    private String officialUrl;

    @SerializedName("chibi")
    private String chibi;

    @SerializedName("chibi_small")
    private String chibiSmall;

    public IdolModel(String name, String japaneseName, Boolean main, Integer age, String birthday,
                     String astrologicalSign, String blood, Integer height, String measurements,
                     String favoriteFood, String leastFavoriteFood, String hobbies, String attribute,
                     String year, String subUnit, CvModel cvModel, String summary,
                     String websiteUrl, String wikiUrl, String wikiaUrl, String officialUrl,
                     String chibi, String chibiSmall) {
        this.name = name;
        this.japaneseName = japaneseName;
        this.main = main;
        this.age = age;
        this.birthday = birthday;
        this.astrologicalSign = astrologicalSign;
        this.blood = blood;
        this.height = height;
        this.measurements = measurements;
        this.favoriteFood = favoriteFood;
        this.leastFavoriteFood = leastFavoriteFood;
        this.hobbies = hobbies;
        this.attribute = attribute;
        this.year = year;
        this.subUnit = subUnit;
        this.cvModel = cvModel;
        this.summary = summary;
        this.websiteUrl = websiteUrl;
        this.wikiUrl = wikiUrl;
        this.wikiaUrl = wikiaUrl;
        this.officialUrl = officialUrl;
        this.chibi = chibi;
        this.chibiSmall = chibiSmall;
    }

    public IdolModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAstrologicalSign() {
        return astrologicalSign;
    }

    public void setAstrologicalSign(String astrologicalSign) {
        this.astrologicalSign = astrologicalSign;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public String getLeastFavoriteFood() {
        return leastFavoriteFood;
    }

    public void setLeastFavoriteFood(String leastFavoriteFood) {
        this.leastFavoriteFood = leastFavoriteFood;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubUnit() {
        return subUnit;
    }

    public void setSubUnit(String subUnit) {
        this.subUnit = subUnit;
    }

    public CvModel getCvModel() {
        return cvModel;
    }

    public void setCvModel(CvModel cvModel) {
        this.cvModel = cvModel;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public String getWikiaUrl() {
        return wikiaUrl;
    }

    public void setWikiaUrl(String wikiaUrl) {
        this.wikiaUrl = wikiaUrl;
    }

    public String getOfficialUrl() {
        return officialUrl;
    }

    public void setOfficialUrl(String officialUrl) {
        this.officialUrl = officialUrl;
    }

    public String getChibi() {
        return chibi;
    }

    public void setChibi(String chibi) {
        this.chibi = chibi;
    }

    public String getChibiSmall() {
        return chibiSmall;
    }

    public void setChibiSmall(String chibiSmall) {
        this.chibiSmall = chibiSmall;
    }
}