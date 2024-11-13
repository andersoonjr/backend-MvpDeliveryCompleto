package mvp_delivery_system.controller;


import mvp_delivery_system.entites.Order;
import mvp_delivery_system.entites.OrderItem;
import mvp_delivery_system.models.response.OrderItemResponse;
import mvp_delivery_system.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/orderItems")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderItemController {

    @Autowired
    public OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> findAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.findAllOrderItems();
        List<OrderItemResponse> orderItemResponses = OrderItemResponse.listOrderItemParaListResponse(orderItems);
        return ResponseEntity.ok().body(orderItemResponses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItemResponse> findOrderItemById(@PathVariable Long id) {
        OrderItem obj = orderItemService.findOrderItemById(id);
        OrderItemResponse objResponse = new OrderItemResponse(obj);
        return ResponseEntity.ok().body(objResponse);
    }

    @PostMapping
    public ResponseEntity<OrderItemResponse> addOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem newOrderItem = orderItemService.saveOrderItem(orderItem);
        OrderItemResponse orderItemResponse = new OrderItemResponse(newOrderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderItemResponse> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        OrderItem updateOrderItem = orderItemService.updateOrderItem(id, orderItem);
        OrderItemResponse orderItemResponse = new OrderItemResponse(updateOrderItem);
        return ResponseEntity.ok().body(orderItemResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<OrderItem> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }



}
