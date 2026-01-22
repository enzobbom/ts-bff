package com.javanauta.ts.bff.business;

import com.javanauta.ts.bff.business.dto.out.TaskDTOResponse;
import com.javanauta.ts.bff.infrastructure.client.NotifierClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifierService {

    private final NotifierClient notifierClient;

    public void sendEmail(TaskDTOResponse taskDTOResponse) {
        notifierClient.sendEmail(taskDTOResponse);
    }
}
