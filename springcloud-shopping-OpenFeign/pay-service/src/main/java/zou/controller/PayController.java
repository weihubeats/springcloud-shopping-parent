package zou.controller;

import com.zou.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WH
 * @version 1.0
 * @date 2020/9/12 23:11
 * @Description TODO
 */

public class PayController implements PayService{


    @Autowired
    PayService payService;

   @Override
    public boolean pay() {
        payService.pay();
       return true;

    }
}
