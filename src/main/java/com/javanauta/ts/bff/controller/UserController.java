package com.javanauta.ts.bff.controller;

import com.javanauta.ts.bff.business.UserService;
import com.javanauta.ts.bff.business.dto.AddressDTO;
import com.javanauta.ts.bff.business.dto.PhoneDTO;
import com.javanauta.ts.bff.business.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "user", description = "User creation and login")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Save user", description = "Saves a new user")
    @ApiResponse(responseCode = "200", description = "User successfully saved")
    @ApiResponse(responseCode = "400", description = "User already registered")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Logs an existing user in")
    @ApiResponse(responseCode = "200", description = "User successfully logged in")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Server error")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.loginUser(userDTO);
    }

    @GetMapping
    @Operation(summary = "Get user by email", description = "Gets the data of an user identified by their email")
    @ApiResponse(responseCode = "200", description = "User data successfully found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.getUserByEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user", description = "Deletes an user identified by their email")
    @ApiResponse(responseCode = "200", description = "User successfully deleted")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token) {
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Update user", description = "Updates an user identified by their email")
    @ApiResponse(responseCode = "200", description = "User successfully updated")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updateUser(token, userDTO));
    }

    @PutMapping("/address")
    @Operation(summary = "Update user address", description = "Updates an user's address identified by its ID")
    @ApiResponse(responseCode = "200", description = "User's address successfully updated")
    @ApiResponse(responseCode = "404", description = "Address not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO, @RequestParam("id") Long id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updateAddress(id, addressDTO, token));
    }

    @PutMapping("/phone")
    @Operation(summary = "Update user phone", description = "Updates an user's phone identified by its ID")
    @ApiResponse(responseCode = "200", description = "User's phone successfully updated")
    @ApiResponse(responseCode = "404", description = "Phone not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<PhoneDTO> updatePhone(@RequestBody PhoneDTO phoneDTO, @RequestParam("id") Long id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updatePhone(id, phoneDTO, token));
    }

    @PostMapping("/address")
    @Operation(summary = "Add address", description = "Adds a new address to an user identified by their email")
    @ApiResponse(responseCode = "200", description = "New address successfully saved")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.addAddress(token, addressDTO));
    }

    @PostMapping("/phone")
    @Operation(summary = "Add phone", description = "Adds a new phone to an user identified by their email")
    @ApiResponse(responseCode = "200", description = "New phone successfully saved")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<PhoneDTO> addPhone(@RequestBody PhoneDTO phoneDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.addPhone(token, phoneDTO));
    }
}
