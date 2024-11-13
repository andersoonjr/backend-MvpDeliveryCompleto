package mvp_delivery_system.models.response;

import lombok.Getter;
import lombok.Setter;
import mvp_delivery_system.entites.Dish;
import mvp_delivery_system.entites.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DishResponse {
    private Long dishId;
    private String name;
    private String description;
    private Double price;
    private String image;
    private Boolean availability;


    public DishResponse (Dish dish) {
        this.dishId = dish.getId();
        this.name = dish.getName();
        this.description = dish.getDescription();
        this.price = dish.getPrice();
        this.image = dish.getImage();
        this.availability = dish.getAvailability();

    }
    public static List<DishResponse> listDishParaListResponse (List<Dish> listDish) {
        List<DishResponse> dishResponseList = new ArrayList<>();
        for (Dish dish : listDish) {
           dishResponseList.add(new DishResponse(dish));
        }
        return dishResponseList;
    }
}

