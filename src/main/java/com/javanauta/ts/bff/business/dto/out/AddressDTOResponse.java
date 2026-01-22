package com.javanauta.ts.bff.business.dto.out;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTOResponse {

    private Long id;
    private String street;
    private Long number;
    private String complement;
    private String city;
    private String state;
    private String zipCode;
}
