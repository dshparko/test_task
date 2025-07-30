package by.dshparko.userservice.http.rest;

import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequiredArgsConstructor
public class BaseUserController {
    private final UserService service;

    @Operation(summary = "Get user by ID", description = "Returns information about user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was found"),
            @ApiResponse(responseCode = "403", description = "Access is denied")
    })
    @GetMapping("/{id}")
    public UserDto userById(@PathVariable("id") Long id) throws AccessDeniedException {
        return service.getUser(id);
    }

    @Operation(summary = "Refresh user", description = "Updates user's profile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was successfully updated"),
            @ApiResponse(responseCode = "403", description = "Access is denied")
    })
    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Long id,
                          @RequestBody @Valid UserDto dto) throws JsonProcessingException, AccessDeniedException {
        return service.updateUser(id, dto);
    }

    @Operation(summary = "Delete a user", description = "Removes user's profile'")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User was successfully removed"),
            @ApiResponse(responseCode = "403", description = "Access is denied")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws JsonProcessingException, AccessDeniedException {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
