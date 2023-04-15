package com.mycompany.paginacpf.model;

public class ResponseDTO {
    private int status;
    private Object body;

    public ResponseDTO(int status, Object body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResponseDto{" + "status=" + status + ", body=" + body + '}';
    }

}
