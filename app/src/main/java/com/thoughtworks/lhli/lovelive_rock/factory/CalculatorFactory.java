package com.thoughtworks.lhli.lovelive_rock.factory;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.text.DecimalFormat;
import java.util.HashMap;

public class CalculatorFactory {

    private long objectivePoints;
    private long currentPoints;
    private long currentRank;
    private long songAmount;
    private String difficulty;
    private long wastedLpEveryDay;
    private Boolean pointAddition;
    private Boolean experienceAddition;
    private double songRankRatio;
    private double comboRankRatio;
    private long currentLp;
    private long currentExperience;
    private long eventEndDay;
    private long eventEndHour;
    private double eventLastTime;
    private String eventEndTime;

    public CalculatorFactory() {
        this.eventEndTime = !LoveLiveApp.getInstance().getLatestEventEnd().equals("") ?
                LoveLiveApp.getInstance().getLatestEventEnd() : null;
    }

    public CalculatorFactory(String objectivePoints, String currentPoints, String currentRank,
                             String songAmount, String difficulty, String wastedLpEveryDay,
                             Boolean pointAddition, Boolean experienceAddition, String songRankRatio,
                             String comboRankRatio, String currentLp, String currentExperience,
                             String eventEndDay, String eventEndHour, String eventLastTime) {
        this.objectivePoints = parseLongField(objectivePoints);
        this.currentPoints = parseLongField(currentPoints);
        this.currentRank = parseLongField(currentRank);
        this.songAmount = parseLongField(songAmount);
        this.difficulty = difficulty;
        this.wastedLpEveryDay = parseLongField(wastedLpEveryDay);
        this.pointAddition = pointAddition;
        this.experienceAddition = experienceAddition;
        this.songRankRatio = parseDoubleField(songRankRatio);
        this.comboRankRatio = parseDoubleField(comboRankRatio);
        this.currentLp = parseLongField(currentLp);
        this.currentExperience = parseLongField(currentExperience);
        this.eventEndDay = parseLongField(eventEndDay);
        this.eventEndHour = parseLongField(eventEndHour);
        this.eventLastTime = parseDoubleField(eventLastTime);
    }

    private double parseDoubleField(String value) {
        return value != null && !value.equals("") ? Double.parseDouble(value) : 0.0;
    }

    private long parseLongField(String value) {
        return value != null && !value.equals("") ? Long.parseLong(value) : 0L;
    }

    public String getEventEndDay() {
        return eventEndTime != null ? String.format("%s", DateTime.parse(eventEndTime).getDayOfMonth()) : "0";
    }

    public String getEventEndHour() {
        return eventEndTime != null ? String.format("%s", DateTime.parse(eventEndTime).getHourOfDay() - 1) : "0";
    }

    public String getEventLastTime() {
        if (eventEndTime != null) {
            Duration duration = new Duration(new DateTime(), DateTime.parse(eventEndTime));
            double lastTime = duration.getStandardHours() + duration.getStandardMinutes() % 60 / 60.0;
            return String.format("%s", new DecimalFormat("0.0").format(lastTime));
        } else {
            return "0";
        }
    }

    public long getPointsWithinOncePlay() {
        double points = getMfBasicPoints();
        points = points * songRankRatio * comboRankRatio;
        return pointAddition ? Math.round(points * 1.1) : Math.round(points);
    }

    public long getTimesNeedToPlay() {
        return 0L;
    }

    protected long getRankUpExp() {
        if (currentRank < 1L) {
            return 0L;
        }
        return currentRank >= 100L ?
                Math.round(34.45 * currentRank - 551) :
                Math.round((34.45 * currentRank - 551) / 2);
    }

    protected long getBiggestLP() {
        if (currentRank < 1L) {
            return 0L;
        }
        return currentRank >= 300L ?
                Math.round(175.0 + (currentRank - 300) / 3) :
                Math.round(25.0 + currentRank / 2);
    }

    protected int getMfBasicPoints() {
        HashMap<String, Integer> basicPointsMap = new HashMap<>();

        basicPointsMap.put("1Easy", 31);
        basicPointsMap.put("1Normal", 72);
        basicPointsMap.put("1Hard", 126);
        basicPointsMap.put("1Expert", 241);

        basicPointsMap.put("2Easy", 64);
        basicPointsMap.put("2Normal", 150);
        basicPointsMap.put("2Hard", 262);
        basicPointsMap.put("2Expert", 500);

        basicPointsMap.put("3Easy", 99);
        basicPointsMap.put("3Normal", 234);
        basicPointsMap.put("3Hard", 408);
        basicPointsMap.put("3Expert", 777);

        return basicPointsMap.get(String.format("%s", songAmount) + difficulty);
    }

    protected int getMfConsumeLp() {
        HashMap<String, Integer> consumeLpMap = new HashMap<>();

        consumeLpMap.put("Easy", 4);
        consumeLpMap.put("Normal", 8);
        consumeLpMap.put("Hard", 12);
        consumeLpMap.put("Expert", 20);

        return consumeLpMap.get(difficulty);
    }

    public void setObjectivePoints(long objectivePoints) {
        this.objectivePoints = objectivePoints;
    }

    public void setCurrentPoints(long currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void setCurrentRank(long currentRank) {
        this.currentRank = currentRank;
    }

    public void setSongAmount(long songAmount) {
        this.songAmount = songAmount;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setWastedLpEveryDay(long wastedLpEveryDay) {
        this.wastedLpEveryDay = wastedLpEveryDay;
    }

    public void setPointAddition(Boolean pointAddition) {
        this.pointAddition = pointAddition;
    }

    public void setExperienceAddition(Boolean experienceAddition) {
        this.experienceAddition = experienceAddition;
    }

    public void setSongRankRatio(double songRankRatio) {
        this.songRankRatio = songRankRatio;
    }

    public void setComboRankRatio(double comboRankRatio) {
        this.comboRankRatio = comboRankRatio;
    }

    public void setCurrentLp(long currentLp) {
        this.currentLp = currentLp;
    }

    public void setCurrentExperience(long currentExperience) {
        this.currentExperience = currentExperience;
    }

    public void setEventEndDay(long eventEndDay) {
        this.eventEndDay = eventEndDay;
    }

    public void setEventEndHour(long eventEndHour) {
        this.eventEndHour = eventEndHour;
    }

    public void setEventLastTime(double eventLastTime) {
        this.eventLastTime = eventLastTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }
}
