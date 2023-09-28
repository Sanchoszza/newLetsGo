package com.android.letsgo.utils;


import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.android.letsgo.dialog.WaitDialog;


public class WaitDialogUtils extends DialogFragment {
    private static WaitDialog waitDialog;

    public static void showDialog(FragmentManager fm) {
        waitDialog = WaitDialog.newInstance();
        waitDialog.setCancelable(true);
        waitDialog.show(fm, WaitDialog.TAG);
    }

    public static void dissmissDialog() {
        if(waitDialog != null){
            waitDialog.dismissAllowingStateLoss();
        }
    }
}

