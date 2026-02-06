package com.javanauta.ts.bff.business;

import com.javanauta.ts.bff.business.dto.in.AddressDTORequest;
import com.javanauta.ts.bff.business.dto.in.PhoneDTORequest;
import com.javanauta.ts.bff.business.dto.in.UserDTORequest;
import com.javanauta.ts.bff.business.dto.in.UserLoginRequestDTO;
import com.javanauta.ts.bff.business.dto.out.AddressDTOResponse;
import com.javanauta.ts.bff.business.dto.out.PhoneDTOResponse;
import com.javanauta.ts.bff.business.dto.out.UserDTOResponse;
import com.javanauta.ts.bff.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserDTOResponse saveUser(UserDTORequest userDTORequest) {
        return userClient.saveUser(userDTORequest);
    }

    public String loginUser(UserLoginRequestDTO userLoginRequestDTO) {
        return userClient.login(userLoginRequestDTO);
    }

    public UserDTOResponse getUserByEmail(String email, String token) {
        return userClient.getUserByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token) {
        userClient.deleteUserByEmail(email, token);
    }

    public UserDTOResponse updateUser(String token, UserDTORequest userDTORequest) {
        return userClient.updateUser(userDTORequest, token);
    }

    public AddressDTOResponse updateAddress(Long id, AddressDTORequest addressDTORequest, String token) {
        return userClient.updateAddress(addressDTORequest, id, token);
    }

    public PhoneDTOResponse updatePhone(Long id, PhoneDTORequest phoneDTORequest, String token) {
        return userClient.updatePhone(phoneDTORequest, id, token);
    }

    public AddressDTOResponse addAddress(String token, AddressDTORequest addressDTORequest) {
        return userClient.addAddress(addressDTORequest, token);
    }

    public PhoneDTOResponse addPhone(String token, PhoneDTORequest phoneDTORequest) {
        return userClient.addPhone(phoneDTORequest, token);
    }
}
