package mvp_delivery_system.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserRequest {
    private String name;
    private String phone;
    private String address;
}
