package net.android.workStudy.mvp.presenter;



import net.android.workStudy.base.BasePresenter;
import net.android.workStudy.base.BaseView;
import net.android.workStudy.mvp.bean.WorkBook;
import net.android.workStudy.http.CommonSubscriber;
import net.android.workStudy.http.RxUtils;
import net.android.workStudy.mvp.contract.TranslateContract;
import net.android.workStudy.mvp.model.TranslateModel;
import net.android.workStudy.utils.TransApi;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 只专注于数据层与视图层的交互部分，通过接口方式通知view
 */

public class TranslatePresenter extends BasePresenter<BaseView> implements TranslateContract.IPresenter {
    private TranslateModel iModel;
    /**
     * 进行初始化操作（初始化model）
     */
    @Override
    public void onCreate() {
        iModel = new TranslateModel();
    }



    @Override
    public void translate(String q) {
        TransApi api = new TransApi("20191030000348832", "WN9wJgHLMCLoUeHRjZrS");
        Map<String, String> transResult = api.buildParams(q, "auto", "en");

        addSubscribe(iModel.translate(transResult)
                .compose(RxUtils.applyFSchedulers())
                .compose(RxUtils.handleResult())
                .subscribeWith(new CommonSubscriber<WorkBook>() {
                    @Override
                    public void onSuccess(WorkBook bean) {
                        mView.success(1,bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.error();
                    }
                }));
    }

    /**
     * 32位MD5加密
     * @param content -- 待加密内容
     * @return
     */
    public String md5Decode32(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
