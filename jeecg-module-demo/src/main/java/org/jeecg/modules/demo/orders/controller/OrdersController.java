package org.jeecg.modules.demo.orders.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.Md5Util;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.orders.entity.Orders;
import org.jeecg.modules.demo.orders.entity.Users;
import org.jeecg.modules.demo.orders.service.IOrdersService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: orders
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Api(tags="orders")
@RestController
@RequestMapping("/orders/orders")
@Slf4j
public class OrdersController extends JeecgController<Orders, IOrdersService> {
	@Autowired
	private IOrdersService ordersService;
	
	/**
	 * 分页列表查询
	 *
	 * @param orders
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "orders-分页列表查询")
	@ApiOperation(value="orders-分页列表查询", notes="orders-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Orders>> queryPageList(Orders orders,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Orders> queryWrapper = QueryGenerator.initQueryWrapper(orders, req.getParameterMap());
		Page<Orders> page = new Page<Orders>(pageNo, pageSize);
		IPage<Orders> pageList = ordersService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param orders
	 * @return
	 */
	@AutoLog(value = "orders-添加")
	@ApiOperation(value="orders-添加", notes="orders-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Orders orders) {
		ordersService.save(orders);
		return Result.OK("添加成功！");
	}
	/**
	 *   添加
	 *
	 * @param orders
	 * @return
	 */
	@AutoLog(value = "orders-添加v2")
	@ApiOperation(value="orders-添加v2", notes="orders-添加v2")
	@GetMapping(value = "/addOrder")
	public Result<String> addOrder( Orders orders, HttpServletRequest req) {
		String appId = req.getParameter("appId");
		String appSecret = req.getParameter("appSecret");
		String timestampStr = req.getParameter("timestamp");
		String sign = req.getParameter("sign");

		if (StringUtils.isBlank(appId)){
			log.debug("appId不能为空...........");
			return Result.error("appId不能为空！");
		}
		if (StringUtils.isBlank(timestampStr)){
			log.debug("timestamp不能为空...........");
			return Result.error("timestamp不能为空！");
		}
		if (StringUtils.isBlank(appSecret)){
			log.debug("appSecret不能为空...........");
			return Result.error("appSecret不能为空！");
		}
		if (StringUtils.isBlank(sign)){
			log.debug("sign不能为空...........");
			return Result.error("sign不能为空！");
		}
		if (StringUtils.isBlank(orders.getOrderId())){
			log.debug("订单号不能为空...........");
			return Result.error("订单号不能为空！");
		}

		long timestamp = 0;
		try {
			timestamp = Long.parseLong(timestampStr);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//1.前端传过来的时间戳与服务器当前时间戳差值大于180，则当前请求的timestamp无效
		if (Math.abs(timestamp - System.currentTimeMillis() / 1000) > 180) {
			log.debug("timestamp无效...........");
			return Result.error("timestamp无效！");
		}

		String sSign = Md5Util.md5Encode(appId + appSecret + timestampStr + orders.getOrderId(), "utf-8");
		log.debug("sSign: " + sSign);

		if (!sSign.toLowerCase().equals(sign)) {
			return Result.error("sign无效！");
		}
		ordersService.save(orders);
		return Result.OK("添加成功！");
	}

	/**
	  *   查询userId的用户订单金额
	  *
	  * @param users
	  * @return
	  */
	 @AutoLog(value = "orders-用户订单金额")
	 @ApiOperation(value="orders-用户订单金额", notes="orders-用户订单金额")
	 @PostMapping(value = "/queryUserByID")
	 public Result<Users> queryUserByID(@RequestBody Users users) {
		 Users user = ordersService.queryUserByID(users.getUserId());
		 return Result.OK(user);
	 }
	/**
	  *   查询所有的订单User
	  *
  * 	@return
	  */
	 @AutoLog(value = "orders-所有有订单用户")
	 @ApiOperation(value="orders-所有有订单用户", notes="orders-所有有订单用户")
	 @GetMapping(value = "/getUsersList")
	 public Result<IPage<Users>> getUsersList(Users user,
											 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											 @RequestParam(name="uId", defaultValue="") String userId,
											 HttpServletRequest req) {
		 QueryWrapper<Users> queryWrapper = QueryGenerator.initQueryWrapper(user, req.getParameterMap());
		 Page<Users> page = new Page<Users>(pageNo, pageSize);
		 IPage<Users> pageList = ordersService.getUsersList(page, queryWrapper, userId);
		 return Result.OK(pageList);
	 }
	
	/**
	 *  编辑
	 *
	 * @param orders
	 * @return
	 */
	@AutoLog(value = "orders-编辑")
	@ApiOperation(value="orders-编辑", notes="orders-编辑")
	@RequiresPermissions("orders:orders:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Orders orders) {
		ordersService.updateById(orders);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "orders-通过id删除")
	@ApiOperation(value="orders-通过id删除", notes="orders-通过id删除")
	@RequiresPermissions("orders:orders:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ordersService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "orders-批量删除")
	@ApiOperation(value="orders-批量删除", notes="orders-批量删除")
	@RequiresPermissions("orders:orders:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ordersService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "orders-通过id查询")
	@ApiOperation(value="orders-通过id查询", notes="orders-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Orders> queryById(@RequestParam(name="id",required=true) String id) {
		Orders orders = ordersService.getById(id);
		if(orders==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(orders);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param orders
    */
    @RequiresPermissions("orders:orders:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Orders orders) {
        return super.exportXls(request, orders, Orders.class, "orders");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("orders:orders:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {

        return super.importExcel(request, response, Orders.class);
    }

}
