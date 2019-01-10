package com.example.mature.store.net;

import com.example.mature.store.bean.UserEntity;

public interface ResultCallback {
    void success(UserEntity userEntity);
    void failure(String error);
}
