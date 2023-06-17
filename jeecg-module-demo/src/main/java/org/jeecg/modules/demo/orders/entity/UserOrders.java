package org.jeecg.modules.demo.orders.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 所有用户订单管理
 * @Author: jeecg-boot
 * @Date:   2023-06-12
 * @Version: V1.0
 */
@Data
@TableName("user_orders")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="user_orders对象", description="所有用户订单管理")
public class UserOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Integer id;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private String userId;
	/**总订单金额*/
	@Excel(name = "总订单金额", width = 15)
    @ApiModelProperty(value = "总订单金额")
    private BigDecimal totalOrderAmount;
	/**总退货金额*/
	@Excel(name = "总退货金额", width = 15)
    @ApiModelProperty(value = "总退货金额")
    private BigDecimal totalRefundAmount;
	/**备注说明*/
	@Excel(name = "备注说明", width = 15)
    @ApiModelProperty(value = "备注说明")
    private String description;
}
