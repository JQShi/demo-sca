package org.ben.model.common;

import lombok.Data;

@Data
public class R {

    private int code;
    private String msg;
    private Object data;

    public static R ok() {
        R r = new R();
        r.code = 200;
        r.msg = "OK";
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.code = 200;
        r.msg = "OK";
        r.data = data;
        return r;
    }

    public static R error() {
        R r = new R();
        r.code = 500;
        return r;
    }

    public static R error(int code , String msg ) {
        R r = new R();
        r.code = code;
        r.msg = msg;
        return r;
    }

}
