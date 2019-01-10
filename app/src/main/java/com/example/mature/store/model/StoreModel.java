package com.example.mature.store.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.mature.store.api.StoreApi;
import com.example.mature.store.bean.UserEntity;
import com.example.mature.store.contract.StoreContract;
import com.example.mature.store.net.OkhttpCallback;
import com.example.mature.store.net.OkhttpUtils;
import com.example.mature.store.net.RegCallback;
import com.example.mature.store.net.ResultCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class StoreModel implements StoreContract.IStoreModel {
    private Handler handler=new Handler();
    @Override
    public void success(HashMap<String, String> params, final ResultCallback callback) {
        OkhttpUtils.getInstance().doPost(StoreApi.LOGIN_API, params, new OkhttpCallback() {
            @Override
            public void success(String result) {
                if (!TextUtils.isEmpty(result)){
                    paserReulst(callback,result);
                }
            }
            @Override
            public void failure(final String error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       callback.failure("网络异常");
                    }
                });
            }
        });
    }
    @Override
    public void reg(HashMap<String, String> params, final RegCallback callback) {
        OkhttpUtils.getInstance().doPost(StoreApi.REGISTER_API, params, new OkhttpCallback() {
            @Override
            public void success(String result) {
                if (callback!=null){
                    callback.success(result);
                }
            }
            @Override
            public void failure(String error) {
                if (callback!=null){
                    callback.failure(error);
                }
            }
        });
    }
    private void paserReulst(final ResultCallback callback, String result) {
        final UserEntity userEntity=new Gson().fromJson(result,UserEntity.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.success(userEntity);
            }
        });
    }
}
