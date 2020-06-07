package net.android.workStudy.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Created: AriesHoo on 2017-03-14 08:54
 * Function: app使用工具类
 * Desc:
 */
public class AppUtil {

    public static void jumpMarket(Context mContext) {
        jumpMarket(mContext, null);
    }

    public static void jumpMarket(Context mContext, String packageName) {
        if (mContext == null) {
            return;
        }
        if (packageName == null || packageName.isEmpty()) {
            packageName = mContext.getPackageName();
        }
        String mAddress = "market://details?id=" + packageName;
        try {
            Intent marketIntent = new Intent("android.intent.action.VIEW");
            marketIntent.setData(Uri.parse(mAddress));
            mContext.startActivity(marketIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startBorwer(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    /**
     * @param activity
     * @param bundle
     */
    public static void startActivity(AppCompatActivity mContext, Class<? extends AppCompatActivity> activity, Bundle bundle) {
        if (mContext == null) {
            return;
        }
        Intent intent = new Intent(mContext, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }

    public static void startActivity(AppCompatActivity mContext, Class<? extends AppCompatActivity> activity) {
        startActivity(mContext, activity, null);
    }

    public static void shareShareText(AppCompatActivity mActivity, String url) {
        if (mActivity == null) {
            return;
        }
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        shareIntent.setType("text/plain");
        //设置分享列表的标题，并且每次都显示分享列表
        mActivity.startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

}
