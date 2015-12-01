package com.thoughtworks.lhli.lovelive_rock;

import android.content.Intent;
import android.view.MenuItem;

import com.thoughtworks.lhli.lovelive_rock.activity.CardActivity;
import com.thoughtworks.lhli.lovelive_rock.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void clickingCardNavigator_shouldStartCardActivity() {
        Intent expectedIntent = new Intent(activity, CardActivity.class);

        activity.findViewById(R.id.card_navigator).performClick();

        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }

    @Test
    public void selectingMenuActionCard_shouldStartCardActivity() {
        MenuItem item = new RoboMenuItem(R.id.action_card);
        Intent expectedIntent = new Intent(activity, CardActivity.class);

        activity.onOptionsItemSelected(item);

        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }
}
