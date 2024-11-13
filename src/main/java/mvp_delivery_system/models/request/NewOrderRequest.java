package mvp_delivery_system.models.request;

import lombok.Getter;
import lombok.Setter;
import mvp_delivery_system.enums.Pagamentos;

import java.util.List;

@Getter
@Setter
public class NewOrderRequest {

    private List<NewOrderItemRequest> orderItems;

    private NewUserRequest client;

    private Pagamentos pagamento;



}
