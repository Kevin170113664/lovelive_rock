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

    public long getLovecaAmount() {
        long lovecaAmount = getLpShortage() / getBiggestLP();
        lovecaAmount = getLpShortage() % getBiggestLP() <= 0 ? lovecaAmount : lovecaAmount + 1;
        return lovecaAmount < 0 ? 0 : lovecaAmount;
    }

    public long getFinalPoints() {
        return currentPoints + getTotalPoints();
    }

    public long getFinalRank() {
        long originCurrentRank = currentRank;
        long finalRank = currentRank;
        long experience = getTotalExperience() + currentExperience;

        while (experience > getRankUpExp()) {
            experience -= getRankUpExp();
            finalRank += 1;
            currentRank += 1;
        }

        currentRank = originCurrentRank;
        return finalRank;
    }

    public long getFinalExperience() {
        long originCurrentRank = currentRank;
        long experience = getTotalExperience() + currentExperience;

        while (experience > getRankUpExp()) {
            experience -= getRankUpExp();
            currentRank += 1;
        }

        currentRank = originCurrentRank;
        return experience;
    }

    public long getTimesNeedToPlay() {
        return Math.round((objectivePoints - currentPoints) / getPointsWithinOncePlay()) + 1;
    }

    public String getTotalPlayTime() {
        return String.format("%s小时%s分钟", getPlayTimeMinutes() / 60, getPlayTimeMinutes() % 60);
    }

    public String getPlayTimeRatio() {
        return new DecimalFormat("0.0").format(getPlayTimeMinutes() / (eventLastTime * 60.0) * 100);
    }

    protected long getTotalRankUpLp() {
        long rankUpLp = 0;
        long originalRank = currentRank;

        while (currentRank + 1 <= getFinalRank()) {
            rankUpLp += getBiggestLP();
            currentRank += 1;
        }

        currentRank = originalRank;
        return rankUpLp;
    }

    protected long getTotalWastedLp() {
        return wastedLpEveryDay * eventEndDay;
    }

    protected long getTotalRecoveryLp() {
        return Math.round(eventLastTime * 10);
    }

    protected long getLpShortage() {
        long necessaryLp = getTimesNeedToPlay() * songAmount * getMfConsumeLp();
        long freeLp = currentLp + getTotalRankUpLp() + getTotalRecoveryLp() - getTotalWastedLp();

        return necessaryLp - freeLp;
    }

    protected long getPlayTimeMinutes() {
        return getMinutesWithinOncePlay() * getTimesNeedToPlay();
    }

    protected long getMinutesWithinOncePlay() {
        HashMap<Integer, Integer> consumeTimeMap = new HashMap<>();

        consumeTimeMap.put(1, 3);
        consumeTimeMap.put(2, 5);
        consumeTimeMap.put(3, 7);

        return consumeTimeMap.get(songAmount);
    }

    protected long getPointsWithinOncePlay() {
        double points = getMfBasicPoints();
        points = points * songRankRatio * comboRankRatio;
        return pointAddition ? Math.round(points * 1.1) : Math.round(points);
    }

    protected long getExperienceWithinOncePlay() {
        HashMap<String, Long> experienceMap = new HashMap<>();

        experienceMap.put("Easy", 12L);
        experienceMap.put("Normal", 26L);
        experienceMap.put("Hard", 46L);
        experienceMap.put("Expert", 83L);

        long basicExperience = experienceMap.get(difficulty) * songAmount;
        basicExperience = songRankRatio == 1 ? basicExperience / 2 : basicExperience;

        return experienceAddition ? Math.round(basicExperience * 1.1) : basicExperience;
    }

    protected long getTotalPoints() {
        return getTimesNeedToPlay() * getPointsWithinOncePlay();
    }

    protected long getTotalExperience() {
        return getTimesNeedToPlay() * getExperienceWithinOncePlay();
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
