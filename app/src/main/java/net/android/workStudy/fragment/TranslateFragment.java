package net.android.workStudy.fragment;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import net.android.workStudy.R;
import net.android.workStudy.http.HttpUtil;
import net.android.workStudy.utils.JinshanParseUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static net.android.workStudy.utils.JinshanParseUtil.parseJinshanChineseToEnglishXMLWithPull;

/**
 * CreateDate: 2019/11/1
 * ClassName: TranslateFragment
 * Description: 翻译
 * Version:
 */
public class TranslateFragment extends Fragment {
    private final static String TAG = "translate";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_translate)
    EditText etTranslate;
    @BindView(R.id.btn_translate)
    Button btnTranslate;
    @BindView(R.id.query)
    TextView query;
    @BindView(R.id.base_mean)
    TextView baseMean;
    @BindView(R.id.related_examples)
    TextView examples;
    @BindView(R.id.en_voice)
    TextView enVoiceLab;
    @BindView(R.id.iv_en_voice)
    ImageView enVoiceImg;
    @BindView(R.id.en_voice_text)
    TextView enVoiceText;
    @BindView(R.id.am_voice)
    TextView amVoiceLab;
    @BindView(R.id.iv_am_voice)
    ImageView amVoiceImg;
    @BindView(R.id.am_voice_text)
    TextView amVoiceText;

    private Unbinder bind;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        bind = ButterKnife.bind(this, view);
        initView();
        initData();
        initListener();
        return view;
    }


    private void initView() {
    }

    private void initData() {

    }


    private void initListener() {
        btnTranslate.setOnClickListener(v -> {
            try {
                String word = etTranslate.getText().toString();       //查询文本

                //金山每日一词网址，默认json，使用中
                //String url = "http://open.iciba.com/dsapi/?date=2018-03-09";
                //金山每日一词网址，可选xml，file=xml&  未使用
                //String url = "http://open.iciba.com/dsapi/?file=xml&date=2018-03-10";

                //金山查词网址，默认xml，使用中
                final String urlxml = "http://dict-co.iciba.com/api/dictionary.php?w=" + word + "&key=833D9ACFEDD4FA6C2162CD7FEFBC49EB";
                //金山查词网址，可选json，&type=json  ，因为缺少例句，未使用
                String url = "http://dict-co.iciba.com/api/dictionary.php?w=" + word + "&type=json&key=833D9ACFEDD4FA6C2162CD7FEFBC49EB";

                if (JinshanParseUtil.isEnglish(word)) {
                    HttpUtil.sendOkHttpRequest(urlxml, new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Toast.makeText(getActivity(), "获取翻译数据失败！", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {

                            final String result = response.body().string();
                            Log.d(TAG, result);

                            getActivity().runOnUiThread(new Runnable() {
                                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                @Override
                                public void run() {

                                    JinshanParseUtil.parseJinshanEnglishToChineseXMLWithPull(result);

                                    SharedPreferences pref = getActivity().getSharedPreferences("workBean", MODE_PRIVATE);

                                    String queryText = pref.getString("queryText", "空");
                                    final String voiceEnText = pref.getString("voiceEnText", "空");
                                    final String voiceEnUrlText = pref.getString("voiceEnUrlText", "空");
                                    String voiceAmText = pref.getString("voiceAmText", "空");
                                    final String voiceAmUrlText = pref.getString("voiceAmUrlText", "空");
                                    String meanText = pref.getString("meanText", "空");
                                    String exampleText = pref.getString("exampleText", "空");


                                    enVoiceLab.setText("英式发音：");
                                    amVoiceLab.setVisibility(View.VISIBLE);
                                    amVoiceImg.setVisibility(View.VISIBLE);
                                    amVoiceText.setVisibility(View.VISIBLE);

                                    query.setText(queryText);
                                    enVoiceText.setText(voiceEnText);
                                    amVoiceText.setText(voiceAmText);

                                    baseMean.setText(meanText);
                                    examples.setText(exampleText);

                                    enVoiceImg.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            try {
                                                MediaPlayer mediaPlayer;
                                                mediaPlayer = MediaPlayer.create(getActivity(), Uri.parse(voiceEnUrlText));
                                                mediaPlayer.start();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                    amVoiceImg.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            try {
                                                MediaPlayer mediaPlayer;
                                                mediaPlayer = MediaPlayer.create(getActivity(), Uri.parse(voiceAmUrlText));
                                                mediaPlayer.start();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });

                                }
                            });
                        }
                    });
                } else {
                    HttpUtil.sendOkHttpRequest(url, new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Toast.makeText(getActivity(), "获取翻译数据失败！", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {

                            final String result = response.body().string();
                            Log.d(TAG, result);

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    JinshanParseUtil.parseJinshanChineseToEnglishJSONWithGson(result);

                                    SharedPreferences pref = getActivity().getSharedPreferences("workBean", MODE_PRIVATE);

                                    String queryText = pref.getString("queryText", "空");
                                    String voiceText = pref.getString("voiceText", "空");
                                    final String voiceUrlText = pref.getString("voiceUrlText", "空");
                                    String meanText = pref.getString("meanText", "空");


                                    enVoiceLab.setText("拼音：");
                                    amVoiceLab.setVisibility(View.GONE);
                                    amVoiceImg.setVisibility(View.GONE);
                                    amVoiceText.setVisibility(View.GONE);

                                    query.setText(queryText);
                                    enVoiceText.setText(voiceText);
                                    baseMean.setText(meanText);

                                    enVoiceImg.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {
                                                MediaPlayer mediaPlayer;
                                                mediaPlayer = MediaPlayer.create(getActivity(), Uri.parse(voiceUrlText));
                                                mediaPlayer.start();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                }
                            });
                        }
                    });

                    HttpUtil.sendOkHttpRequest(urlxml, new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Toast.makeText(getActivity(), "获取翻译数据失败！", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            final String result = response.body().string();

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String example = parseJinshanChineseToEnglishXMLWithPull(result);

                                    examples.setText(example);
                                }
                            });

                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }
}
