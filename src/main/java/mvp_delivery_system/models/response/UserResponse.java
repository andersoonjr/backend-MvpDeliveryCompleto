package mvp_delivery_system.models.response;

import lombok.Getter;
import lombok.Setter;
import mvp_delivery_system.entites.User;


@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String phone;
    private String address;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
    }

}
