package com.zld.openclient.base;

import com.zld.openclient.common.NetConst;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lingdong on 2017/12/6.
 */

public class RetrofitFactory {
    private static final String TAG = RetrofitFactory.class.getSimpleName();

    private static final java.lang.Object Object = new Object();

    private volatile static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        synchronized (Object) {
            if (retrofit == null) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .addInterceptor(loggingInterceptor)
//                        .addInterceptor(mHeaderInterceptor)
                        .retryOnConnectionFailure(true);

                retrofit = new Retrofit.Builder()
                        .baseUrl(NetConst.getBaseUrl())
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
}
