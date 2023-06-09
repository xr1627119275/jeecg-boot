package org.jeecg.modules.demo.orders.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.demo.orders.entity.Orders;
import org.jeecg.modules.demo.orders.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description: orders
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
public interface IOrdersService extends IService<Orders> {


    public Users queryUserByID(String userId);

    IPage<Users> getUsersList(Page page, QueryWrapper<Users> queryWrapper, String userId);
}
