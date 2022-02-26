package fr.silverpricing.api.mapper;

import fr.silverpricing.api.model.Order;
import fr.silverpricing.api.model.User;
import fr.silverpricing.api.rest.dto.UserDto;
import fr.silverpricing.api.rest.dto.UserDto.OrderDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-26T12:15:01+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );
        userDto.setRole( user.getRole() );
        userDto.setOrders( orderListToOrderDtoList( user.getOrders() ) );

        return userDto;
    }

    protected OrderDto orderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( order.getId() );
        orderDto.setDescription( order.getDescription() );
        orderDto.setCreatedAt( order.getCreatedAt() );

        return orderDto;
    }

    protected List<OrderDto> orderListToOrderDtoList(List<Order> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDto> list1 = new ArrayList<OrderDto>( list.size() );
        for ( Order order : list ) {
            list1.add( orderToOrderDto( order ) );
        }

        return list1;
    }
}
