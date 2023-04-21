package com.hniesep.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;

/**
 * @author 吉铭炼
 */
public class ServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3583566093089790852L;
    private static final Logger log = LoggerFactory.getLogger(ServiceException.class);

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }

    public ServiceException(Exception cause) {
        super(cause);
        if (log.isDebugEnabled()) {
            log.debug(cause.getMessage());
        }
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }
}