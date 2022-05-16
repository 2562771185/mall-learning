package com.jhzz.malltiny01.controller;

import com.jhzz.malltiny01.common.CommonPage;
import com.jhzz.malltiny01.common.CommonResult;
import com.jhzz.malltiny01.model.PmsBrand;
import com.jhzz.malltiny01.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/16
 * \* Time: 15:05
 * \* Description: 品牌管理Controller
 * \
 */
@Api(tags = "品牌管理控制器")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @Resource
    private PmsBrandService pmsBrandService;
    @ApiOperation(value = "获取品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @ApiOperation(value = "新建品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult createBrand(@RequestBody PmsBrand brand) {
        CommonResult commonResult;
        boolean res = pmsBrandService.createBrand(brand);
        if (res) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug("createBrand success:{}", brand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", brand);
        }
        return commonResult;
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        CommonResult commonResult;
        boolean res = pmsBrandService.updateBrand(id, pmsBrandDto);
        if (res) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }
    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        boolean res = pmsBrandService.deleteBrand(id);
        if (res) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }
    @ApiOperation(value = "获取品牌分页信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        CommonPage commonPage = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(commonPage);
    }
    @ApiOperation(value = "根据id查询品牌信息")
    @ApiImplicitParam(name = "id",value = "品牌id", required = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }

}
