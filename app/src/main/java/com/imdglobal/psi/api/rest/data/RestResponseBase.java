package com.imdglobal.psi.api.rest.data;

/**
 * Created by rizkyriadhy on 19/06/17.
 */
public class RestResponseBase {

    public String message;
    public int code ;

    public RestResponseBase() {
    }

    public RestResponseBase(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
