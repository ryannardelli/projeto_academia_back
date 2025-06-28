package com.projeto_academia_back.controllers;


import com.projeto_academia_back.dtos.UpdateUserDto;
import com.projeto_academia_back.models.Users;
import com.projeto_academia_back.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manageusers")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/all")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable  Long id) {
        return usersService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        usersService.deleteUserById(id);
    }


    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody UpdateUserDto dto) {
        return usersService.updateUser(id, dto);
    }

}
