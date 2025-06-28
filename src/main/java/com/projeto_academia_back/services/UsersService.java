package com.projeto_academia_back.services;


import com.projeto_academia_back.dtos.UpdateUserDto;
import com.projeto_academia_back.models.Users;
import com.projeto_academia_back.repositories.ProfileRepository;
import com.projeto_academia_back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Users updateUser(Long id, UpdateUserDto dto) {
        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        Users user = optionalUser.get();

        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }

        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }

        if (dto.getProfileConfigured() != null) {
            user.setProfileConfigured(dto.getProfileConfigured());
        }

        return userRepository.save(user);
    }
}