package com.example.dayglow.global;

import lombok.Getter;

@Getter
public class Response {

    private boolean success;
    private Object data;

    public Response(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static Response success() {
        return new Response(true, null);
    }

    public static Response success(Object data) {
        return new Response(true, data);
    }
}
