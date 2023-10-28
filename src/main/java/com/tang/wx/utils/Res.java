package com.tang.wx.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Res extends HashMap<String, Object> {
    public Res() {
        this.put("code", HttpStatus.SC_OK);
        this.put("msg", "success");
        this.put("data", null);
    }

    public Res put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static Res ok() {
        return new Res();
    }

    public static Res ok(Object data) {
        return new Res().put("data", data);
    }

    public static Res ok(Map<String, Object> map) {
        Res r = new Res();
        r.putAll(map);
        return r;
    }

    public static Res error(int code, String msg) {
        Res r = new Res();
        r.put("msg", msg);
        r.put("code", code);
        return r;
    }

    public static Res error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知错误，请联系管理员");
    }

    public static Res error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }



}
