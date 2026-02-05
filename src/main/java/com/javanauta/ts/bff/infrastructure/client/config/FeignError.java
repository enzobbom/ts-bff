package com.javanauta.ts.bff.infrastructure.client.config;

import com.javanauta.ts.bff.infrastructure.exception.*;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.warn("Feign call exception: {} returned status {}", methodKey, response.status());

        String errMsg = null;
        if (response.body() != null) {
            try (InputStream is = response.body().asInputStream()) {
                 errMsg = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                log.warn("Reading Feign response body failed... Generating local exception with default message instead", e);
            }
        }

        return switch (response.status()) {
            case 400 -> new BadRequestException(errMsg != null ? errMsg : "Malformed request");
            case 401 -> new UnauthorizedException(errMsg != null ? errMsg : "Request not authorized");
            case 404 -> new ResourceNotFoundException(errMsg != null ? errMsg : "Resource not found");
            case 409 -> new ConflictException(errMsg != null ? errMsg : "Attribute already exists");
            case 422 -> new ValidationErrorException(errMsg != null ? errMsg : "Invalid values");
            default -> new BusinessException(errMsg != null ? errMsg : "Internal server error");
        };
    }
}
