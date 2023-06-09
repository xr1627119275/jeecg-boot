package org.jeecg.modules.demo.orders.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.orders.entity.Orders;
import org.jeecg.modules.demo.orders.entity.Users;
import org.jeecg.modules.demo.orders.mapper.OrdersMapper;
import org.jeecg.modules.demo.orders.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description: orders
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
    @Autowired
    OrdersMapper ordersMapper;
    @Override
    public Users queryUserByID(String userId) {

        return  ordersMapper.queryUserByID(userId);
    }
    @Override
    public IPage<Users> getUsersList(Page page, QueryWrapper<Users> queryWrapper, String userId) {
        return ordersMapper.getUsersList(page, queryWrapper, userId);
    }


}
