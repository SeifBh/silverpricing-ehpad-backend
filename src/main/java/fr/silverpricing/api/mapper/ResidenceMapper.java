package fr.silverpricing.api.mapper;

import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface ResidenceMapper {

    UserDto toResidenceDto(Residence residence);
}