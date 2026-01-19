package com.javanauta.ts.bff.business.dto.out;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDTOResponse {

    private Long id;
    private String countryCode;
    private String areaCode;
    private String number;
}
