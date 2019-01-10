package com.example.lib_core.net;

public interface OkhttpCallback {
    void failure(String error);
    void success(String result);
}
