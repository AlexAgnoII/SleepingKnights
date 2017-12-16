package com.example.alexagnoii.sleepingknights;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by jessganoww on 12/16/17.
 */

public class SettingsDialog extends Dialog {
    public SettingsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
