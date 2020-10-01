package application.controller.api;

import application.data.model.Order;
import application.data.service.OrderService;
import application.model.api.DataApiResult;
import application.model.dto.OrderDTO;
import application.model.viewmodel.order.OrderVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/detail/{orderId}")
    public DataApiResult detail(@PathVariable int orderId){
        DataApiResult result=new DataApiResult();
        OrderDTO dto=new OrderDTO();

        try{
            Order order=orderService.findOne(orderId);

            dto.setOrderStatus(order.getStatus());
            result.setData(dto);
            result.setSuccess(true);
            result.setMessage("done");

        }catch (Exception e){
            result.setMessage("some error");
            result.setSuccess(false);
        }

        return result;

    }

    @PutMapping(value = "/update")
    public DataApiResult update(@RequestBody OrderDTO dto) {
        DataApiResult result = new DataApiResult();

        try {
            Order order = orderService.findOne(dto.getId());

            order.setStatus(dto.getOrderStatus());
            orderService.update(order);

            result.setMessage("successfully updated order");
            result.setData(order.getStatus());
            result.setSuccess(true);

        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }

        return result;
    }
}


