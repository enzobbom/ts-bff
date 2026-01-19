package com.javanauta.ts.bff.infrastructure.client;

import com.javanauta.ts.bff.business.dto.AddressDTO;
import com.javanauta.ts.bff.business.dto.PhoneDTO;
import com.javanauta.ts.bff.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @PostMapping
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    @PostMapping("/login")
    String login(@RequestBody UserDTO userDTO);

    @GetMapping
    UserDTO getUserByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping
    UserDTO updateUser(@RequestBody UserDTO userDTO, @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTO updateAddress(@RequestBody AddressDTO addressDTO, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PutMapping("/phone")
    PhoneDTO updatePhone(@RequestBody PhoneDTO phoneDTO, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PostMapping("/address")
    AddressDTO addAddress(@RequestBody AddressDTO addressDTO, @RequestHeader("Authorization") String token);

    @PostMapping("/phone")
    PhoneDTO addPhone(@RequestBody PhoneDTO phoneDTO, @RequestHeader("Authorization") String token);
}
