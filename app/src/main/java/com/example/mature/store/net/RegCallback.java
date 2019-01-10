package com.example.mature.store.net;

import com.example.mature.store.bean.UserEntity;

public interface RegCallback {
    void success(String result);
    void failure(String error);
}
