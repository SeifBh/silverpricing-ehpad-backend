package fr.silverpricing.api.mapper;

import fr.silverpricing.api.model.Residence;
import fr.silverpricing.api.rest.dto.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-26T12:15:01+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class ResidenceMapperImpl implements ResidenceMapper {

    @Override
    public UserDto toResidenceDto(Residence residence) {
        if ( residence == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( residence.getId() );

        return userDto;
    }
}
