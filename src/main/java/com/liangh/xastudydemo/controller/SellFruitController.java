package com.liangh.xastudydemo.controller;

import com.liangh.xastudydemo.service.FruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 卖水果控制器
 */
@Slf4j
@RestController
public class SellFruitController {


    @Autowired
    private FruitService fruitService;

    /**
     * 查询当前水果数量和销售情况
     * @return
     */
    @GetMapping("/detail")
    public Map detail(){
        Map detail = fruitService.detail();
        return detail;
    }


    /**
     * 买苹果，这里简化了，只需传个参数hasMoney。
     * @param hasmoney 0,表示没钱。1表示有钱
     * @return
     */
    @GetMapping("buyApple")
    public String xaTransation(@RequestParam( required = false, defaultValue = "0") int hasmoney){


        String msg = "买苹果成功";
        long t1 = System.currentTimeMillis();
        try{
            fruitService.buyApple(hasmoney);
        }catch (Exception e){
            log.error(e.getMessage());
//            log.error("买苹果失败",e);
            msg = "买苹果失败!!!";
        }
        log.info("分布式事物提交或者回滚耗时：{}",System.currentTimeMillis() - t1);
        return msg;
    }




}
