package com.thoughtworks.lhli.lovelive_rock.model.point;

import com.google.gson.annotations.SerializedName;

public class Rank {
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

    public int getPoints(String combo) {
        switch (combo) {
            case "S":
                return S;
            case "A":
                return A;
            case "B":
                return B;
            case "C":
                return C;
            case "-":
                return D;
        }
        return 0;
    }
}
