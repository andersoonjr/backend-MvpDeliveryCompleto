package mvp_delivery_system.models.response;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import mvp_delivery_system.entites.Dish;
import mvp_delivery_system.entites.Order;
import mvp_delivery_system.entites.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderItemResponse {
    private Long idOrdemItem;
    private Integer quantity;
    private DishResponse dishResponse;


    public OrderItemResponse(OrderItem orderItem) {
        this.idOrdemItem = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.dishResponse = new DishResponse(orderItem.getDish());

    }

    public static List<OrderItemResponse> listOrderItemParaListResponse (List<OrderItem> listOrderItem) {
        List<OrderItemResponse> orderItemResponseList = new ArrayList<>();
        for (OrderItem orderItem : listOrderItem) {
            orderItemResponseList.add(new OrderItemResponse(orderItem));
        }
        return orderItemResponseList;
    }
}
