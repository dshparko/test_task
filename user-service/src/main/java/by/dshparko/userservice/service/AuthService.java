package by.dshparko.userservice.service;

import by.dshparko.userservice.dto.AuthDto;
import by.dshparko.userservice.dto.AuthenticationResponse;
import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.security.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    public AuthenticationResponse login(AuthDto request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()
                )
        );
        var userDetails = userDetailsService.loadUserByUsername(request.username());
        var token = jwtUtils.generateToken(userDetails);
        return new AuthenticationResponse(token);
    }

    public UserDto register(UserDto request) throws JsonProcessingException {
        return userService.createUser(request);
    }
}
