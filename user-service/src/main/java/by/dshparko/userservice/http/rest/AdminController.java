package by.dshparko.userservice.http.rest;

import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/admin/users")
@Tag(name = "Admin Controller", description = "Operations to change accounts details: receiving, creating, updating and deleting")
public class AdminController extends BaseUserController {

    private final UserService service;

    public AdminController(UserService service) {
        super(service);
        this.service = service;
    }

    @Operation(summary = "Get a list of all users", description = "Returns a collection of users with HATEOAS links")
    @ApiResponse(responseCode = "200", description = "User list successfully returned")
    @GetMapping
    public List<UserDto> findAll() {
        return service.findAllUsers();
    }

}
