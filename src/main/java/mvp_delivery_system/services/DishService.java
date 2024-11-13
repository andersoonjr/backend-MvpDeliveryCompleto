package mvp_delivery_system.services;


import mvp_delivery_system.entites.Dish;
import mvp_delivery_system.models.request.NewDishRequest;
import mvp_delivery_system.models.response.DishResponse;
import mvp_delivery_system.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private final DishRepository dishRepository;

    public DishService(DishRepository DishRepository) {
        this.dishRepository = DishRepository;
    }

    public List<Dish> findAllDishes() {
        return dishRepository.findAll();
    }

    public Dish findDishById(Long id) {
        Optional<Dish> obj = dishRepository.findById(id);

        return obj.get();
    }

    public Dish createDish(NewDishRequest newDishRequest) {
        Dish newDish = new Dish();
        newDish.setName(newDishRequest.getName());
        newDish.setDescription(newDishRequest.getDescription());
        newDish.setPrice(newDishRequest.getPrice());
        newDish.setImage(newDishRequest.getImage());

        newDish.setAvailability(true);
        return dishRepository.save(newDish) ;
    }

    public Dish updateDish(Long id, Dish dishNovo) {
        Dish dish = findDishById(id);
        dish.setName(dishNovo.getName());
        dish.setDescription(dishNovo.getDescription());
        dish.setPrice(dishNovo.getPrice());
        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }


    public void dishDeleteAvailability (Long id) {
        Dish dish = findDishById(id);

        dish.setAvailability(false);

        dishRepository.save(dish);

    }

    public List<DishResponse> getAllValidDishes () {
        List<Dish> dishes = dishRepository.findAllByAvailability(true);

        List<DishResponse> dishResponse = DishResponse.listDishParaListResponse(dishes);

        return dishResponse;
    }
    
}
