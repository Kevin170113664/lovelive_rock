package com.thoughtworks.lhli.lovelive_rock.factory;

import com.thoughtworks.lhli.lovelive_rock.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class CalculatorFactoryTest {

    private CalculatorFactory calculatorFactory;

    @Before
    public void setUp() {
        calculatorFactory = new CalculatorFactory();
    }

    @Test
    public void shouldParseDoubleField() {
        assertEquals(0.0, calculatorFactory.parseDoubleField(null));
        assertEquals(0.0, calculatorFactory.parseDoubleField(""));
        assertEquals(1.2, calculatorFactory.parseDoubleField("1.20"));
    }

    @Test
    public void shouldParseLongField() {
        assertEquals(0L, calculatorFactory.parseLongField(null));
        assertEquals(0L, calculatorFactory.parseLongField(""));
        assertEquals(12837L, calculatorFactory.parseLongField("12837"));
    }

    @Test
    public void shouldCalculateEventEndDay() {
        calculatorFactory.setEventEndTime(null);
        assertEquals("0", calculatorFactory.getEventEndDay());

        calculatorFactory.setEventEndTime("");
        assertEquals("0", calculatorFactory.getEventEndDay());

        calculatorFactory.setEventEndTime("2015-12-15T15:00:00+09:00");
        assertEquals("15", calculatorFactory.getEventEndDay());
    }

    @Test
    public void shouldCalculateEventEndHour() {
        calculatorFactory.setEventEndTime(null);
        assertEquals("0", calculatorFactory.getEventEndHour());

        calculatorFactory.setEventEndTime("");
        assertEquals("0", calculatorFactory.getEventEndHour());

        calculatorFactory.setEventEndTime("2015-12-15T15:00:00+09:00");
        assertEquals("14", calculatorFactory.getEventEndHour());
    }

// TODO: 12/19/15
//    @Test
//    public void shouldCalculateEventLastTime() {
//        calculatorFactory.setEventEndTime("2015-12-15T15:00:00+09:00");
//        Duration duration = new Duration(0L);
//        when(duration.getStandardHours()).thenReturn(10L);
//        when(duration.getStandardMinutes()).thenReturn(30L);
//
//        assertEquals(0L, calculatorFactory.getEventLastTime());
//    }

    @Test
    public void shouldCalculateLovecaAmount() {
        calculatorFactory.setLovecaAmount(-2L);
        assertEquals(0L, calculatorFactory.getLovecaAmount());

        calculatorFactory.setLovecaAmount(10086L);
        assertEquals(10086L, calculatorFactory.getLovecaAmount());
    }

    @Test
    public void shouldCalculateRankUpExp() {
        calculatorFactory.setExpRatio(2);

        assertEquals(0L, calculatorFactory.getRankUpExpByRank(-2L));
        assertEquals(305L, calculatorFactory.getRankUpExpByRank(33L));
        assertEquals(1430L, calculatorFactory.getRankUpExpByRank(99L));
        assertEquals(2894L, calculatorFactory.getRankUpExpByRank(100L));
        assertEquals(27009L, calculatorFactory.getRankUpExpByRank(800L));
    }

    @Test
    public void shouldCalculateBiggestLp() {
        calculatorFactory.setCurrentRank(2L);
        assertEquals(26L, calculatorFactory.getBiggestLp());

        calculatorFactory.setCurrentRank(100L);
        assertEquals(75L, calculatorFactory.getBiggestLp());

        calculatorFactory.setCurrentRank(800L);
        assertEquals(341L, calculatorFactory.getBiggestLp());

        calculatorFactory.setCurrentRank(-2L);
        assertEquals(0L, calculatorFactory.getBiggestLp());
    }

    @Test
    public void shouldCalculateBasicPoints() {
        calculatorFactory.setSongAmount(3);
        calculatorFactory.setDifficulty("Expert");
        assertEquals(777, calculatorFactory.getMfBasicPoints());

        calculatorFactory.setSongAmount(1);
        calculatorFactory.setDifficulty("Easy");
        assertEquals(31, calculatorFactory.getMfBasicPoints());

        calculatorFactory.setSongAmount(2);
        calculatorFactory.setDifficulty("Hard");
        assertEquals(262, calculatorFactory.getMfBasicPoints());

        calculatorFactory.setSongAmount(3);
        calculatorFactory.setDifficulty("Normal");
        assertEquals(234, calculatorFactory.getMfBasicPoints());
    }

    @Test
    public void shouldCalculateMfConsumeLp() {
        calculatorFactory.setDifficulty("Expert");
        assertEquals(20, calculatorFactory.getMfConsumeLp());

        calculatorFactory.setDifficulty("Hard");
        assertEquals(12, calculatorFactory.getMfConsumeLp());

        calculatorFactory.setDifficulty("Normal");
        assertEquals(8, calculatorFactory.getMfConsumeLp());

        calculatorFactory.setDifficulty("Easy");
        assertEquals(4, calculatorFactory.getMfConsumeLp());
    }

    @Test
    public void shouldCalculateLpWithinOncePlay() {
        calculatorFactory.setSongAmount(2);
        calculatorFactory.setDifficulty("Hard");
        assertEquals(24, calculatorFactory.getMfLpWithinOncePlay());

        calculatorFactory.setSongAmount(3);
        calculatorFactory.setDifficulty("Expert");
        assertEquals(60, calculatorFactory.getMfLpWithinOncePlay());
    }

    @Test
    public void shouldCalculateMfPointsWithinOncePlay() {
        calculatorFactory.setSongAmount(3);
        calculatorFactory.setDifficulty("Expert");
        calculatorFactory.setPointAddition(true);
        calculatorFactory.setSongRankRatio(1.2);

        calculatorFactory.setComboRankRatio(1.08);
        assertEquals(1108, calculatorFactory.getMfPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.06);
        assertEquals(1087, calculatorFactory.getMfPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.04);
        assertEquals(1067, calculatorFactory.getMfPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.02);
        assertEquals(1046, calculatorFactory.getMfPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.0);
        assertEquals(1026, calculatorFactory.getMfPointsWithinOncePlay());

        calculatorFactory.setDifficulty("Hard");

        calculatorFactory.setComboRankRatio(1.08);
        assertEquals(582, calculatorFactory.getMfPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.06);
        assertEquals(571, calculatorFactory.getMfPointsWithinOncePlay());
    }

    @Test
    public void shouldCalculateExpWithinOncePlay() {
        calculatorFactory.setExpRatio(2);
        calculatorFactory.setDifficulty("Expert");
        calculatorFactory.setSongAmount(1);
        calculatorFactory.setExperienceAddition(false);
        calculatorFactory.setSongRankRatio(1);
        assertEquals(41, calculatorFactory.getMfExperienceWithinOncePlay());

        calculatorFactory.setSongAmount(3);
        calculatorFactory.setExperienceAddition(true);
        calculatorFactory.setSongRankRatio(1.2);
        assertEquals(274, calculatorFactory.getMfExperienceWithinOncePlay());

        calculatorFactory.setExperienceAddition(false);
        assertEquals(249, calculatorFactory.getMfExperienceWithinOncePlay());
    }

// TODO how to mock the time
//    @Test
//    public void shouldCalculateTotalWastedLp() {
//        calculatorFactory = new CalculatorFactory() {
//            DateTime DateTimeNow = new DateTime(2015, 12,
//                    26, 21, 0, 0, 0);
//        };
//        calculatorFactory.setWastedLpEveryDay(7L);
//        calculatorFactory.setEventEndDay(31L);
//
//        assertEquals(35, calculatorFactory.getTotalWastedLp());
//    }

    @Test
    public void shouldCalculatePlayTimeMinutes() {
        calculatorFactory = new CalculatorFactory() {
            public long getMinutesWithinOncePlay() {
                return 7;
            }

            public long getTimesNeedToPlay() {
                return 250;
            }
        };

        assertEquals(1750, calculatorFactory.getPlayTimeMinutes());
    }

    @Test
    public void shouldCalculatePlayTimeRatio() {
        calculatorFactory.setPlayTimeRatio(0.166);

        assertEquals("16.6%", calculatorFactory.getPlayTimeRatio());
    }

    @Test
    public void shouldCalculateMinutesWithinOncePlay() {
        calculatorFactory.setSongAmount(3);
        assertEquals(7, calculatorFactory.getMinutesWithinOncePlay());

        calculatorFactory.setSongAmount(2);
        assertEquals(5, calculatorFactory.getMinutesWithinOncePlay());

        calculatorFactory.setSongAmount(1);
        assertEquals(3, calculatorFactory.getMinutesWithinOncePlay());
    }

    @Test
    public void shouldCalculateTotalPlayTime() {
        calculatorFactory.setTotalPlayTime(338L);

        assertEquals("5Hour(s)38Min(s)", calculatorFactory.getTotalPlayTime());
    }

    @Test
    public void shouldCalculateNormalExpWithinOncePlay() {
        assertEquals(83L, calculatorFactory.getNormalExpWithinOncePlay(25L));
        assertEquals(46L, calculatorFactory.getNormalExpWithinOncePlay(15L));
        assertEquals(26L, calculatorFactory.getNormalExpWithinOncePlay(10L));
        assertEquals(12L, calculatorFactory.getNormalExpWithinOncePlay(5L));

        assertEquals(83L, calculatorFactory.getNormalExpWithinOncePlay("Expert"));
        assertEquals(46L, calculatorFactory.getNormalExpWithinOncePlay("Hard"));
        assertEquals(26L, calculatorFactory.getNormalExpWithinOncePlay("Normal"));
        assertEquals(12L, calculatorFactory.getNormalExpWithinOncePlay("Easy"));

        assertEquals(83L, calculatorFactory.getNormalExpWithinOncePlay("4×Expert"));
        assertEquals(46L, calculatorFactory.getNormalExpWithinOncePlay("4×Hard"));
        assertEquals(26L, calculatorFactory.getNormalExpWithinOncePlay("4×Normal"));
        assertEquals(12L, calculatorFactory.getNormalExpWithinOncePlay("4×Easy"));
    }

    @Test
    public void shouldCalculateItemsWithinOncePlay() {
        calculatorFactory.setEventDifficulty("4×Expert");
        assertEquals(300L, calculatorFactory.getConsumeItemWithinOncePlay());

        calculatorFactory.setEventDifficulty("Expert");
        assertEquals(75L, calculatorFactory.getConsumeItemWithinOncePlay());

        calculatorFactory.setEventDifficulty("4×Easy");
        assertEquals(60L, calculatorFactory.getConsumeItemWithinOncePlay());
    }

    @Test
    public void shouldCalculateBasicPointsWithinOncePlay() {
        calculatorFactory.setConsumeLP(25L);
        assertEquals(27L, calculatorFactory.getNormalBasicPointsWithinOncePlay());

        calculatorFactory.setConsumeLP(15L);
        assertEquals(16L, calculatorFactory.getNormalBasicPointsWithinOncePlay());

        calculatorFactory.setConsumeLP(10L);
        assertEquals(10L, calculatorFactory.getNormalBasicPointsWithinOncePlay());

        calculatorFactory.setConsumeLP(5L);
        assertEquals(5L, calculatorFactory.getNormalBasicPointsWithinOncePlay());
    }

    @Test
    public void shouldCalculateFieldsWhenMfPlayWithFreeLp() {
        calculatorFactory = new CalculatorFactory() {
            public long getMfLpWithinOncePlay() {
                return 60L;
            }

            public long getMfPointsWithinOncePlay() {
                return 1108L;
            }

            public long getMfExperienceWithinOncePlay() {
                return 249L;
            }
        };
        calculatorFactory.setFinalLp(2400L);
        calculatorFactory.setFinalExperience(0L);
        calculatorFactory.setFinalPoints(0L);
        calculatorFactory.setCurrentRank(200L);
        calculatorFactory.setTimesNeedToPlay(0L);

        calculatorFactory.mfPlayWithFreeLp();

        assertEquals(5L, calculatorFactory.getFinalLp());
        assertEquals(4119L, calculatorFactory.getFinalExperience());
        assertEquals(46536L, calculatorFactory.getFinalPoints());
        assertEquals(42L, calculatorFactory.getTimesNeedToPlay());
    }

    @Test
    public void shouldCalculateFieldsWhenMfPlayWithLoveca() {
        calculatorFactory = new CalculatorFactory() {
            public long getMfLpWithinOncePlay() {
                return 60L;
            }

            public long getMfPointsWithinOncePlay() {
                return 1108L;
            }

            public long getMfExperienceWithinOncePlay() {
                return 249L;
            }
        };
        calculatorFactory.setObjectivePoints(70000L);
        calculatorFactory.setLovecaAmount(0L);
        calculatorFactory.setFinalLp(0L);
        calculatorFactory.setFinalExperience(0L);
        calculatorFactory.setFinalPoints(0L);
        calculatorFactory.setCurrentRank(200L);
        calculatorFactory.setTimesNeedToPlay(0L);

        calculatorFactory.mfPlayWithLoveca();

        assertEquals(29L, calculatorFactory.getLovecaAmount());
        assertEquals(41L, calculatorFactory.getFinalLp());
        assertEquals(3224L, calculatorFactory.getFinalExperience());
        assertEquals(70912L, calculatorFactory.getFinalPoints());
        assertEquals(64L, calculatorFactory.getTimesNeedToPlay());
    }

    @Test
    public void shouldCalculateFieldsWhenSmPlayWithFreeLp() {
        calculatorFactory = new CalculatorFactory() {
            public long getSmLpWithinOncePlay() {
                return 25L;
            }

            public long getNormalExpWithinOncePlay(String difficulty) {
                return 83L;
            }
        };
        calculatorFactory.setOncePoints(408L);
        calculatorFactory.setFinalLp(2400L);
        calculatorFactory.setFinalExperience(0L);
        calculatorFactory.setFinalPoints(0L);
        calculatorFactory.setCurrentRank(200L);
        calculatorFactory.setTimesNeedToPlay(0L);

        calculatorFactory.smPlayWithFreeLp();

        assertEquals(0L, calculatorFactory.getFinalLp());
        assertEquals(2044L, calculatorFactory.getFinalExperience());
        assertEquals(41208L, calculatorFactory.getFinalPoints());
        assertEquals(101L, calculatorFactory.getTimesNeedToPlay());
    }

    @Test
    public void shouldCalculateFieldsWhenSmPlayWithLoveca() {
        calculatorFactory = new CalculatorFactory() {
            public long getSmLpWithinOncePlay() {
                return 25L;
            }

            public long getNormalExpWithinOncePlay(String difficulty) {
                return 83L;
            }
        };
        calculatorFactory.setOncePoints(408L);
        calculatorFactory.setObjectivePoints(60000L);
        calculatorFactory.setLovecaAmount(0L);
        calculatorFactory.setFinalLp(0L);
        calculatorFactory.setFinalExperience(0L);
        calculatorFactory.setFinalPoints(0L);
        calculatorFactory.setCurrentRank(200L);
        calculatorFactory.setTimesNeedToPlay(0L);

        calculatorFactory.smPlayWithLoveca();

        assertEquals(29L, calculatorFactory.getLovecaAmount());
        assertEquals(0L, calculatorFactory.getFinalLp());
        assertEquals(6111L, calculatorFactory.getFinalExperience());
        assertEquals(61200L, calculatorFactory.getFinalPoints());
        assertEquals(150L, calculatorFactory.getTimesNeedToPlay());
    }

    @Test
    public void shouldCalculateFieldsWhenNormalPlayWithFreeLp() {
        calculatorFactory = new CalculatorFactory() {
            public long getNormalBasicPointsWithinOncePlay() {
                return 27L;
            }

            public long getNormalExpWithinOncePlay(long consumeLp) {
                return 83L;
            }

            public long getNormalExpWithinOncePlay(String eventDifficulty) {
                return 83L;
            }

            public long getConsumeItemWithinOncePlay() {
                return 75L;
            }
        };
        calculatorFactory.setConsumeLP(25L);
        calculatorFactory.setOncePoints(404L);
        calculatorFactory.setFinalLp(2400L);
        calculatorFactory.setFinalExperience(0L);
        calculatorFactory.setFinalPoints(0L);
        calculatorFactory.setFinalItem(0L);
        calculatorFactory.setCurrentRank(200L);
        calculatorFactory.setTimesNeedToPlay(0L);
        calculatorFactory.setEventTimesNeedToPlay(0L);

        calculatorFactory.normalPlayWithFreeLp();

        assertEquals(0L, calculatorFactory.getFinalLp());
        assertEquals(5032L, calculatorFactory.getFinalExperience());
        assertEquals(17271L, calculatorFactory.getFinalPoints());
        assertEquals(27L, calculatorFactory.getFinalItem());
        assertEquals(101L, calculatorFactory.getTimesNeedToPlay());
        assertEquals(36L, calculatorFactory.getEventTimesNeedToPlay());
    }

    @Test
    public void shouldCalculateFieldsWhenNormalPlayWithLoveca() {
        calculatorFactory = new CalculatorFactory() {
            public long getNormalBasicPointsWithinOncePlay() {
                return 27L;
            }

            public long getNormalExpWithinOncePlay(long consumeLp) {
                return 83L;
            }

            public long getNormalExpWithinOncePlay(String eventDifficulty) {
                return 83L;
            }

            public long getConsumeItemWithinOncePlay() {
                return 75L;
            }
        };
        calculatorFactory.setObjectivePoints(30000L);
        calculatorFactory.setLovecaAmount(0L);
        calculatorFactory.setConsumeLP(25L);
        calculatorFactory.setOncePoints(404L);
        calculatorFactory.setFinalLp(0L);
        calculatorFactory.setFinalExperience(0L);
        calculatorFactory.setFinalPoints(0L);
        calculatorFactory.setFinalItem(0L);
        calculatorFactory.setCurrentRank(200L);
        calculatorFactory.setTimesNeedToPlay(0L);
        calculatorFactory.setEventTimesNeedToPlay(0L);

        calculatorFactory.normalPlayWithLoveca();

        assertEquals(32L, calculatorFactory.getLovecaAmount());
        assertEquals(12L, calculatorFactory.getFinalLp());
        assertEquals(634L, calculatorFactory.getFinalExperience());
        assertEquals(30177L, calculatorFactory.getFinalPoints());
        assertEquals(0L, calculatorFactory.getFinalItem());
        assertEquals(175L, calculatorFactory.getTimesNeedToPlay());
        assertEquals(63L, calculatorFactory.getEventTimesNeedToPlay());
    }

    @Test
    public void shouldValidateEventEndTime() {
        assertEquals("0", calculatorFactory.getEventLastTime("32", "22"));
        assertEquals("0", calculatorFactory.getEventLastTime("28", "30"));
    }
}