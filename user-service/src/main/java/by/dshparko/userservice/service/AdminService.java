package by.dshparko.userservice.service;

import by.dshparko.userservice.database.entity.Role;
import by.dshparko.userservice.database.entity.User;
import by.dshparko.userservice.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<String> getAdminEmails() {
        return userRepository.findUsersByRole(Role.ADMIN)
                .stream()
                .map(User::getEmail)
                .toList();
    }
}
