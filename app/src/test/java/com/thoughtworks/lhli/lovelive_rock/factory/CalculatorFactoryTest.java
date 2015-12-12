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

//    TODO
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
    public void getLovecaAmount() {
        calculatorFactory = new CalculatorFactory() {
            public void calculatePredictLovecaAmount() {
            }

            public long getBiggestLp() {
                return 125L;
            }
        };
        calculatorFactory.setLovecaAmount(-2L);
        assertEquals(0, calculatorFactory.getLovecaAmount());

        calculatorFactory = new CalculatorFactory() {
            public void calculatePredictLovecaAmount() {
            }

            public long getBiggestLp() {
                return 0L;
            }
        };
        calculatorFactory.setLovecaAmount(10086L);
        assertEquals(0, calculatorFactory.getLovecaAmount());

        calculatorFactory = new CalculatorFactory() {
            public void calculatePredictLovecaAmount() {
            }

            public long getBiggestLp() {
                return 250L;
            }
        };
        calculatorFactory.setLovecaAmount(10010L);
        assertEquals(10010L, calculatorFactory.getLovecaAmount());
    }

    @Test
    public void shouldCalculateRankUpExp() {
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
        assertEquals(24, calculatorFactory.getLpWithinOncePlay());

        calculatorFactory.setSongAmount(3);
        calculatorFactory.setDifficulty("Expert");
        assertEquals(60, calculatorFactory.getLpWithinOncePlay());
    }

    @Test
    public void shouldCalculateMfPointsWithinOncePlay() {
        calculatorFactory.setSongAmount(3);
        calculatorFactory.setDifficulty("Expert");
        calculatorFactory.setPointAddition(true);
        calculatorFactory.setSongRankRatio(1.2);

        calculatorFactory.setComboRankRatio(1.08);
        assertEquals(1108, calculatorFactory.getPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.06);
        assertEquals(1087, calculatorFactory.getPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.04);
        assertEquals(1067, calculatorFactory.getPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.02);
        assertEquals(1046, calculatorFactory.getPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.0);
        assertEquals(1026, calculatorFactory.getPointsWithinOncePlay());

        calculatorFactory.setDifficulty("Hard");

        calculatorFactory.setComboRankRatio(1.08);
        assertEquals(582, calculatorFactory.getPointsWithinOncePlay());

        calculatorFactory.setComboRankRatio(1.06);
        assertEquals(571, calculatorFactory.getPointsWithinOncePlay());
    }

    @Test
    public void shouldCalculateExpWithinOncePlay() {
        calculatorFactory.setDifficulty("Expert");
        calculatorFactory.setSongAmount(1);
        calculatorFactory.setExperienceAddition(false);
        calculatorFactory.setSongRankRatio(1);
        assertEquals(41, calculatorFactory.getExperienceWithinOncePlay());

        calculatorFactory.setSongAmount(3);
        calculatorFactory.setExperienceAddition(true);
        calculatorFactory.setSongRankRatio(1.2);
        assertEquals(274, calculatorFactory.getExperienceWithinOncePlay());

        calculatorFactory.setExperienceAddition(false);
        assertEquals(249, calculatorFactory.getExperienceWithinOncePlay());
    }

    @Test
    public void shouldCalculateTotalWastedLp() {
        calculatorFactory.setWastedLpEveryDay(7L);
        calculatorFactory.setEventEndDay(4L);

        assertEquals(28, calculatorFactory.getTotalWastedLp());
    }

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
        calculatorFactory = new CalculatorFactory() {
            public long getPlayTimeMinutes() {
                return 1000L;
            }
        };
        calculatorFactory.setEventLastTime(100.5);

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

        assertEquals("5小时38分钟", calculatorFactory.getTotalPlayTime());
    }
}