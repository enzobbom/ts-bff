package com.javanauta.ts.bff.controller;

import com.javanauta.ts.bff.business.UserService;
import com.javanauta.ts.bff.business.dto.in.AddressDTORequest;
import com.javanauta.ts.bff.business.dto.in.PhoneDTORequest;
import com.javanauta.ts.bff.business.dto.in.UserDTORequest;
import com.javanauta.ts.bff.business.dto.in.UserLoginRequestDTO;
import com.javanauta.ts.bff.business.dto.out.AddressDTOResponse;
import com.javanauta.ts.bff.business.dto.out.PhoneDTOResponse;
import com.javanauta.ts.bff.business.dto.out.UserDTOResponse;
import com.javanauta.ts.bff.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "user", description = "Creation, login, update and deletion of Users")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create user", description = "Creates a new user")
    @ApiResponse(responseCode = "200", description = "User successfully created")
    @ApiResponse(responseCode = "400", description = "User already registered")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTOResponse> saveUser(@RequestBody UserDTORequest userDTORequest) {
        return ResponseEntity.ok(userService.saveUser(userDTORequest));
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Logs an existing user in")
    @ApiResponse(responseCode = "200", description = "User successfully logged in")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Server error")
    public String login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        return userService.loginUser(userLoginRequestDTO);
    }

    @GetMapping
    @Operation(summary = "Get user by email", description = "Gets the data of an user identified by their email")
    @ApiResponse(responseCode = "200", description = "User data successfully found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTOResponse> getUserByEmail(@RequestParam("email") String email, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.getUserByEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user", description = "Deletes an user identified by their email")
    @ApiResponse(responseCode = "200", description = "User successfully deleted")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email, @RequestHeader(name = "Authorization", required = false) String token) {
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Update user", description = "Updates an user identified by their email")
    @ApiResponse(responseCode = "200", description = "User successfully updated")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTOResponse> updateUser(@RequestBody UserDTORequest userDTORequest, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateUser(token, userDTORequest));
    }

    @PutMapping("/address")
    @Operation(summary = "Update user address", description = "Updates an user's address identified by its ID")
    @ApiResponse(responseCode = "200", description = "User's address successfully updated")
    @ApiResponse(responseCode = "404", description = "Address not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<AddressDTOResponse> updateAddress(@RequestBody AddressDTORequest addressDTORequest, @RequestParam("id") Long id, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updateAddress(id, addressDTORequest, token));
    }

    @PutMapping("/phone")
    @Operation(summary = "Update user phone", description = "Updates an user's phone identified by its ID")
    @ApiResponse(responseCode = "200", description = "User's phone successfully updated")
    @ApiResponse(responseCode = "404", description = "Phone not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<PhoneDTOResponse> updatePhone(@RequestBody PhoneDTORequest phoneDTORequest, @RequestParam("id") Long id, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.updatePhone(id, phoneDTORequest, token));
    }

    @PostMapping("/address")
    @Operation(summary = "Add address", description = "Adds a new address to an user identified by their email")
    @ApiResponse(responseCode = "200", description = "New address successfully saved")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<AddressDTOResponse> addAddress(@RequestBody AddressDTORequest addressDTORequest, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.addAddress(token, addressDTORequest));
    }

    @PostMapping("/phone")
    @Operation(summary = "Add phone", description = "Adds a new phone to an user identified by their email")
    @ApiResponse(responseCode = "200", description = "New phone successfully saved")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<PhoneDTOResponse> addPhone(@RequestBody PhoneDTORequest phoneDTORequest, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.addPhone(token, phoneDTORequest));
    }
}
