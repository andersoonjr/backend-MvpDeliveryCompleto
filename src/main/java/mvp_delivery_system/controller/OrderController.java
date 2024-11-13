package mvp_delivery_system.controller;

import mvp_delivery_system.entites.Order;
import mvp_delivery_system.enums.OrderStatus;
import mvp_delivery_system.models.request.NewOrderRequest;
import mvp_delivery_system.models.response.OrderResponse;
import mvp_delivery_system.services.OrderItemService;
import mvp_delivery_system.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders() {
        List<Order> listOrder = orderService.findAllOrders();
        List<OrderResponse> orderResponse = OrderResponse.listaOrderParaOrderResponse(listOrder);
        return ResponseEntity.ok().body(orderResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable Long id) {
        Order obj = orderService.findOrderById(id);
        OrderResponse orderResponse = new OrderResponse(obj);
        return ResponseEntity.ok().body(orderResponse);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.saveOrder(order);
        OrderResponse orderResponse = new OrderResponse(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order updateOrder = orderService.updateOrder(id, orderDetails);
        OrderResponse orderResponse = new OrderResponse(updateOrder);
        return ResponseEntity.ok().body(orderResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/criarPedido")
    public ResponseEntity<OrderResponse> criarPedido(@RequestBody NewOrderRequest newOrderRequest) {
        Order objOrder = orderService.createNewOrder(newOrderRequest);
        OrderResponse orderResponse = new OrderResponse(objOrder);
        return ResponseEntity.ok().body(orderResponse);
    }

    @PutMapping(value = "/trocarStatusParaPronto/{id}")
    public ResponseEntity<OrderResponse> trocarStatusParaPronto(@PathVariable Long id) {
        Order statusOrder = orderService.trocarStatusPedidoParaPronto(id);
        OrderResponse orderResponse = new OrderResponse(statusOrder);
        return ResponseEntity.ok().body(orderResponse);
    }
    @PutMapping(value = "/trocarStatusParaEnviando/{id}")
    public ResponseEntity<OrderResponse> trocarStatusParaEnviando(@PathVariable Long id) {
        Order statusOrder = orderService.trocarStatusPedidoParaEnviando(id);
        OrderResponse orderResponse = new OrderResponse(statusOrder);
        return ResponseEntity.ok().body(orderResponse);


    }
    @PutMapping(value= "/pedidoEntregue/{id}")
    public ResponseEntity<OrderResponse> pedidoEntregue(@PathVariable Long id) {
        Order statusOrder =  orderService.pedidoRecebido(id);
        OrderResponse orderResponse = new OrderResponse(statusOrder);
        return ResponseEntity.ok().body(orderResponse);
    }

    @GetMapping(value= "/listaPedidosPreparando")
    public ResponseEntity<List<OrderResponse>> listaPedidosPreparando() {
        List<OrderResponse> orderResponses = orderService.listaPedidoPorStatus(OrderStatus.PREPARANDO);
        return ResponseEntity.ok().body(orderResponses);
    }

    @GetMapping(value = "/listaPedidosPronto")
    public ResponseEntity<List<OrderResponse>> listaPedidosPronto() {
        List<OrderResponse> orderResponses = orderService.listaPedidoPorStatus(OrderStatus.PRONTO);
        return ResponseEntity.ok().body(orderResponses);
    }

    @GetMapping(value = "/listaPedidosEnviando")
    public ResponseEntity<List<OrderResponse>> listaPedidosEnviando() {
        List<OrderResponse> orderResponses = orderService.listaPedidoPorStatus(OrderStatus.ENVIANDO);
        return ResponseEntity.ok().body(orderResponses);
    }


}
