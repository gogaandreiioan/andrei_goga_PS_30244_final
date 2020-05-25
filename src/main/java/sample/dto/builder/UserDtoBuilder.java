package sample.dto.builder;

import sample.dto.UserDto;

public class UserDtoBuilder {

    private UserDto userDto;

    public UserDtoBuilder() {
        this.userDto = new UserDto();
    }

    public UserDtoBuilder setUsername(String username){
        userDto.setUsername(username);
        return this;
    }


    public UserDtoBuilder setPassword(String password){
        userDto.setPassword(password);
        return this;
    }


    public UserDtoBuilder setEmail(String email){
        userDto.setEmail(email);
        return this;
    }

    public UserDtoBuilder setRole(String role){
        userDto.setRole(role);
        return this;
    }

    public UserDto build(){
        return userDto;
    }
}
