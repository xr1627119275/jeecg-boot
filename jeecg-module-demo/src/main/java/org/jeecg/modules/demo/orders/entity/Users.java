package org.jeecg.modules.demo.orders.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: orders
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="订单所有用户信息", description="订单所有用户信息")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private String userId;

	/**订单金额*/
	@Excel(name = "订单总金额", width = 15)
    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalOrderAmount;
	/**退款金额*/
	@Excel(name = "退款总金额", width = 15)
    @ApiModelProperty(value = "退款金额")
    private BigDecimal totalRefundAmount;

}
