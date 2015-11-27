package com.thoughtworks.lhli.lovelive_rock.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.thoughtworks.lhli.lovelive_rock.R;

import butterknife.Bind;

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

    private ArrayAdapter<CharSequence> adapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.filter_dialog, null));
        return builder.create();
    }

    private void setDropDownList() {
//        adapter = ArrayAdapter.createFromResource(this,
//                R.array.rarity_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        raritySpinner.setAdapter(adapter);
//
//        adapter = ArrayAdapter.createFromResource(this,
//                R.array.idol_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        idolSpinner.setAdapter(adapter);
//
//        adapter = ArrayAdapter.createFromResource(this,
//                R.array.attribute_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        attributeSpinner.setAdapter(adapter);
//
//        adapter = ArrayAdapter.createFromResource(this,
//                R.array.grade_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        gradeSpinner.setAdapter(adapter);
//
//        adapter = ArrayAdapter.createFromResource(this,
//                R.array.sub_team_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        subTeamSpinner.setAdapter(adapter);
//
//        adapter = ArrayAdapter.createFromResource(this,
//                R.array.skill_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        skillTypeSpinner.setAdapter(adapter);
    }
}
