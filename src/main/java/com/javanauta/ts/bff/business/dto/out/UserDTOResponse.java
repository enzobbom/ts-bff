package com.javanauta.ts.bff.business.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTOResponse {

    private String name;
    private String email;
    private String password;
    private List<AddressDTOResponse> addresses;
    private List<PhoneDTOResponse> phones;
}
