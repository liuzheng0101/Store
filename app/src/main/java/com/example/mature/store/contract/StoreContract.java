package com.example.mature.store.contract;

import com.example.mature.store.bean.UserEntity;
import com.example.mature.store.net.RegCallback;
import com.example.mature.store.net.ResultCallback;

import java.util.HashMap;

public interface StoreContract {
    public abstract class getStore{
        public abstract void getLogin(HashMap<String,String> params);
        public abstract void getregister(HashMap<String,String> params);
    }
    public interface IStoreModel{
        void success(HashMap<String,String> params, ResultCallback callback);
        void reg(HashMap<String,String> params,RegCallback callback);
    }
    public interface IStoreView{
        void loginsuccess(UserEntity userEntity);
        void loginfailure(String error);
        void regsuccess(String result);
        void failure(String error);
    }
}
