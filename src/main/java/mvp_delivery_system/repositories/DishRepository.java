package mvp_delivery_system.repositories;

import mvp_delivery_system.entites.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findAllByAvailability(Boolean availability);
}
