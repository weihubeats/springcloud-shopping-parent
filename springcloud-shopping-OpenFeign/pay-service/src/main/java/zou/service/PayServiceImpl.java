package zou.service;

import com.zou.model.Order;
import com.zou.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zou.fegin.OrderServiceApi;

/**
 * @author WH
 * @version 1.0
 * @date 2020/9/12 22:54
 * @Description TODO
 */
@Service
public class PayServiceImpl implements PayService {


    @Autowired
    private OrderServiceApi orderServiceApi;



    @Override
    public boolean pay() {
        Order order = orderServiceApi.getOrder("123456");
        System.out.println("获取到订单号" + order.getOrderNo());
        return true;
    }
}
