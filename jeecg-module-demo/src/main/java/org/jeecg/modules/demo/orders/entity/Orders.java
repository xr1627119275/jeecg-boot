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
 * @Description: orders
 * @Author: jeecg-boot
 * @Date:   2023-06-09
 * @Version: V1.0
 */
@Data
@TableName("orders")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="orders对象", description="orders")
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Integer id;
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
    private String orderId;
	/**售后编码*/
	@Excel(name = "售后编码", width = 15)
    @ApiModelProperty(value = "售后编码")
    private String aftersaleCode;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private String userId;
	/**售后类型*/
	@Excel(name = "售后类型", width = 15)
    @ApiModelProperty(value = "售后类型")
    private String aftersaleType;
	/**售后状态*/
	@Excel(name = "售后状态", width = 15)
    @ApiModelProperty(value = "售后状态")
    private String aftersaleStatus;
	/**发货状态*/
	@Excel(name = "发货状态", width = 15)
    @ApiModelProperty(value = "发货状态")
    private String shippingStatus;
	/**发货状态*/
	@Excel(name = "发货状态", width = 15)
    @ApiModelProperty(value = "发货状态")
    private String operationType;
	/**快递*/
	@Excel(name = "快递", width = 15)
    @ApiModelProperty(value = "快递")
    private String expressInterception;
	/**退款原因*/
	@Excel(name = "退款原因", width = 15)
    @ApiModelProperty(value = "退款原因")
    private String refundReason;
	/**申请说明*/
	@Excel(name = "申请说明", width = 15)
    @ApiModelProperty(value = "申请说明")
    private String applicationDescription;
	/**订单金额*/
	@Excel(name = "订单金额", width = 15)
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;
	/**退款金额*/
	@Excel(name = "退款金额", width = 15)
    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;
	/**卖家id*/
	@Excel(name = "卖家id", width = 15)
    @ApiModelProperty(value = "卖家id")
    private String sellerId;
	/**卖家备注*/
	@Excel(name = "卖家备注", width = 15)
    @ApiModelProperty(value = "卖家备注")
    private String sellerNote;
	/**收件地址*/
	@Excel(name = "收件地址", width = 15)
    @ApiModelProperty(value = "收件地址")
    private String shippingAddress;
	/**物流公司*/
	@Excel(name = "物流公司", width = 15)
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;
	/**快递单号*/
	@Excel(name = "快递单号", width = 15)
    @ApiModelProperty(value = "快递单号")
    private String trackingNumber;
	/**物流记录*/
	@Excel(name = "物流记录", width = 15)
    @ApiModelProperty(value = "物流记录")
    private String logisticsRecord;
	/**其他记录*/
	@Excel(name = "其他记录", width = 15)
    @ApiModelProperty(value = "其他记录")
    private String otherRecord;
}
