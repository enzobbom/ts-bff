package com.javanauta.ts.bff.business.dto.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTORequest {

    private String street;
    private Long number;
    private String complement;
    private String city;
    private String neighbourhood;
    private String state;
    private String cep;
}
