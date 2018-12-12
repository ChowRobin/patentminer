package org.patentminer.bean;


import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    public static final int NO_LOGIN = -1;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    public static final int UNKNOWN_EXCEPTION = -99;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    private HttpServletResponse response;

    public ResultBean() {
        super();
    }

    public ResultBean(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResultBean(T data, HttpServletResponse response) {
        super();
        this.data = data;
        this.response = response;
    }

    public ResultBean(HttpServletResponse response) {
        this.response = response;
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }

    public
    ResultBean setHttpStatus(int status) {
        if (this.response != null) {
            System.out.println("update http status");
            this.response.setStatus(status);
        }
        return this;
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getFAIL() {
        return FAIL;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public ResultBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultBean setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultBean setData(T data) {
        this.data = data;
        return this;
    }

    public static int getNoPermission() {
        return NO_PERMISSION;
    }
}
