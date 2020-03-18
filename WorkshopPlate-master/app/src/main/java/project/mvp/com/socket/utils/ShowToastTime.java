package project.mvp.com.socket.utils;

import android.widget.Toast;

import project.mvp.com.socket.application.MyApplication;

public class ShowToastTime {
    private static Toast toast = null;

    public static void showTextToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
