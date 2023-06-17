package org.jeecg.modules.demo.orders.service.impl;

import org.jeecg.modules.demo.orders.entity.UserOrders;
import org.jeecg.modules.demo.orders.mapper.UserOrdersMapper;
import org.jeecg.modules.demo.orders.service.IUserOrdersService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 所有用户订单管理
 * @Author: jeecg-boot
 * @Date:   2023-06-12
 * @Version: V1.0
 */
@Service
public class UserOrdersServiceImpl extends ServiceImpl<UserOrdersMapper, UserOrders> implements IUserOrdersService {

}
