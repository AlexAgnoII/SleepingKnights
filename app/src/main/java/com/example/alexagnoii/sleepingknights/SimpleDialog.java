package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by jessganoww on 11/14/17.
 */

public class SimpleDialog extends DialogFragment {

    private int layout;

    public SimpleDialog(int layout) {
        this.layout = layout;
    }


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        return builder.create();
    }
}
