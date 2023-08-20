package com.z.exception;


import com.z.bean.enums.IResponseCode;

public class ServiceException extends RuntimeException {

    private String errorCode;

    public ServiceException() {
    }

    public ServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(IResponseCode codeEnum){
        super(codeEnum.getDesc());
        this.errorCode = codeEnum.getCode();
    }

    public ServiceException(Throwable e,String code){
        super(e);
        this.errorCode = code;
    }

    public String getErrorCode(){
        return errorCode;
    }

}
