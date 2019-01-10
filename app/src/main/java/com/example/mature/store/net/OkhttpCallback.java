package com.example.mature.store.net;

public interface OkhttpCallback {
    void success(String result);
    void failure (String error);
}
