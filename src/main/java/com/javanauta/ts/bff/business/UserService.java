package com.javanauta.ts.bff.business;

import com.javanauta.ts.bff.business.dto.AddressDTO;
import com.javanauta.ts.bff.business.dto.PhoneDTO;
import com.javanauta.ts.bff.business.dto.UserDTO;
import com.javanauta.ts.bff.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserDTO saveUser(UserDTO userDTO) {
        return userClient.saveUser(userDTO);
    }

    public String loginUser(UserDTO userDTO) {
        return userClient.login(userDTO);
    }

    public UserDTO getUserByEmail(String email, String token) {
        return userClient.getUserByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token) {
        userClient.deleteUserByEmail(email, token);
    }

    public UserDTO updateUser(String token, UserDTO userDTO) {
        return userClient.updateUser(userDTO, token);
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO, String token) {
        return userClient.updateAddress(addressDTO, id, token);
    }

    public PhoneDTO updatePhone(Long id, PhoneDTO phoneDTO, String token) {
        return userClient.updatePhone(phoneDTO, id, token);
    }

    public AddressDTO addAddress(String token, AddressDTO addressDTO) {
        return userClient.addAddress(addressDTO, token);
    }

    public PhoneDTO addPhone(String token, PhoneDTO phoneDTO) {
        return userClient.addPhone(phoneDTO, token);
    }
}
