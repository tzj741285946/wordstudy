package net.android.workStudy.http;


import net.android.workStudy.mvp.bean.WorkBook;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;


public interface BaseHttpService {

    /**
     * get 请求，参数以map的形式传入(也可直接传值)
     *
     * @param map
     * @return
     */
    @GET("translate")
    Flowable<WorkBook> translate(@QueryMap Map<String, String> map);

    /**
     * 多文件以及参数上传
     *
     * @param file
     * @param menuFile
     * @param userId
     * @param remark
     * @param lat
     * @param lng
     * @param saleId
     * @param deliveryAreaId
     * @return
     */
    @POST(ApiUrl.ARTICLE_LIST)
    @Multipart
    Observable<String> addMultiFile(@Part MultipartBody.Part[] file, //多文件
                                    @Part MultipartBody.Part[] menuFile,//多文件
                                    @Part("userId") RequestBody userId,
                                    @Part("remark") RequestBody remark,
                                    @Part("lat") RequestBody lat,
                                    @Part("lng") RequestBody lng,
                                    @Part("saleId") RequestBody saleId,
                                    @Part("deliveryAreaId") RequestBody deliveryAreaId);
}
