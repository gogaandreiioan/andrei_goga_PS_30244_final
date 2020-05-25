package sample.service.user;

import sample.dto.UserDto;
import sample.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
    User findByUsernameInternal(String username);
    boolean create(UserDto userDto);
    boolean deleteByUsername(UserDto userDto);
    boolean update(UserDto userDto, String newUsername);
}
