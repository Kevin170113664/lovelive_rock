package com.thoughtworks.lhli.lovelive_rock.factory;

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

    private long lovecaAmount;
    private long finalPoints;
    private long finalRank;
    private long finalExperience;
    private long finalLp;
    private long timesNeedToPlay;
    private long totalPlayTime;
    private double playTimeRatio;

    public CalculatorFactory(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public CalculatorFactory() {
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

    protected double parseDoubleField(String value) {
        return isStringValid(value) ? Double.parseDouble(value) : 0.0;
    }

    protected long parseLongField(String value) {
        return isStringValid(value) ? Long.parseLong(value) : 0L;
    }

    protected boolean isStringValid(String value) {
        return value != null && !value.equals("");
    }

    public String getEventEndDay() {
        return isStringValid(eventEndTime) ? String.format("%s", DateTime.parse(eventEndTime).getDayOfMonth()) : "0";
    }

    public String getEventEndHour() {
        return isStringValid(eventEndTime) ? String.format("%s", DateTime.parse(eventEndTime).getHourOfDay() - 1) : "0";
    }

    public String getEventLastTime() {
        if (isStringValid(eventEndTime)) {
            Duration duration = new Duration(new DateTime(), DateTime.parse(eventEndTime));
            double lastTime = duration.getStandardHours() + duration.getStandardMinutes() % 60 / 60.0;
            return String.format("%s", new DecimalFormat("0.0").format(lastTime));
        } else {
            return "0";
        }
    }

    public long getLovecaAmount() {
        if (getBiggestLp() == 0) {
            return 0;
        }
        calculatePredictLovecaAmount();
        return lovecaAmount < 0 ? 0 : lovecaAmount;
    }

    public long getFinalRankUpExp() {
        return getRankUpExpByRank(getFinalRank());
    }

    //TODO write test for this method and refactor.
    protected void calculatePredictLovecaAmount() {
        lovecaAmount = 0L;
        finalLp = currentLp + Math.round(getRecoveryLp());
        finalExperience = currentExperience;
        finalPoints = currentPoints;
        timesNeedToPlay = 0L;
        long originalRank = currentRank;

        while (finalLp > getLpWithinOncePlay()) {
            finalLp -= getLpWithinOncePlay();
            timesNeedToPlay += 1;
            finalPoints += getPointsWithinOncePlay();
            finalExperience += getExperienceWithinOncePlay();
            if (finalExperience > getBiggestLp()) {
                finalExperience -= getRankUpExp();
                currentRank += 1;
                finalLp += getBiggestLp();
            }
        }

        while (true) {
            while (finalLp > getLpWithinOncePlay()) {
                finalLp -= getLpWithinOncePlay();
                timesNeedToPlay += 1;
                finalPoints += getPointsWithinOncePlay();
                finalExperience += getExperienceWithinOncePlay();
                if (finalExperience > getRankUpExp()) {
                    finalExperience -= getRankUpExp();
                    currentRank += 1;
                    finalLp += getBiggestLp();
                }
            }
            if (finalPoints < objectivePoints) {
                lovecaAmount += 1;
                finalLp += getBiggestLp();
            } else {
                break;
            }
        }

        finalRank = currentRank;
        totalPlayTime = timesNeedToPlay * getMinutesWithinOncePlay();
        playTimeRatio = totalPlayTime / (eventLastTime * 60.0);

        currentRank = originalRank;
    }

    protected double getRecoveryLp() {
        return eventLastTime * 10 - getTotalWastedLp();
    }

    protected long getTotalWastedLp() {
        return wastedLpEveryDay * eventEndDay;
    }

    protected long getPlayTimeMinutes() {
        return getMinutesWithinOncePlay() * getTimesNeedToPlay();
    }

    protected long getMinutesWithinOncePlay() {
        HashMap<Long, Long> consumeTimeMap = new HashMap<>();

        consumeTimeMap.put(1L, 3L);
        consumeTimeMap.put(2L, 5L);
        consumeTimeMap.put(3L, 7L);

        return consumeTimeMap.get(songAmount);
    }

    protected long getLpWithinOncePlay() {
        return songAmount * getMfConsumeLp();
    }

    protected long getPointsWithinOncePlay() {
        double points = getMfBasicPoints() * songRankRatio * comboRankRatio;

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

    protected long getRankUpExp() {
        return getRankUpExpByRank(currentRank);
    }

    protected long getRankUpExpByRank(long rank) {
        if (rank < 1L) {
            return 0L;
        }
        if (rank < 34L) {
            return Math.round(rank * rank * 0.28);
        }
        if (rank < 100L) {
            return Math.round((34.45 * rank - 551) / 2);
        }
        if (rank >= 100L) {
            return Math.round(34.45 * rank - 551);
        }
        return 0L;
    }

    protected long getBiggestLp() {
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

    public void setTotalPlayTime(long totalPlayTime) {
        this.totalPlayTime = totalPlayTime;
    }

    public void setLovecaAmount(long lovecaAmount) {
        this.lovecaAmount = lovecaAmount;
    }

    public long getFinalPoints() {
        return finalPoints;
    }

    public long getFinalRank() {
        return finalRank;
    }

    public long getFinalExperience() {
        return finalExperience;
    }

    public long getFinalLp() {
        return finalLp;
    }

    public long getTimesNeedToPlay() {
        return timesNeedToPlay;
    }

    public String getTotalPlayTime() {
        return String.format("%s小时%s分钟", totalPlayTime / 60, totalPlayTime % 60);
    }

    public String getPlayTimeRatio() {
        return new DecimalFormat("0.0").format(getPlayTimeMinutes() / (eventLastTime * 60.0) * 100) + "%";
    }
}
