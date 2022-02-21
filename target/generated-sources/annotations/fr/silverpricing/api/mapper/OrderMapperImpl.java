package fr.silverpricing.api.mapper;

import fr.silverpricing.api.model.Order;
import fr.silverpricing.api.model.User;
import fr.silverpricing.api.rest.dto.CreateOrderRequest;
import fr.silverpricing.api.rest.dto.OrderDto;
import fr.silverpricing.api.rest.dto.OrderDto.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-21T22:25:51+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(CreateOrderRequest createOrderRequest) {
        if ( createOrderRequest == null ) {
            return null;
        }

        Order order = new Order();

        order.setDescription( createOrderRequest.getDescription() );

        return order;
    }

    @Override
    public OrderDto toOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setCreatedAt( order.getCreatedAt() );
        orderDto.setId( order.getId() );
        orderDto.setDescription( order.getDescription() );
        orderDto.setUser( userToUserDto( order.getUser() ) );

        return orderDto;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUsername( user.getUsername() );

        return userDto;
    }
}
