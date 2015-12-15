package com.thoughtworks.lhli.lovelive_rock.model.point;

import com.google.gson.annotations.SerializedName;

public class Pt {

    @SerializedName("Expert")
    private Expert expert;

    @SerializedName("Hard")
    private Hard hard;

    @SerializedName("Normal")
    private Normal normal;

    @SerializedName("Easy")
    private Easy easy;

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public Hard getHard() {
        return hard;
    }

    public void setHard(Hard hard) {
        this.hard = hard;
    }

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    public Easy getEasy() {
        return easy;
    }

    public void setEasy(Easy easy) {
        this.easy = easy;
    }

    public int getPoints(String difficulty, String rank, String combo) {
        switch (difficulty) {
            case "Expert":
                return expert.getPoints(rank, combo);
            case "Hard":
                return hard.getPoints(rank, combo);
            case "Normal":
                return normal.getPoints(rank, combo);
            case "Easy":
                return easy.getPoints(rank, combo);
        }
        return 0;
    }
}

