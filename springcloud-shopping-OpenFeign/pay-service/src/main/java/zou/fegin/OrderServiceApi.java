package zou.fegin;

import com.zou.service.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author WH
 * @version 1.0
 * @date 2020/9/13 10:15
 * @Description TODO
 */
@FeignClient(name = "order-service",url = "127.0.0.1:8080")
public interface OrderServiceApi extends OrderService {
}
