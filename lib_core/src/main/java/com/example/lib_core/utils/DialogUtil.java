package com.example.lib_core.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil extends ProgressDialog {
    public DialogUtil(Context context) {
        super(context);
    }

    public DialogUtil(Context context, int theme) {
        super(context, theme);
    }
}
