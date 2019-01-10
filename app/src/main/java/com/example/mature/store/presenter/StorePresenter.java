package com.example.mature.store.presenter;

import com.example.mature.store.bean.UserEntity;
import com.example.mature.store.contract.StoreContract;
import com.example.mature.store.model.StoreModel;
import com.example.mature.store.net.RegCallback;
import com.example.mature.store.net.ResultCallback;

import java.util.HashMap;

public class StorePresenter extends StoreContract.getStore {
    private StoreModel storeModel;
    private StoreContract.IStoreView view;
    public StorePresenter(StoreContract.IStoreView view) {
        this.view = view;
        this.storeModel=new StoreModel();
    }
    @Override
    public void getLogin(HashMap<String, String> params) {
        storeModel.success(params, new ResultCallback() {
            @Override
            public void success(UserEntity userEntity) {
                view.loginsuccess(userEntity);
            }
            @Override
            public void failure(String error) {
                view.loginfailure(error);
            }
        });
    }
    @Override
    public void getregister(HashMap<String, String> params) {
        if (storeModel!=null){
            storeModel.reg(params, new RegCallback() {
                @Override
                public void success(String result) {
                    if (view != null) {
                        view.regsuccess(result);
                    }
                }
                @Override
                public void failure(String error) {
                    if (view!=null){
                        view.failure(error);
                    }
                }
            });
        }
    }
    public void destory(){
        if (view!=null){
            view=null;
        }
    }
}
