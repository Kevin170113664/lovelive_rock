package com.thoughtworks.lhli.lovelive_rock.factory;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.R;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.text.DecimalFormat;
import java.util.HashMap;

public class CalculatorFactory {

    private final Integer MINUTES_FOR_ONE_SONG = 3;

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

    private long currentItem;
    private String eventDifficulty;
    private String eventRank;
    private String eventCombo;
    private long oncePoints;
    private long consumeLP;

    private long lovecaAmount;
    private long finalPoints;
    private long finalRank;
    private long finalExperience;
    private long finalLp;
    private long timesNeedToPlay;
    private long totalPlayTime;
    private double playTimeRatio;
    private long finalItem;
    private long eventTimesNeedToPlay;

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

    public CalculatorFactory(String objectivePoints, String currentPoints, String currentRank,
                             String wastedLpEveryDay, String currentLp, String currentExperience,
                             String eventEndDay, String eventLastTime, String currentItem,
                             String eventDifficulty, String eventRank, String eventCombo,
                             String oncePoints, String consumeLP) {
        this.objectivePoints = parseLongField(objectivePoints);
        this.currentPoints = parseLongField(currentPoints);
        this.currentRank = parseLongField(currentRank);
        this.wastedLpEveryDay = parseLongField(wastedLpEveryDay);
        this.currentLp = parseLongField(currentLp);
        this.currentExperience = parseLongField(currentExperience);
        this.eventEndDay = parseLongField(eventEndDay);
        this.eventLastTime = parseDoubleField(eventLastTime);
        this.currentItem = parseLongField(currentItem);
        this.eventDifficulty = eventDifficulty;
        this.eventRank = eventRank;
        this.eventCombo = eventCombo;
        this.oncePoints = parseLongField(oncePoints);
        this.consumeLP = parseLongField(consumeLP);
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
        return lovecaAmount < 0 ? 0 : lovecaAmount;
    }

    public long getFinalRankUpExp() {
        return getRankUpExpByRank(getFinalRank());
    }

    public void calculateMfProcess() {
        if (getBiggestLp() == 0) {
            return;
        }
        long originalRank = currentRank;
        initialisePredictFields();
        mfPlayWithFreeLp();
        mfPlayWithLoveca();
        calculateMfResultAfterPlay();
        currentRank = originalRank;
    }

    protected void initialisePredictFields() {
        lovecaAmount = 0L;
        finalPoints = currentPoints;
        finalExperience = currentExperience;
        finalLp = currentLp + Math.round(getRecoveryLp());
        finalItem = currentItem;
        timesNeedToPlay = 0L;
        eventTimesNeedToPlay = 0L;
    }

    protected void calculateMfResultAfterPlay() {
        finalRank = currentRank;
        totalPlayTime = timesNeedToPlay * getMinutesWithinOncePlay();
        playTimeRatio = totalPlayTime / (eventLastTime * 60.0);
    }

    protected void calculateNormalResultAfterPlay() {
        finalRank = currentRank;
        totalPlayTime = (eventTimesNeedToPlay + timesNeedToPlay) * MINUTES_FOR_ONE_SONG;
        playTimeRatio = totalPlayTime / (eventLastTime * 60.0);
    }

    protected void consumeOneLoveca() {
        lovecaAmount += 1;
        finalLp += getBiggestLp();
    }

    protected void mfPlayWithFreeLp() {
        while (finalLp >= getMfLpWithinOncePlay()) {
            mfPlayOnceWithEnoughLp();
            while (finalExperience >= getCurrentRankUpExp()) {
                upgradeOneRankWithEnoughExp();
            }
        }
    }

    protected void mfPlayWithLoveca() {
        while (true) {
            while (finalLp >= getMfLpWithinOncePlay()) {
                mfPlayOnceWithEnoughLp();
            }
            if (finalPoints < objectivePoints) {
                consumeOneLoveca();
            } else {
                break;
            }
        }
    }

    protected void normalPlayWithFreeLp() {
        while (finalLp >= consumeLP) {
            normalPlayOnceWithEnoughLp();
            while (finalItem >= getConsumeItemWithinOncePlay()) {
                normalPlayOnceWithEnoughItem();
            }
        }
    }

    protected void normalPlayWithLoveca() {
        while (finalPoints < objectivePoints) {
            consumeOneLoveca();
            while (finalLp >= consumeLP) {
                normalPlayOnceWithEnoughLp();
                while (finalItem >= getConsumeItemWithinOncePlay()) {
                    normalPlayOnceWithEnoughItem();
                }
            }
        }
    }

    private void normalPlayOnceWithEnoughItem() {
        finalItem -= getConsumeItemWithinOncePlay();
        eventTimesNeedToPlay += 1;
        finalPoints += oncePoints;
        finalExperience += getNormalExpWithinOncePlay(eventDifficulty);
        while (finalExperience >= getCurrentRankUpExp()) {
            upgradeOneRankWithEnoughExp();
        }
    }

    protected long getNormalExpWithinOncePlay(Long consumeLP) {
        HashMap<Long, Long> normalExpMap = new HashMap<>();

        normalExpMap.put(25L, 83L);
        normalExpMap.put(15L, 46L);
        normalExpMap.put(10L, 26L);
        normalExpMap.put(5L, 12L);

        return normalExpMap.get(consumeLP);
    }

    protected long getNormalExpWithinOncePlay(String eventDifficulty) {
        String difficulty = eventDifficulty;
        if (difficulty.substring(0, 1).equals("4")) {
            difficulty = difficulty.substring(2);
        }

        HashMap<String, Long> normalExpMap = new HashMap<>();
        normalExpMap.put("Expert", 83L);
        normalExpMap.put("Hard", 46L);
        normalExpMap.put("Normal", 26L);
        normalExpMap.put("Easy", 12L);

        return normalExpMap.get(difficulty);
    }

    protected void upgradeOneRankWithEnoughExp() {
        finalExperience -= getCurrentRankUpExp();
        currentRank += 1;
        finalLp += getBiggestLp();
    }

    protected void mfPlayOnceWithEnoughLp() {
        finalLp -= getMfLpWithinOncePlay();
        timesNeedToPlay += 1;
        finalPoints += getMfPointsWithinOncePlay();
        finalExperience += getMfExperienceWithinOncePlay();
        while (finalExperience >= getCurrentRankUpExp()) {
            upgradeOneRankWithEnoughExp();
        }
    }

    protected void normalPlayOnceWithEnoughLp() {
        finalLp -= consumeLP;
        timesNeedToPlay += 1;
        finalItem += getNormalBasicPointsWithinOncePlay();
        finalPoints += getNormalBasicPointsWithinOncePlay();
        finalExperience += getNormalExpWithinOncePlay(consumeLP);
        while (finalExperience >= getCurrentRankUpExp()) {
            upgradeOneRankWithEnoughExp();
        }
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

    protected long getMfLpWithinOncePlay() {
        return songAmount * getMfConsumeLp();
    }

    protected long getConsumeItemWithinOncePlay() {
        Boolean isFourMultiply = false;
        String difficulty = eventDifficulty;
        if (difficulty.substring(0, 1).equals("4")) {
            isFourMultiply = true;
            difficulty = difficulty.substring(2);
        }

        HashMap<String, Long> consumeItemMap = new HashMap<>();
        consumeItemMap.put("Expert", 75L);
        consumeItemMap.put("Hard", 45L);
        consumeItemMap.put("Normal", 30L);
        consumeItemMap.put("Easy", 15L);

        return isFourMultiply ? consumeItemMap.get(difficulty) * 4 : consumeItemMap.get(difficulty);
    }

    protected long getMfPointsWithinOncePlay() {
        double points = getMfBasicPoints() * songRankRatio * comboRankRatio;

        return pointAddition ? Math.round(points * 1.1) : Math.round(points);
    }

    protected long getNormalBasicPointsWithinOncePlay() {
        HashMap<Long, Long> normalBasicPointsMap = new HashMap<>();

        normalBasicPointsMap.put(25L, 27L);
        normalBasicPointsMap.put(15L, 16L);
        normalBasicPointsMap.put(10L, 10L);
        normalBasicPointsMap.put(5L, 5L);

        return normalBasicPointsMap.get(consumeLP);
    }

    protected long getMfExperienceWithinOncePlay() {
        HashMap<String, Long> experienceMap = new HashMap<>();

        experienceMap.put("Easy", 12L);
        experienceMap.put("Normal", 26L);
        experienceMap.put("Hard", 46L);
        experienceMap.put("Expert", 83L);

        long basicExperience = experienceMap.get(difficulty) * songAmount;
        basicExperience = songRankRatio == 1 ? basicExperience / 2 : basicExperience;

        return experienceAddition ? Math.round(basicExperience * 1.1) : basicExperience;
    }

    protected long getCurrentRankUpExp() {
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

    public void setFinalPoints(long finalPoints) {
        this.finalPoints = finalPoints;
    }

    public void setFinalRank(long finalRank) {
        this.finalRank = finalRank;
    }

    public void setFinalExperience(long finalExperience) {
        this.finalExperience = finalExperience;
    }

    public void setFinalLp(long finalLp) {
        this.finalLp = finalLp;
    }

    public void setTimesNeedToPlay(long timesNeedToPlay) {
        this.timesNeedToPlay = timesNeedToPlay;
    }

    public void setPlayTimeRatio(double playTimeRatio) {
        this.playTimeRatio = playTimeRatio;
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

    public void setCurrentItem(long currentItem) {
        this.currentItem = currentItem;
    }

    public void setEventDifficulty(String eventDifficulty) {
        this.eventDifficulty = eventDifficulty;
    }

    public void setEventRank(String eventRank) {
        this.eventRank = eventRank;
    }

    public void setEventCombo(String eventCombo) {
        this.eventCombo = eventCombo;
    }

    public void setOncePoints(long oncePoints) {
        this.oncePoints = oncePoints;
    }

    public void setConsumeLP(long consumeLP) {
        this.consumeLP = consumeLP;
    }

    public void setFinalItem(long finalItem) {
        this.finalItem = finalItem;
    }

    public void setEventTimesNeedToPlay(long eventTimesNeedToPlay) {
        this.eventTimesNeedToPlay = eventTimesNeedToPlay;
    }

    public String getTotalPlayTime() {
        return String.format("%s" + LoveLiveApp.getInstance().getString(R.string.hour_unit) + "%s" + LoveLiveApp.getInstance().getString(R.string.minute_unit),
                totalPlayTime / 60, totalPlayTime % 60);
    }

    public String getPlayTimeRatio() {
        return new DecimalFormat("0.0").format(playTimeRatio * 100) + "%";
    }

    public void calculateNormalProcess() {
        if (getBiggestLp() == 0) {
            return;
        }
        long originalRank = currentRank;
        initialisePredictFields();
        normalPlayWithFreeLp();
        normalPlayWithLoveca();
        calculateNormalResultAfterPlay();
        currentRank = originalRank;
    }

    public long getFinalItem() {
        return finalItem;
    }

    public long getEventTimesNeedToPlay() {
        return eventTimesNeedToPlay;
    }
}
