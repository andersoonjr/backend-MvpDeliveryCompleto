package mvp_delivery_system.services;

import mvp_delivery_system.entites.OrderItem;
import mvp_delivery_system.entites.Dish;
import mvp_delivery_system.repositories.OrderItemRepository;
import mvp_delivery_system.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final DishRepository dishRepository;

    public OrderItemService(OrderItemRepository OrderItemRepository, DishRepository dishRepository) {
        this.orderItemRepository = OrderItemRepository;
        this.dishRepository = dishRepository;
    }

    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem findOrderItemById(Long id) {
        Optional<OrderItem> obj = orderItemRepository.findById(id);
        return obj.get();
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItem orderItemNovo) {
        OrderItem orderItem = findOrderItemById(id);
        orderItem.setQuantity(orderItemNovo.getQuantity());
        orderItem.setDish(orderItemNovo.getDish());
        orderItem.setOrder(orderItemNovo.getOrder());
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    //.................Meus métodos.....................//





    public OrderItem adicionarProdutoAoCarrinho(Long productId, Integer quantity) {

        Dish dish = dishRepository.findById(productId).get();

        OrderItem orderItemNovo = new OrderItem();
        orderItemNovo.setDish(dish);
        orderItemNovo.setQuantity(quantity);


        return orderItemRepository.save(orderItemNovo);

    }

    public double calculateTotalPrice(List<OrderItem> items) {
        double total = 15.0;
        for (OrderItem item : items) {
            total += item.getDish().getPrice() * item.getQuantity();
        }
        return total;
    }


}
