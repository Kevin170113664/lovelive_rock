package com.thoughtworks.lhli.lovelive_rock;

import android.content.Intent;

import com.thoughtworks.lhli.lovelive_rock.activity.CardActivity;
import com.thoughtworks.lhli.lovelive_rock.activity.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class MainActivityTest {

    @Test
    public void clickingCardNavigator_shouldStartCardActivity() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        Intent expectedIntent = new Intent(activity, CardActivity.class);

        activity.findViewById(R.id.card_navigator).performClick();

        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }


}
