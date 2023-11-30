package com.springboot.todo.vo;

public class ResultDto {
    int resultCode;
    String message;

    public ResultDto() {
    }

    public ResultDto(int result, String message) {
        this.resultCode = result;
        this.message = message;
    }

    public int getResult() {
        return resultCode;
    }

    public void setResult(int result) {
        this.resultCode = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
