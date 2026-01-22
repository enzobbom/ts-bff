package com.javanauta.ts.bff.infrastructure.client.config;

import com.javanauta.ts.bff.infrastructure.exception.BusinessException;
import com.javanauta.ts.bff.infrastructure.exception.ConflictException;
import com.javanauta.ts.bff.infrastructure.exception.ResourceNotFoundException;
import com.javanauta.ts.bff.infrastructure.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 401 -> new UnauthorizedException("Error: request was not authorized");
            case 404 -> new ResourceNotFoundException("Error: resource not found");
            case 409 -> new ConflictException("Error: attribute already exists");
            default -> new BusinessException("Internal server error");
        };
    }
}
