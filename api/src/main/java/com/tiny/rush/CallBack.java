package com.tiny.rush;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/********************************************************
 * File Name : BaseCallBack.java
 * Author : ManhNV
 * Date : 2017-09-10
 * Description: 
 * Last-modified by : ManhNV
 * Last-modified : 2017-09-10
 ********************************************************/


public abstract class CallBack<T> implements Callback {
    public T body;

    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call
     * @param response
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                onSuccess((T) response.body());
            }
        } else {
            onError(response.message(), response.errorBody());
        }
    }

    public abstract void onSuccess(T body);

    public abstract void onError(String message, ResponseBody errorBody);

    public abstract void onFailure(Throwable t);

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call call, Throwable t) {
        onFailure(t);
    }
}
