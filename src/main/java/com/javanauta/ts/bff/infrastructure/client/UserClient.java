package com.javanauta.ts.bff.infrastructure.client;

import com.javanauta.ts.bff.business.dto.in.AddressDTORequest;
import com.javanauta.ts.bff.business.dto.in.PhoneDTORequest;
import com.javanauta.ts.bff.business.dto.in.UserDTORequest;
import com.javanauta.ts.bff.business.dto.in.UserLoginRequestDTO;
import com.javanauta.ts.bff.business.dto.out.AddressDTOResponse;
import com.javanauta.ts.bff.business.dto.out.PhoneDTOResponse;
import com.javanauta.ts.bff.business.dto.out.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${ts.user.service.uri}")
public interface UserClient {

    @PostMapping
    UserDTOResponse saveUser(@RequestBody UserDTORequest userDTORequest);

    @PostMapping("/login")
    String login(@RequestBody UserLoginRequestDTO userLoginRequestDTO);

    @GetMapping
    UserDTOResponse getUserByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping
    UserDTOResponse updateUser(@RequestBody UserDTORequest userDTORequest, @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTOResponse updateAddress(@RequestBody AddressDTORequest addressDTORequest, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PutMapping("/phone")
    PhoneDTOResponse updatePhone(@RequestBody PhoneDTORequest phoneDTORequest, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PostMapping("/address")
    AddressDTOResponse addAddress(@RequestBody AddressDTORequest addressDTORequest, @RequestHeader("Authorization") String token);

    @PostMapping("/phone")
    PhoneDTOResponse addPhone(@RequestBody PhoneDTORequest phoneDTORequest, @RequestHeader("Authorization") String token);
}
