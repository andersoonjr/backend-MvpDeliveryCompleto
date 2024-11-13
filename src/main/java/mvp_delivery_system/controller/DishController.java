package mvp_delivery_system.controller;


import mvp_delivery_system.entites.Dish;
import mvp_delivery_system.models.request.NewDishRequest;
import mvp_delivery_system.models.response.DishResponse;
import mvp_delivery_system.repositories.DishRepository;
import mvp_delivery_system.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/dishes")
@CrossOrigin(origins = "http://localhost:4200")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private DishRepository dishRepository;

    @GetMapping
    public ResponseEntity<List<DishResponse>> findAllDishes() {
        List<Dish> list = dishService.findAllDishes();
        List<DishResponse> dishResponseList = DishResponse.listDishParaListResponse(list);
        return ResponseEntity.ok().body(dishResponseList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DishResponse> findDishById(@PathVariable Long id) {
        Dish obj = dishService.findDishById(id);
        DishResponse dishResponse = new DishResponse(obj);
        return ResponseEntity.ok().body(dishResponse);
    }

    @PostMapping
    public ResponseEntity<DishResponse> createDish(@RequestBody NewDishRequest newDishRequest) {
        Dish objDish = dishService.createDish(newDishRequest);
        DishResponse dishResponse = new DishResponse(objDish);
        return ResponseEntity.ok().body(dishResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DishResponse> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        Dish objDish = dishService.updateDish(id, dish);
        DishResponse dishResponse = new DishResponse(objDish);
        return ResponseEntity.ok().body(dishResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value= "/updateAvailabilityOfDish/{id}")
    public ResponseEntity updateAvailabilityOfDish(@PathVariable Long id) {
        Dish objDish = dishService.findDishById(id);
        dishService.dishDeleteAvailability(objDish.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAllValidDishes" )
    public ResponseEntity<List<DishResponse>> getAllValidDishes () {
        List<DishResponse> dishResponseList = dishService.getAllValidDishes() ;
        return ResponseEntity.ok().body(dishResponseList);
    }


}
