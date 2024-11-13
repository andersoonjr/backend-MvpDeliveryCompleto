package mvp_delivery_system.services;


import mvp_delivery_system.entites.Order;
import mvp_delivery_system.entites.OrderItem;
import mvp_delivery_system.entites.User;
import mvp_delivery_system.enums.OrderStatus;
import mvp_delivery_system.models.request.NewOrderItemRequest;
import mvp_delivery_system.models.request.NewOrderRequest;
import mvp_delivery_system.models.response.OrderResponse;
import mvp_delivery_system.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final DishService dishService;

    public OrderService(OrderRepository OrderRepository, UserService userService, OrderItemService orderItemService, DishService dishService) {
        this.orderRepository = OrderRepository;
        this.userService = userService;
        this.orderItemService = orderItemService;
        this.dishService = dishService;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();

    }

    public Order findOrderById(Long id) {
        Optional<Order> obj = orderRepository.findById(id);
        return obj.get();
    }
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order newOrder) {
        Order oldOrder = findOrderById(id);
        oldOrder.setOrderItems(newOrder.getOrderItems());
        oldOrder.setClient(newOrder.getClient());
        oldOrder.setTotalPrice(newOrder.getTotalPrice());
        oldOrder.setOrderstatus(newOrder.getOrderstatus());
        oldOrder.setDataHora(newOrder.getDataHora());
        return orderRepository.save(oldOrder);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    //////////////////////////////////////////



    public Order createNewOrder(NewOrderRequest newOrderRequest) {

        Order order = new Order();

        User user = new User();

        user.setName(newOrderRequest.getClient().getName());
        user.setPhone(newOrderRequest.getClient().getPhone());
        user.setAddress(newOrderRequest.getClient().getAddress());

        userService.createUser(user);

        order.setClient(user);

        List<OrderItem> orderItems = new ArrayList<>();

        for (NewOrderItemRequest orderItemRequest : newOrderRequest.getOrderItems()) {

           OrderItem orderItem =  orderItemService.adicionarProdutoAoCarrinho(orderItemRequest.getDishId(), orderItemRequest.getQuantity() );

           orderItems.add(orderItem);

        }

        order.setOrderItems(orderItems);

        order.setOrderstatus(OrderStatus.PREPARANDO);
        order.setTotalPrice(orderItemService.calculateTotalPrice(orderItems));
        order.setDataHora(LocalDateTime.now());

        order.setPagamento(newOrderRequest.getPagamento());

        Order savedOrder = orderRepository.save(order);

        for (OrderItem orderItem : savedOrder.getOrderItems()) {

            orderItem.setOrder(savedOrder);

            orderItemService.saveOrderItem(orderItem);
        }
        return savedOrder;

    }


    public Order trocarStatusPedidoParaPronto (Long idOrder) {

        Order order = findOrderById(idOrder);
        order.setOrderstatus(OrderStatus.PRONTO);

        return orderRepository.save(order);
    }

    public Order trocarStatusPedidoParaEnviando (Long idOrder)
    {
        Order order = findOrderById(idOrder);
        order.setOrderstatus(OrderStatus.ENVIANDO);

        
        return orderRepository.save(order);
    }

    public Order pedidoRecebido(Long idOrder) {
        Order order = findOrderById(idOrder);
        order.setOrderstatus(OrderStatus.ENTREGUE);

        return orderRepository.save(order);
    }

    public List<OrderResponse> listaPedidoPorStatus(OrderStatus orderStatus) {

        List<Order> orders = orderRepository.findByOrderstatus(orderStatus);

        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            if (order.getOrderstatus() == orderStatus) {
                orderResponses.add(new OrderResponse(order));
            }
        }
        return orderResponses;
    }
}
