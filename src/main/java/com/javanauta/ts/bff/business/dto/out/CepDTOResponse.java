package com.javanauta.ts.bff.business.dto.out;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CepDTOResponse {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}
