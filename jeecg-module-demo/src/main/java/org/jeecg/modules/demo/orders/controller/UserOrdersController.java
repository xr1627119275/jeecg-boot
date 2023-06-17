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
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.orders.entity.UserOrders;
import org.jeecg.modules.demo.orders.service.IUserOrdersService;

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
 * @Description: 所有用户订单管理
 * @Author: jeecg-boot
 * @Date:   2023-06-12
 * @Version: V1.0
 */
@Api(tags="所有用户订单管理")
@RestController
@RequestMapping("/orders/userOrders")
@Slf4j
public class UserOrdersController extends JeecgController<UserOrders, IUserOrdersService> {
	@Autowired
	private IUserOrdersService userOrdersService;
	
	/**
	 * 分页列表查询
	 *
	 * @param userOrders
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "所有用户订单管理-分页列表查询")
	@ApiOperation(value="所有用户订单管理-分页列表查询", notes="所有用户订单管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<UserOrders>> queryPageList(UserOrders userOrders,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<UserOrders> queryWrapper = QueryGenerator.initQueryWrapper(userOrders, req.getParameterMap());
		Page<UserOrders> page = new Page<UserOrders>(pageNo, pageSize);
//		queryWrapper.getExpression().add();
		IPage<UserOrders> pageList = userOrdersService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param userOrders
	 * @return
	 */
	@AutoLog(value = "所有用户订单管理-添加")
	@ApiOperation(value="所有用户订单管理-添加", notes="所有用户订单管理-添加")
	@RequiresPermissions("orders:user_orders:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody UserOrders userOrders) {
		userOrdersService.save(userOrders);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param userOrders
	 * @return
	 */
	@AutoLog(value = "所有用户订单管理-编辑")
	@ApiOperation(value="所有用户订单管理-编辑", notes="所有用户订单管理-编辑")
	@RequiresPermissions("orders:user_orders:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody UserOrders userOrders) {
		userOrdersService.updateById(userOrders);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "所有用户订单管理-通过id删除")
	@ApiOperation(value="所有用户订单管理-通过id删除", notes="所有用户订单管理-通过id删除")
	@RequiresPermissions("orders:user_orders:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		userOrdersService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "所有用户订单管理-批量删除")
	@ApiOperation(value="所有用户订单管理-批量删除", notes="所有用户订单管理-批量删除")
	@RequiresPermissions("orders:user_orders:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.userOrdersService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "所有用户订单管理-通过id查询")
	@ApiOperation(value="所有用户订单管理-通过id查询", notes="所有用户订单管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<UserOrders> queryById(@RequestParam(name="id",required=true) String id) {
		UserOrders userOrders = userOrdersService.getById(id);
		if(userOrders==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(userOrders);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param userOrders
    */
    @RequiresPermissions("orders:user_orders:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, UserOrders userOrders) {
        return super.exportXls(request, userOrders, UserOrders.class, "所有用户订单管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("orders:user_orders:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, UserOrders.class);
    }

}
