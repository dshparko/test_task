package by.dshparko.userservice.http.rest;

import by.dshparko.userservice.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "Operations to change your account details: receiving, updating and deleting")
public class UserController extends BaseUserController {

    public UserController(UserService service) {
        super(service);
    }
}
