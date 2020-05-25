package sample.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sample.dto.UserDto;
import sample.dto.builder.UserDtoBuilder;
import sample.entity.User;
import sample.entity.builder.UserBuilder;
import sample.mapper.Mapper;
import sample.mapper.UserMapper;
import sample.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private Mapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new UserMapper();
    }

    @Override
    public User findByUsernameInternal(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null)
            return user;
        else return null;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: users) {
            userDtoList.add((UserDto)mapper.mapFrom(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null)
            return (UserDto)mapper.mapFrom(user);
        else return null;    }

    @Override
    public UserDto findByEmail(String email){
        User user = userRepository.findByEmail(email);
        if (user != null)
          return (UserDto)mapper.mapFrom(user);
        else return null;
    }

    @Override
    public boolean create(UserDto userDto) {
        User user = (User)mapper.mapTo(userDto);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteByUsername(UserDto userDto) {
        userRepository.deleteByUsername(userDto.getUsername());
        return true;
    }

    @Override
    public boolean update(UserDto userDto, String newUsername) {
        userRepository.updateUsername(newUsername, userDto.getEmail());
        return true;
    }
}
