package ru.geekbrains.march.market.auth.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.UserDto;
import ru.geekbrains.march.market.auth.entities.User;


@Component
public class UserConverter {

    public UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
