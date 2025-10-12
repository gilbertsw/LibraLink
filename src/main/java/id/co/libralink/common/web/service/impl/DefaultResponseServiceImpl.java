package id.co.libralink.common.web.service.impl;

import id.co.libralink.common.base.code.SuccessCode;
import id.co.libralink.common.message.MessageSourceService;
import id.co.libralink.common.web.model.DefaultResponse;
import id.co.libralink.common.web.service.DefaultResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultResponseServiceImpl implements DefaultResponseService {

    private final MessageSourceService messageSourceService;

    @Override
    public <T> DefaultResponse<T> build(HttpStatus status, T data) {
        return new DefaultResponse<>(status, data);
    }

    @Override
    public <T> DefaultResponse<T> build(HttpStatus status, String defaultMessage, T data) {
        return new DefaultResponse<>(status, defaultMessage, data);
    }

    @Override
    public <T> DefaultResponse<T> build(HttpStatus status, SuccessCode code) {
        String message = messageSourceService.getLocalizedMessage(code.getKey());
        return new DefaultResponse<>(status, code, message);
    }

    @Override
    public <T> DefaultResponse<T> build(HttpStatus status, SuccessCode code, T data) {
        String message = messageSourceService.getLocalizedMessage(code.getKey());
        return new DefaultResponse<>(status, code, message, data);
    }

    @Override
    public <T> DefaultResponse<T> build(HttpStatus status, SuccessCode code, Object[] args, T data) {
        String message = messageSourceService.getLocalizedMessage(code.getKey(), args);
        return new DefaultResponse<>(status, code, message, data);
    }

}
