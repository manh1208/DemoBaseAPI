package com.tiny.rush;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/********************************************************
 * File Name : CommonRest.java
 * Author : ManhNV
 * Date : 2017-09-06
 * Description: 
 * Last-modified by : ManhNV
 * Last-modified : 2017-09-06
 ********************************************************/

public interface ICommonRest {


    @POST
    Call<Object> add(@Url String url, @Body Object t);

    @PUT
    Call<Object> update(@Url String url, @Body Object t);

    @GET
    Call<Object> getAll(@Url String url);

    @GET
    Call<Object> getById(@Url String url);

    @GET
    Call<Object> getBy(@Url String url, @QueryMap Map<String, String> queries);

    @DELETE
    Call<Object> delete(@Url String url);


}
