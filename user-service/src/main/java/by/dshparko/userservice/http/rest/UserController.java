package by.dshparko.userservice.http.rest;

import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/user")
@Data
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public UserDto userById(@PathVariable("id") Long id) throws AccessDeniedException {
        return service.getUser(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Long id,
                          @RequestBody @Valid UserDto dto) throws JsonProcessingException, AccessDeniedException {
        return service.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws JsonProcessingException, AccessDeniedException {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
