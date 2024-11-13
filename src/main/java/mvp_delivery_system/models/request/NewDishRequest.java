package mvp_delivery_system.models.request;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewDishRequest {
    private String name;
    private String description;
    private Double price;
    private String image;
    private Boolean availability;
}
