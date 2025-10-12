package id.co.libralink.common.base.controller;

import id.co.libralink.common.base.code.ErrorCode;
import id.co.libralink.common.enums.CommonErrorCode;
import id.co.libralink.common.web.service.ErrorResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

public class BaseErrorHandler {

    @Autowired
    protected ErrorResponseService errorResponseService;

    protected ErrorCode getIntegrityViolationCode(DataIntegrityViolationException ex) {
        final String DUPLICATE = "Duplicate entry", USED = "Cannot delete or update";
        return Optional.ofNullable(ex.getRootCause())
                .map(Throwable::getMessage)
                .map(message -> {
                    if (message.contains(DUPLICATE)) {
                        return CommonErrorCode.DATA_ALREADY_EXIST;
                    } else if (message.contains(USED)) {
                        return CommonErrorCode.DATA_IS_USED;
                    }
                    return CommonErrorCode.DATA_REFERENCE_EXCEPTION;
                }).orElse(CommonErrorCode.DATA_REFERENCE_EXCEPTION);
    }

}
