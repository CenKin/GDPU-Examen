package cn.examen.domain.commons;

import java.util.Date;

public class Result {
    private String stateCode;
    private String msg;
    private Date timestamp;
    private Exception exception;

    public Result(){
        super();
    }

    public Result(String stateCode, String msg, Date timestamp, Exception exception) {
        this.stateCode = stateCode;
        this.msg = msg;
        this.timestamp = timestamp;
        this.exception = exception;
    }

    public Result(String stateCode, String msg, Date timestamp) {
        this.stateCode = stateCode;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
