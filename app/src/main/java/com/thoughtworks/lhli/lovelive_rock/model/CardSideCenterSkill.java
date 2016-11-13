package com.thoughtworks.lhli.lovelive_rock.model;

import com.google.gson.annotations.SerializedName;

public class CardSideCenterSkill {
    @SerializedName("id")
    private Integer id;

    @SerializedName("skill_id")
    private Integer skillId;

    @SerializedName("skill")
    private String skill;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
