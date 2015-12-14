package com.thoughtworks.lhli.lovelive_rock.model;

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
}

class Expert {
    @SerializedName("S")
    private Rank S;

    @SerializedName("A")
    private Rank A;

    @SerializedName("B")
    private Rank B;

    @SerializedName("C")
    private Rank C;

    @SerializedName("D")
    private Rank D;

    public Rank getS() {
        return S;
    }

    public void setS(Rank s) {
        this.S = s;
    }

    public Rank getA() {
        return A;
    }

    public void setA(Rank a) {
        this.A = a;
    }

    public Rank getB() {
        return B;
    }

    public void setB(Rank b) {
        this.B = b;
    }

    public Rank getC() {
        return C;
    }

    public void setC(Rank c) {
        this.C = c;
    }

    public Rank getD() {
        return D;
    }

    public void setD(Rank d) {
        this.D = d;
    }
}

class Hard {
    @SerializedName("S")
    private Rank S;

    @SerializedName("A")
    private Rank A;

    @SerializedName("B")
    private Rank B;

    @SerializedName("C")
    private Rank C;

    @SerializedName("D")
    private Rank D;

    public Rank getD() {
        return D;
    }

    public void setD(Rank d) {
        this.D = d;
    }

    public Rank getS() {
        return S;
    }

    public void setS(Rank s) {
        this.S = s;
    }

    public Rank getA() {
        return A;
    }

    public void setA(Rank a) {
        this.A = a;
    }

    public Rank getB() {
        return B;
    }

    public void setB(Rank b) {
        this.B = b;
    }

    public Rank getC() {
        return C;
    }

    public void setC(Rank c) {
        this.C = c;
    }
}

class Normal {
    @SerializedName("S")
    private Rank S;

    @SerializedName("A")
    private Rank A;

    @SerializedName("B")
    private Rank B;

    @SerializedName("C")
    private Rank C;

    @SerializedName("D")
    private Rank D;

    public Rank getS() {
        return S;
    }

    public void setS(Rank s) {
        S = s;
    }

    public Rank getA() {
        return A;
    }

    public void setA(Rank a) {
        A = a;
    }

    public Rank getB() {
        return B;
    }

    public void setB(Rank b) {
        B = b;
    }

    public Rank getC() {
        return C;
    }

    public void setC(Rank c) {
        C = c;
    }

    public Rank getD() {
        return D;
    }

    public void setD(Rank d) {
        D = d;
    }
}

class Easy {
    @SerializedName("S")
    private Rank S;

    @SerializedName("A")
    private Rank A;

    @SerializedName("B")
    private Rank B;

    @SerializedName("C")
    private Rank C;

    @SerializedName("D")
    private Rank D;

    public Rank getS() {
        return S;
    }

    public void setS(Rank s) {
        S = s;
    }

    public Rank getA() {
        return A;
    }

    public void setA(Rank a) {
        A = a;
    }

    public Rank getB() {
        return B;
    }

    public void setB(Rank b) {
        B = b;
    }

    public Rank getC() {
        return C;
    }

    public void setC(Rank c) {
        C = c;
    }

    public Rank getD() {
        return D;
    }

    public void setD(Rank d) {
        D = d;
    }
}

class Rank {
    @SerializedName("S")
    private Integer S;

    @SerializedName("A")
    private Integer A;

    @SerializedName("B")
    private Integer B;

    @SerializedName("C")
    private Integer C;

    @SerializedName("D")
    private Integer D;

    public Integer getS() {
        return S;
    }

    public void setS(Integer s) {
        S = s;
    }

    public Integer getA() {
        return A;
    }

    public void setA(Integer a) {
        A = a;
    }

    public Integer getB() {
        return B;
    }

    public void setB(Integer b) {
        B = b;
    }

    public Integer getC() {
        return C;
    }

    public void setC(Integer c) {
        C = c;
    }

    public Integer getD() {
        return D;
    }

    public void setD(Integer d) {
        D = d;
    }
}
