package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 处理超时订单
     */
    @Scheduled(cron = "0 * * * * ? ")
    //@Scheduled(cron = "0/5 * * * * ? ")
    public void processTimeoutOrder(){
        log.info("定时处理超时订单:{}",new Date());
        List<Orders> list = orderMapper.getByStatusAndOrdertimeLT(Orders.PENDING_PAYMENT,  LocalDateTime.now().plusMinutes(-15));
        if(list!= null && list.size() > 0){

                list.forEach(order -> {
                    order.setStatus(Orders.CANCELLED);
                    order.setCancelReason("支付超时，自动取消");
                    order.setCancelTime(LocalDateTime.now());
                    orderMapper.update(order);
                });
        }


    }




    /**
     * 处理“派送中”状态的订单
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    //@Scheduled(cron = "0/5 * * * * ?")
    public void processDeliveryOrder(){
        log.info("处理派送中订单：{}", new Date());
        List<Orders> list = orderMapper.getByStatusAndOrdertimeLT(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().plusMinutes(-60));
        if(list!= null && !list.isEmpty()){
            list.forEach(order -> {
                order.setStatus(Orders.COMPLETED);
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            });
        }
    }
}
