package by.dshparko.userservice.http.rest;

import by.dshparko.userservice.dto.AuthDto;
import by.dshparko.userservice.dto.AuthenticationResponse;
import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto request) throws JsonProcessingException {
        return ResponseEntity.ok(authService.register(request));
    }
}