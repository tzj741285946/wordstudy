package net.android.workStudy.widegt;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void show(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
