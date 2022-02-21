package fr.silverpricing.api.mapper;

import fr.silverpricing.api.model.User;
import fr.silverpricing.api.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
}