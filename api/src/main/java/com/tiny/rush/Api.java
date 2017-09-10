package com.tiny.rush;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/********************************************************
 * File Name : Api.java
 * Author : ManhNV
 * Date : 2017-09-06
 * Description: 
 * Last-modified by : ManhNV
 * Last-modified : 2017-09-06
 ********************************************************/


public class Api<T extends BaseModel, R extends BaseResponseModel> {
    public static final int GET = 1;
    public static final int POST = 2;
    public static final int PUT = 3;
    public static final int DELETE = 4;
    public static final int PATH = 5;
    private ICommonRest commonRest;
    private String endPoint;

    @SuppressWarnings("unchecked")
    public Api(Class clazz) {
        Validate.sdkInitialized();
        this.endPoint = clazz.getSimpleName().toLowerCase();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level logLv;
        switch (SeedAppSDK.getLogLevel()) {
            case SeedAppSDK.BODY:
                logLv = HttpLoggingInterceptor.Level.BODY;
                break;
            case SeedAppSDK.HEADER:
                logLv = HttpLoggingInterceptor.Level.HEADERS;
                break;
            case SeedAppSDK.NONE:
                logLv = HttpLoggingInterceptor.Level.NONE;
                break;
            default:
                logLv = HttpLoggingInterceptor.Level.BASIC;

        }
        logging.setLevel(logLv);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SeedAppSDK.getUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        commonRest = retrofit.create(ICommonRest.class);
    }

    @SuppressWarnings("unchecked")
    public void add(T entity, CallBack<R> callBack) {
        String url = this.endPoint;
        commonRest.add(url, entity).enqueue(callBack);
    }

    @SuppressWarnings("unchecked")
    public void getById(int id, CallBack<R> callBack) {
        String url = this.endPoint + "/" + id;
        commonRest.getById(url).enqueue(callBack);
    }

    @SuppressWarnings("unchecked")
    public void getBy(Map<String, String> queries, CallBack<R> callBack) {
        String url = this.endPoint + "s/";
        commonRest.getBy(url, queries).enqueue(callBack);
    }

    @SuppressWarnings("unchecked")
    public void update(T entity, CallBack<R> callBack) {
        String url = this.endPoint;
        commonRest.update(url, entity).enqueue(callBack);
    }

    @SuppressWarnings("unchecked")
    public void delete(int id, CallBack<R> callBack) {
        String url = this.endPoint + "/" + id;
        commonRest.delete(url).enqueue(callBack);
    }

    @SuppressWarnings("unchecked")
    public void getAll(CallBack<R> callBack) {
        String url = this.endPoint + "s/";
        commonRest.delete(url).enqueue(callBack);
    }


    public static class Builder<R extends BaseResponseModel> {
        private String url;
        private Map<String, String> queries;
        private Map<String, String> headers;
        private Map<String, String> fields;
        private Map<String, RequestBody> parts;
        private int method;

        public Builder() {
        }

        private Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        private Builder method(int method) {
            this.method = method;
            return this;
        }

        public Builder putQuery(String key, String value) {
            if (queries == null) {
                queries = new HashMap<>();
            }
            queries.put(key, value);
            return this;
        }

        public Builder putHeader(String key, String value) {
            if (headers == null) {
                headers = new HashMap<>();
            }
            queries.put(key, value);
            return this;
        }


        public Builder putField(String key, String value) {
            if (fields == null) {
                fields = new HashMap<>();
            }
            fields.put(key, value);
            return this;
        }

        public Builder putPart(String key, RequestBody responseBody) {
            if (parts == null) {
                parts = new HashMap<>();
            }
            parts.put(key, responseBody);
            return this;
        }

        public void enqueue(CallBack<R> callBack) {

        }

    }


}
