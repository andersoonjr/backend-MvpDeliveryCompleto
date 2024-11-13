package mvp_delivery_system.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "tb_product")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    @Lob
    @Column(columnDefinition = "longtext")
    private String image;
    private Boolean availability;

    @OneToMany(mappedBy = "dish")
    private List<OrderItem> orderItem;


}
