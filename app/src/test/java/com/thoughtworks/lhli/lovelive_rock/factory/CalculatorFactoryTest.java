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
    public void shouldCalculateRankUpExpCorrectly() {
        calculatorFactory.setCurrentRank(33L);
        assertEquals(305L, calculatorFactory.getRankUpExp());

        calculatorFactory.setCurrentRank(99L);
        assertEquals(1430L, calculatorFactory.getRankUpExp());

        calculatorFactory.setCurrentRank(100L);
        assertEquals(2894L, calculatorFactory.getRankUpExp());

        calculatorFactory.setCurrentRank(800L);
        assertEquals(27009L, calculatorFactory.getRankUpExp());

        calculatorFactory.setCurrentRank(-2L);
        assertEquals(0L, calculatorFactory.getRankUpExp());
    }

    @Test
    public void shouldCalculateBiggestLpCorrectly() {
        calculatorFactory.setCurrentRank(2L);
        assertEquals(26L, calculatorFactory.getBiggestLP());

        calculatorFactory.setCurrentRank(100L);
        assertEquals(75L, calculatorFactory.getBiggestLP());

        calculatorFactory.setCurrentRank(800L);
        assertEquals(341L, calculatorFactory.getBiggestLP());

        calculatorFactory.setCurrentRank(-2L);
        assertEquals(0L, calculatorFactory.getBiggestLP());
    }

    @Test
    public void shouldCalculateBasicPointsCorrectly() {
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
    public void shouldCalculateMfConsumeLpCorrectly() {
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
    public void shouldCalculateMfPointsWithinEveryPlayCorrectly() {
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
    public void shouldCalculateExpWithinEveryPlayCorrectly() {
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
    public void shouldCalculatePlayTimeRatioCorrectly() {
        calculatorFactory = new CalculatorFactory() {
            public long getPlayTimeMinutes() {
                return 1000L;
            }
        };
        calculatorFactory.setEventLastTime(100.5);

        assertEquals("16.6", calculatorFactory.getPlayTimeRatio());
    }

    @Test
    public void shouldCalculateTotalPlayTimeCorrectly() {
        calculatorFactory.setTotalPlayTime(338L);

        assertEquals("5小时38分钟", calculatorFactory.getTotalPlayTime());
    }

    @Test
    public void shouldCalculateTotalRankUpLpCorrectly() {
        calculatorFactory = new CalculatorFactory() {
            public long getFinalRank() {
                return 303L;
            }
        };
        calculatorFactory.setCurrentRank(298L);

        assertEquals(873, calculatorFactory.getTotalRankUpLp());
    }

    @Test
    public void shouldCalculateTotalWastedLpCorrectly() {
        calculatorFactory.setWastedLpEveryDay(7L);
        calculatorFactory.setEventEndDay(4L);

        assertEquals(28, calculatorFactory.getTotalWastedLp());
    }
}