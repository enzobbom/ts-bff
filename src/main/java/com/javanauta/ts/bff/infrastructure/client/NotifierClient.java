package com.javanauta.ts.bff.infrastructure.client;

import com.javanauta.ts.bff.business.dto.out.TaskDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notifier", url = "${notifier.url}")
public interface NotifierClient {

    @PostMapping
    void sendEmail(@RequestBody TaskDTOResponse taskDTOResponse);
}
