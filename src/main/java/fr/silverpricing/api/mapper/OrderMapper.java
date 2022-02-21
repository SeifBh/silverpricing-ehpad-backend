package fr.silverpricing.api.mapper;

import fr.silverpricing.api.model.Order;
import fr.silverpricing.api.rest.dto.CreateOrderRequest;
import fr.silverpricing.api.rest.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(CreateOrderRequest createOrderRequest);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    OrderDto toOrderDto(Order order);
}