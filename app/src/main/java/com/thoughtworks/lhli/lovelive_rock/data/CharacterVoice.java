package com.thoughtworks.lhli.lovelive_rock.data;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CHARACTER_VOICE".
 */
public class CharacterVoice implements java.io.Serializable {

    private Long id;
    private String url;
    private String twitter;
    private String nickname;
    private String name;
    private String instagram;

    public CharacterVoice() {
    }

    public CharacterVoice(Long id) {
        this.id = id;
    }

    public CharacterVoice(Long id, String url, String twitter, String nickname, String name, String instagram) {
        this.id = id;
        this.url = url;
        this.twitter = twitter;
        this.nickname = nickname;
        this.name = name;
        this.instagram = instagram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

}
