package com.buyahi.afya_app.network;

import com.buyahi.afya_app.network.YelpApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.buyahi.afya_app.Constants.YELP_API_KEY;
import static com.buyahi.afya_app.Constants.YELP_BASE_URL;

public class YelpClient {
    private static Retrofit retrofit = null;

    public static YelpApi getClient() {

        if (retrofit == null) {
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

                    //.addInterceptor(new Interceptor() {
            Interceptor interceptor1 = new  Interceptor(){

                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", YELP_API_KEY)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    };
            HttpLoggingInterceptor interceptor =new HttpLoggingInterceptor();

            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(interceptor1).addInterceptor(interceptor);


            retrofit = new Retrofit.Builder()
                    .baseUrl(YELP_BASE_URL)
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(YelpApi.class);
    }
}
