package org.jeecg.modules.demo.orders.mapper;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.orders.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.demo.orders.entity.Users;

import java.util.List;

/**
 * @Description: orders
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    /**
     * 查询用户已授权字段
     * @param userId
     * @return
     */
    public Users queryUserByID(@Param("userId") String userId);

//    public List<Users> getUsersList();

    IPage<Users> getUsersList(Page page, @Param(Constants.WRAPPER) Wrapper<Users> queryWrapper, String userId);



}
