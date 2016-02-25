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
    private Short BPM;

    @SerializedName("time")
    private Short time;

    @SerializedName("event")
    private EventModel eventModel;

    @SerializedName("rank")
    private Short rank;

    @SerializedName("daily_rotation")
    private String dailyRotation;

    @SerializedName("daily_rotation_position")
    private Short dailyRotationPosition;

    @SerializedName("image")
    private String image;

    @SerializedName("easy_difficulty")
    private Short easyDifficulty;

    @SerializedName("easy_notes")
    private Short easyNotes;

    @SerializedName("normal_difficulty")
    private Short normalDifficulty;

    @SerializedName("normal_notes")
    private Short normalNotes;

    @SerializedName("hard_difficulty")
    private Short hardDifficulty;

    @SerializedName("hard_notes")
    private Short hardNotes;

    @SerializedName("expert_difficulty")
    private Short expertDifficulty;

    @SerializedName("expert_random_difficulty")
    private Short expertRandomDifficulty;

    @SerializedName("expert_notes")
    private Short expertNotes;

    @SerializedName("available")
    private Boolean available;

    @SerializedName("itunes_id")
    private Long itunesId;

    public SongModel(String name, String romajiName, String translatedName, String attribute,
                     Short BPM, Short time, EventModel eventModel, Short rank, String dailyRotation,
                     Short dailyRotationPosition, String image, Short easyDifficulty, Short easyNotes,
                     Short normalDifficulty, Short normalNotes, Short hardDifficulty, Short hardNotes,
                     Short expertDifficulty, Short expertRandomDifficulty, Short expertNotes,
                     Boolean available, Long itunesId) {
        this.name = name;
        this.romajiName = romajiName;
        this.translatedName = translatedName;
        this.attribute = attribute;
        this.BPM = BPM;
        this.time = time;
        this.eventModel = eventModel;
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

    public Short getBPM() {
        return BPM;
    }

    public void setBPM(Short BPM) {
        this.BPM = BPM;
    }

    public Short getTime() {
        return time;
    }

    public void setTime(Short time) {
        this.time = time;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public Short getRank() {
        return rank;
    }

    public void setRank(Short rank) {
        this.rank = rank;
    }

    public String getDailyRotation() {
        return dailyRotation;
    }

    public void setDailyRotation(String dailyRotation) {
        this.dailyRotation = dailyRotation;
    }

    public Short getDailyRotationPosition() {
        return dailyRotationPosition;
    }

    public void setDailyRotationPosition(Short dailyRotationPosition) {
        this.dailyRotationPosition = dailyRotationPosition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Short getEasyDifficulty() {
        return easyDifficulty;
    }

    public void setEasyDifficulty(Short easyDifficulty) {
        this.easyDifficulty = easyDifficulty;
    }

    public Short getEasyNotes() {
        return easyNotes;
    }

    public void setEasyNotes(Short easyNotes) {
        this.easyNotes = easyNotes;
    }

    public Short getNormalDifficulty() {
        return normalDifficulty;
    }

    public void setNormalDifficulty(Short normalDifficulty) {
        this.normalDifficulty = normalDifficulty;
    }

    public Short getNormalNotes() {
        return normalNotes;
    }

    public void setNormalNotes(Short normalNotes) {
        this.normalNotes = normalNotes;
    }

    public Short getHardDifficulty() {
        return hardDifficulty;
    }

    public void setHardDifficulty(Short hardDifficulty) {
        this.hardDifficulty = hardDifficulty;
    }

    public Short getHardNotes() {
        return hardNotes;
    }

    public void setHardNotes(Short hardNotes) {
        this.hardNotes = hardNotes;
    }

    public Short getExpertDifficulty() {
        return expertDifficulty;
    }

    public void setExpertDifficulty(Short expertDifficulty) {
        this.expertDifficulty = expertDifficulty;
    }

    public Short getExpertRandomDifficulty() {
        return expertRandomDifficulty;
    }

    public void setExpertRandomDifficulty(Short expertRandomDifficulty) {
        this.expertRandomDifficulty = expertRandomDifficulty;
    }

    public Short getExpertNotes() {
        return expertNotes;
    }

    public void setExpertNotes(Short expertNotes) {
        this.expertNotes = expertNotes;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getItunesId() {
        return itunesId;
    }

    public void setItunesId(Long itunesId) {
        this.itunesId = itunesId;
    }
}
