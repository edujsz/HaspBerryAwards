package com.eduardo.raspberryawards.exception.ExceptionHandler;

import com.eduardo.raspberryawards.controller.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHanlder {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ErrorInfo
    handleException(HttpServletRequest req, Exception ex){
        return  new ErrorInfo(ex.getLocalizedMessage(), req.getRequestURL().toString());
    }
}
