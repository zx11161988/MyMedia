package com.example.jingxiangwu.medialist.data;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by jingxiang wu on 2016/8/15.
 */
public class OkHttp {
    private OkHttpClient mOkHttpClient;
    private RequestListener mRequestListener;
    private Request mRequest;

    public static interface RequestListener {
        public void finish(boolean successful, String data);
    }

    public OkHttp() {
        //创建okHttpClient对象
        mOkHttpClient = new OkHttpClient();
    }

    public void setListener(RequestListener listener) {
        mRequestListener = listener;
    }

    public void createRequest() {

        //创建一个Request
        mRequest = new Request.Builder()
                .url("http://192.168.0.106:8080/restful/product/list").build();
        //new call
        Call call = mOkHttpClient.newCall(mRequest);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mRequestListener.finish(false, null);
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                //String htmlStr =  response.body().string();
                mRequestListener.finish(true, response.body().string());
            }
        });
    }

    public void resumeRequest() {

    }

    public void addData(HashMap<String, String> map) {
        //创建一个Request
        String uri = "http://192.168.0.103:8080/restful/product/";

        RequestBody formBody = new FormEncodingBuilder()
                .add(MediaData.NAME, map.get(MediaData.NAME))
                .add(MediaData.DESCRIPTION, map.get(MediaData.DESCRIPTION))
                .add(MediaData.PRICE, map.get(MediaData.PRICE))
                .add(MediaData.DATA, map.get(MediaData.DATA))
                .build();
        mRequest = new Request.Builder()
                .url(uri).post(formBody)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(mRequest);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //mRequestListener.finish(false, null);
                Log.d("wjx"," onFailure");
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Log.d("wjx"," onResponse");
                //String htmlStr =  response.body().string();
                //mRequestListener.finish(true, response.body().string());
            }
        });
    }
    public void pauseRequest() {
        if (mOkHttpClient != null) {
            mOkHttpClient.cancel(mRequest);
        }
    }
}
