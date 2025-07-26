package by.dshparko.userservice.http.rest;

import by.dshparko.userservice.dto.UserDto;
import by.dshparko.userservice.service.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@Data
public class AdminController {
    private final UserService service;

    @GetMapping
    public List<UserDto> findAll() {
        return service.findAllUsers();
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Long id,
                          @RequestBody UserDto dto) {
        return service.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
