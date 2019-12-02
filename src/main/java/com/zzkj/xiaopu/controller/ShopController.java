package com.zzkj.xiaopu.controller;

import com.zzkj.xiaopu.common.JsonData;
import com.zzkj.xiaopu.service.BaseService;
import com.zzkj.xiaopu.utils.ByteUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(tags = "店铺管理")
@RestController
@RequestMapping(value = "/api/shop")
public class ShopController {
    @Autowired
    private BaseService baseService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam(value = "custTel", required = true) String custTel,
                                   @RequestParam(value = "custPassword", required = true) String custPassword) throws Exception {
        Map map = new HashMap<String, Object>();
        Map pmap = new HashMap();
        pmap.put("custTel", custTel);
        custPassword = ByteUtil.getMD5(custTel + custPassword);
        pmap.put("custPassword", custPassword);
        List<Map> list=  baseService.selectList("apiservice.Shop.login", pmap);
        ResponseEntity res;
        if (list.size() <= 0){
            res = new ResponseEntity(JsonData.fail("用户名或密码错误"), HttpStatus.OK);
        }else {
            res = new ResponseEntity(JsonData.success(list.get(0)), HttpStatus.OK);
        }
        return res;
    }

    @ApiOperation("新增商户")
    @RequestMapping(value = "/addShop", method = RequestMethod.POST)
    public ResponseEntity<?> addShop(@RequestParam(value = "shopName", required = false) String shopName,
                                     @RequestParam(value = "shopAddress", required = false) String shopAddress,
                                     @RequestParam(value = "custName", required = false) String custName,
                                     @RequestParam(value = "custTel", required = false) String custTel,
                                     @RequestParam(value = "custPassword", required = false) String custPassword,
                                     @RequestParam(value = "orderNumber", required = false) String orderNumber){
        Map map = new HashMap<String, Object>();
        Map pmap = new HashMap();
        pmap.put("shopId", UUID.randomUUID().toString().replace("-", ""));
        pmap.put("shopName", shopName);
        pmap.put("shopAddress", shopAddress);
        pmap.put("custName", custName);
        pmap.put("custTel", custTel);
        pmap.put("orderNumber", orderNumber);

        custPassword = ByteUtil.getMD5(custTel + custPassword);
        pmap.put("custPassword", custPassword);
        ResponseEntity res;
        System.out.println("测试jenkins");
        try {
            int count = baseService.insert("apiservice.Shop.addShop", pmap);
            if (count > 0){
                return res = new ResponseEntity(JsonData.success(count), HttpStatus.OK);
            }else {
                return res = new ResponseEntity(JsonData.fail("新增商户失败"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return res = new ResponseEntity(JsonData.fail("新增商户接口异常"), HttpStatus.OK);
        }
    }
}
