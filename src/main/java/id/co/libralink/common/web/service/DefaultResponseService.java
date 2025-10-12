package id.co.libralink.common.web.service;

import id.co.libralink.common.base.code.SuccessCode;
import id.co.libralink.common.web.model.DefaultResponse;
import org.springframework.http.HttpStatus;

public interface DefaultResponseService {

    <T> DefaultResponse<T> build(HttpStatus status, T data);

    <T> DefaultResponse<T> build(HttpStatus status, String defaultMessage, T data);

    <T> DefaultResponse<T> build(HttpStatus status, SuccessCode code);

    <T> DefaultResponse<T> build(HttpStatus status, SuccessCode code, T data);

    <T> DefaultResponse<T> build(HttpStatus status, SuccessCode code, Object[] args, T data);

}
