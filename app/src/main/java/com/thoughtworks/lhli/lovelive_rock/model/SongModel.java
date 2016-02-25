package com.thoughtworks.lhli.lovelive_rock.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SongModel implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("romaji_name")
    private String romajiName;

    @SerializedName("translated_name")
    private String translatedName;

    @SerializedName("attribute")
    private String attribute;

    @SerializedName("BPM")
    private String BPM;

    @SerializedName("time")
    private String time;

    @SerializedName("event")
    private String event;

    @SerializedName("rank")
    private String rank;

    @SerializedName("daily_rotation")
    private String dailyRotation;

    @SerializedName("daily_rotation_position")
    private String dailyRotationPosition;

    @SerializedName("image")
    private String image;

    @SerializedName("easy_difficulty")
    private String easyDifficulty;

    @SerializedName("easy_notes")
    private String easyNotes;

    @SerializedName("normal_difficulty")
    private String normalDifficulty;

    @SerializedName("normal_notes")
    private String normalNotes;

    @SerializedName("hard_difficulty")
    private String hardDifficulty;

    @SerializedName("hard_notes")
    private String hardNotes;

    @SerializedName("expert_difficulty")
    private String expertDifficulty;

    @SerializedName("expert_random_difficulty")
    private String expertRandomDifficulty;

    @SerializedName("expert_notes")
    private String expertNotes;

    @SerializedName("available")
    private String available;

    @SerializedName("itunes_id")
    private String itunesId;

    public SongModel(String name, String romajiName, String translatedName, String attribute,
                     String BPM, String time, String event, String rank, String dailyRotation,
                     String dailyRotationPosition, String image, String easyDifficulty,
                     String easyNotes, String normalDifficulty, String normalNotes,
                     String hardDifficulty, String hardNotes, String expertDifficulty,
                     String expertRandomDifficulty, String expertNotes, String available,
                     String itunesId) {
        this.name = name;
        this.romajiName = romajiName;
        this.translatedName = translatedName;
        this.attribute = attribute;
        this.BPM = BPM;
        this.time = time;
        this.event = event;
        this.rank = rank;
        this.dailyRotation = dailyRotation;
        this.dailyRotationPosition = dailyRotationPosition;
        this.image = image;
        this.easyDifficulty = easyDifficulty;
        this.easyNotes = easyNotes;
        this.normalDifficulty = normalDifficulty;
        this.normalNotes = normalNotes;
        this.hardDifficulty = hardDifficulty;
        this.hardNotes = hardNotes;
        this.expertDifficulty = expertDifficulty;
        this.expertRandomDifficulty = expertRandomDifficulty;
        this.expertNotes = expertNotes;
        this.available = available;
        this.itunesId = itunesId;
    }

    public SongModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRomajiName() {
        return romajiName;
    }

    public void setRomajiName(String romajiName) {
        this.romajiName = romajiName;
    }

    public String getTranslatedName() {
        return translatedName;
    }

    public void setTranslatedName(String translatedName) {
        this.translatedName = translatedName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getBPM() {
        return BPM;
    }

    public void setBPM(String BPM) {
        this.BPM = BPM;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDailyRotation() {
        return dailyRotation;
    }

    public void setDailyRotation(String dailyRotation) {
        this.dailyRotation = dailyRotation;
    }

    public String getDailyRotationPosition() {
        return dailyRotationPosition;
    }

    public void setDailyRotationPosition(String dailyRotationPosition) {
        this.dailyRotationPosition = dailyRotationPosition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEasyDifficulty() {
        return easyDifficulty;
    }

    public void setEasyDifficulty(String easyDifficulty) {
        this.easyDifficulty = easyDifficulty;
    }

    public String getEasyNotes() {
        return easyNotes;
    }

    public void setEasyNotes(String easyNotes) {
        this.easyNotes = easyNotes;
    }

    public String getNormalDifficulty() {
        return normalDifficulty;
    }

    public void setNormalDifficulty(String normalDifficulty) {
        this.normalDifficulty = normalDifficulty;
    }

    public String getNormalNotes() {
        return normalNotes;
    }

    public void setNormalNotes(String normalNotes) {
        this.normalNotes = normalNotes;
    }

    public String getHardDifficulty() {
        return hardDifficulty;
    }

    public void setHardDifficulty(String hardDifficulty) {
        this.hardDifficulty = hardDifficulty;
    }

    public String getHardNotes() {
        return hardNotes;
    }

    public void setHardNotes(String hardNotes) {
        this.hardNotes = hardNotes;
    }

    public String getExpertDifficulty() {
        return expertDifficulty;
    }

    public void setExpertDifficulty(String expertDifficulty) {
        this.expertDifficulty = expertDifficulty;
    }

    public String getExpertRandomDifficulty() {
        return expertRandomDifficulty;
    }

    public void setExpertRandomDifficulty(String expertRandomDifficulty) {
        this.expertRandomDifficulty = expertRandomDifficulty;
    }

    public String getExpertNotes() {
        return expertNotes;
    }

    public void setExpertNotes(String expertNotes) {
        this.expertNotes = expertNotes;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getItunesId() {
        return itunesId;
    }

    public void setItunesId(String itunesId) {
        this.itunesId = itunesId;
    }
}
