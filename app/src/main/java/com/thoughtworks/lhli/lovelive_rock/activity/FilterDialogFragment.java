package com.thoughtworks.lhli.lovelive_rock.activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.bus.FilterEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class FilterDialogFragment extends DialogFragment {

    @Bind(R.id.rarity_spinner)
    protected Spinner raritySpinner;

    @Bind(R.id.idol_spinner)
    protected Spinner idolSpinner;

    @Bind(R.id.attribute_spinner)
    protected Spinner attributeSpinner;

    @Bind(R.id.grade_spinner)
    protected Spinner gradeSpinner;

    @Bind(R.id.sub_team_spinner)
    protected Spinner subTeamSpinner;

    @Bind(R.id.skill_type_spinner)
    protected Spinner skillTypeSpinner;

    @Bind(R.id.event_spinner)
    protected Spinner eventSpinner;

    @Bind(R.id.promo_spinner)
    protected Spinner promoSpinner;

    @Bind(R.id.collection_spinner)
    protected Spinner collectionSpinner;

    private ArrayAdapter<CharSequence> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.TOP | Gravity.LEFT);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 150;
        window.setAttributes(params);

        return inflater.inflate(R.layout.dialog_filter, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());
        setDropDownList();
        setDropDownListSelectedEvent();
    }

    private void setDropDownList() {
        setSpinnerAdapter(raritySpinner, R.array.rarity_array);
        setSpinnerAdapter(idolSpinner, R.array.idol_array);
        setSpinnerAdapter(attributeSpinner, R.array.attribute_array);
        setSpinnerAdapter(gradeSpinner, R.array.grade_array);
        setSpinnerAdapter(subTeamSpinner, R.array.sub_team_array);
        setSpinnerAdapter(skillTypeSpinner, R.array.skill_type_array);
        setSpinnerAdapter(eventSpinner, R.array.is_event_card_or_not);
        setSpinnerAdapter(promoSpinner, R.array.is_promo_or_not);
        setSpinnerAdapter(collectionSpinner, R.array.collection_array);
    }

    private void setSpinnerAdapter(Spinner spinner, int resourceId) {
        adapter = ArrayAdapter.createFromResource(getActivity(),
                resourceId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void setDropDownListSelectedEvent() {
        raritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendFilterToCardActivity(parent, raritySpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        attributeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendFilterToCardActivity(parent, attributeSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendFilterToCardActivity(parent, gradeSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        idolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendFilterToCardActivity(parent, idolSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        subTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendFilterToCardActivity(parent, subTeamSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        skillTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendFilterToCardActivity(parent, skillTypeSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        eventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendFilterToCardActivity(parent, eventSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        promoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendFilterToCardActivity(parent, promoSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        collectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendFilterToCardActivity(parent, collectionSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void sendFilterToCardActivity(AdapterView<?> parent, String selectedText) {
        EventBus.getDefault().post(new FilterEvent(parent.getId(), selectedText));
    }
}
