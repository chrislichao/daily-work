package org.chrisli.activiti.exception;

import org.chrisli.activiti.view.ResponseBaseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseBaseVo exceptionHandler(Exception e) {
        logger.error("捕获到未知异常", e);
        return ResponseBaseVo.fail("Unknow Error", e.getMessage());
    }
}