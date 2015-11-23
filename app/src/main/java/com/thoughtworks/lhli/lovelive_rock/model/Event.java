package com.thoughtworks.lhli.lovelive_rock.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Event {
    @SerializedName("japanese_name")
    private String japaneseName;

    @SerializedName("romaji_name")
    private String romajiName;

    @SerializedName("english_name")
    private String englishName;

    @SerializedName("image")
    private String image;

    @SerializedName("english_image")
    private String englishImage;

    @SerializedName("beginning")
    private Date beginning;

    @SerializedName("end")
    private Date end;

    @SerializedName("english_beginning")
    private Date englishBeginning;

    @SerializedName("english_end")
    private Date englishEnd;

    @SerializedName("japan_current")
    private Boolean japanCurrent;

    @SerializedName("world_current")
    private Boolean worldCurrent;

    @SerializedName("cards")
    private int[] cards;

    @SerializedName("song")
    private String song;

    @SerializedName("japanese_t1_points")
    private Integer japaneseT1Points;

    @SerializedName("japanese_t1_rank")
    private Integer japaneseT1Rank;

    @SerializedName("japanese_t2_points")
    private Integer japaneseT2Points;

    @SerializedName("japanese_t2_rank")
    private Integer japaneseT2Rank;

    @SerializedName("english_t1_points")
    private Integer englishT1Points;

    @SerializedName("english_t1_rank")
    private Integer englishT1Rank;

    @SerializedName("english_t2_points")
    private Integer englishT2Points;

    @SerializedName("english_t2_rank")
    private Integer englishT2Rank;

    @SerializedName("note")
    private String note;

    public Event(String japaneseName, String romajiName, String englishName, String image,
                 String englishImage, Date beginning, Date end, Date englishBeginning,
                 Date englishEnd, Boolean japanCurrent, Boolean worldCurrent, int[] cards,
                 String song, Integer japaneseT1Points, Integer japaneseT1Rank,
                 Integer japaneseT2Points, Integer japaneseT2Rank, Integer englishT1Points,
                 Integer englishT1Rank, Integer englishT2Points, Integer englishT2Rank, String note) {
        this.japaneseName = japaneseName;
        this.romajiName = romajiName;
        this.englishName = englishName;
        this.image = image;
        this.englishImage = englishImage;
        this.beginning = beginning;
        this.end = end;
        this.englishBeginning = englishBeginning;
        this.englishEnd = englishEnd;
        this.japanCurrent = japanCurrent;
        this.worldCurrent = worldCurrent;
        this.cards = cards;
        this.song = song;
        this.japaneseT1Points = japaneseT1Points;
        this.japaneseT1Rank = japaneseT1Rank;
        this.japaneseT2Points = japaneseT2Points;
        this.japaneseT2Rank = japaneseT2Rank;
        this.englishT1Points = englishT1Points;
        this.englishT1Rank = englishT1Rank;
        this.englishT2Points = englishT2Points;
        this.englishT2Rank = englishT2Rank;
        this.note = note;
    }

    public Event() {
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getRomajiName() {
        return romajiName;
    }

    public void setRomajiName(String romajiName) {
        this.romajiName = romajiName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEnglishImage() {
        return englishImage;
    }

    public void setEnglishImage(String englishImage) {
        this.englishImage = englishImage;
    }

    public Date getBeginning() {
        return beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getEnglishBeginning() {
        return englishBeginning;
    }

    public void setEnglishBeginning(Date englishBeginning) {
        this.englishBeginning = englishBeginning;
    }

    public Date getEnglishEnd() {
        return englishEnd;
    }

    public void setEnglishEnd(Date englishEnd) {
        this.englishEnd = englishEnd;
    }

    public Boolean getJapanCurrent() {
        return japanCurrent;
    }

    public void setJapanCurrent(Boolean japanCurrent) {
        this.japanCurrent = japanCurrent;
    }

    public Boolean getWorldCurrent() {
        return worldCurrent;
    }

    public void setWorldCurrent(Boolean worldCurrent) {
        this.worldCurrent = worldCurrent;
    }

    public int[] getCards() {
        return cards;
    }

    public void setCards(int[] cards) {
        this.cards = cards;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Integer getJapaneseT1Points() {
        return japaneseT1Points;
    }

    public void setJapaneseT1Points(Integer japaneseT1Points) {
        this.japaneseT1Points = japaneseT1Points;
    }

    public Integer getJapaneseT1Rank() {
        return japaneseT1Rank;
    }

    public void setJapaneseT1Rank(Integer japaneseT1Rank) {
        this.japaneseT1Rank = japaneseT1Rank;
    }

    public Integer getJapaneseT2Points() {
        return japaneseT2Points;
    }

    public void setJapaneseT2Points(Integer japaneseT2Points) {
        this.japaneseT2Points = japaneseT2Points;
    }

    public Integer getJapaneseT2Rank() {
        return japaneseT2Rank;
    }

    public void setJapaneseT2Rank(Integer japaneseT2Rank) {
        this.japaneseT2Rank = japaneseT2Rank;
    }

    public Integer getEnglishT1Points() {
        return englishT1Points;
    }

    public void setEnglishT1Points(Integer englishT1Points) {
        this.englishT1Points = englishT1Points;
    }

    public Integer getEnglishT1Rank() {
        return englishT1Rank;
    }

    public void setEnglishT1Rank(Integer englishT1Rank) {
        this.englishT1Rank = englishT1Rank;
    }

    public Integer getEnglishT2Points() {
        return englishT2Points;
    }

    public void setEnglishT2Points(Integer englishT2Points) {
        this.englishT2Points = englishT2Points;
    }

    public Integer getEnglishT2Rank() {
        return englishT2Rank;
    }

    public void setEnglishT2Rank(Integer englishT2Rank) {
        this.englishT2Rank = englishT2Rank;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}