package com.task.ergo.controller.dto;

import java.time.LocalDateTime;

public abstract class BaseResponse<T> {
    LocalDateTime timestamp = LocalDateTime.now();
    int status;
    String error;

    String path;

    T result;

    public BaseResponse() {}

    public BaseResponse(int status, String error, String path) {
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public BaseResponse(int status, String error, String path, T result) {
        this.status = status;
        this.error = error;
        this.path = path;
        this.result = result;
    }

    public BaseResponse(int status, String path, T result) {
        this.status = status;
        this.path = path;
        this.result = result;
    }

    public BaseResponse(int status, String path) {
        this.status = status;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
