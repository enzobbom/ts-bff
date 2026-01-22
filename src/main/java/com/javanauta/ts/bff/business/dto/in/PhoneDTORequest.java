package com.javanauta.ts.bff.business.dto.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDTORequest {

    private String countryCode;
    private String areaCode;
    private String number;
}
