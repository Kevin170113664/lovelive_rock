package com.thoughtworks.lhli.lovelive_rock.activity;

import com.thoughtworks.lhli.lovelive_rock.BuildConfig;
import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.stub;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class MedleyFestivalCalculatorActivityTest {

    private MedleyFestivalCalculatorActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MedleyFestivalCalculatorActivity.class);
    }

    @Test
    public void shouldLoadEventEndTimeCorrectly() {
        stub(LoveLiveApp.getInstance().getLatestEventEnd()).toReturn("2015-12-15T15:00:00+09:00");
        activity.setEventEndTime();

        assertEquals("15", activity.findViewById(R.id.event_end_day_text).toString());
        assertEquals("14", activity.findViewById(R.id.event_end_hour_text).toString());
    }
}