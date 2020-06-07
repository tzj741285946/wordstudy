package net.android.workStudy.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import net.android.workStudy.db.AnswerTable;
import net.android.workStudy.db.ExamTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader {
    public static List<ExamTable> getJson(Context context, String fileName) {
        ArrayList<ExamTable> list = new ArrayList<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONObject  object  = new JSONObject(baos.toString());
            JSONArray array = object.getJSONArray("message");
            Gson gson = new Gson();//创建Gson对象
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonElements = jsonParser.parse(array.toString()).getAsJsonArray();//获取JsonArray对象
            for (JsonElement bean : jsonElements) {
                ExamTable bean1 = gson.fromJson(bean, ExamTable.class);//解析
                list.add(bean1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
