package by.dshparko.userservice.http.rest;

import by.dshparko.userservice.dto.AuthDto;
import by.dshparko.userservice.dto.AuthenticationResponse;
import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "Operations to register and login")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Login users", description = "Returns ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was successfully created"),
            @ApiResponse(responseCode = "404", description = "User wasn't found")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "Create a new user", description = "Accepts DTO and creates user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User was created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto request) throws JsonProcessingException {
        return ResponseEntity.ok(authService.register(request));
    }
}