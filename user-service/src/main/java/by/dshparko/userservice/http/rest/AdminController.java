package by.dshparko.userservice.http.rest;

import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/admin/users")
@Data
@Tag(name = "Admin Controller", description = "Operations to change accounts details: receiving, creating, updating and deleting")
public class AdminController {
    private final UserService service;

    @Operation(summary = "Get a list of all users", description = "Returns a collection of users with HATEOAS links")
    @ApiResponse(responseCode = "200", description = "User list successfully returned")
    @GetMapping
    public List<UserDto> findAll() {
        return service.findAllUsers();
    }

    @Operation(summary = "Get user by ID", description = "Returns information about user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User was found"),
            @ApiResponse(responseCode = "403", description = "Access is denied")
    })
    @GetMapping("/{id}")
    public UserDto userById(@PathVariable("id") Long id) throws AccessDeniedException {
        return service.getUser(id);
    }

    @Operation(summary = "Create a new user", description = "Accepts DTO and creates user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User was created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody
                                              @Valid UserDto dto) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(dto));
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
