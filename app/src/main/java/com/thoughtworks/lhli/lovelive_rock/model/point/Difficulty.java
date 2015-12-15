package com.thoughtworks.lhli.lovelive_rock.model.point;

import com.google.gson.annotations.SerializedName;

public class Difficulty {
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

    public int getPoints(String rank, String combo) {
        switch (rank) {
            case "S":
                return S.getPoints(combo);
            case "A":
                return A.getPoints(combo);
            case "B":
                return B.getPoints(combo);
            case "C":
                return C.getPoints(combo);
            case "-":
                return D.getPoints(combo);
        }
        return 0;
    }
}
