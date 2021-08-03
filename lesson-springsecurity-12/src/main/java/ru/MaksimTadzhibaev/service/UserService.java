package ru.MaksimTadzhibaev.service;

import org.springframework.data.domain.Page;
import ru.MaksimTadzhibaev.controller.UserDto;
import ru.MaksimTadzhibaev.controller.UserListParams;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto user);

    void deleteById(Long id);
}
