package com.liangh.xastudydemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FruitService {

    /**
     * 账单数据库
     */
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    /**
     * 水果数据库
     */
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    /**
     * 能够发声的喇叭
     */
    @Autowired
    private JmsTemplate jmsTemplate;


    /**
     * 查询当前水果数量和销售情况
     * @return
     */
    public Map detail(){
        String sql = "select * from goods";
        List<Map<String, Object>> goodsList = jdbcTemplate2.queryForList(sql);
        String sql2 = "select * from bill";
        List<Map<String, Object>> billList = jdbcTemplate1.queryForList(sql2);
        Map detail = new HashMap();
        detail.put("fruit",goodsList);
        detail.put("bill",billList);
        return detail;
    };



    @Transactional
    public void buyApple(int hasMoney){

        //苹果数量减一
        String sql = "update goods set qty = qty - 1 where id = 1";
        int update = jdbcTemplate2.update(sql);

        String sql2 = "INSERT INTO `bill`( `customer`, `goods`, `amount`) VALUES ('xiaoming', 'apple', 80)";
        //老板记录一条账单
        int update2 = jdbcTemplate1.update(sql2);

        log.info("goods表更新 {} 条数据； bill表更新 {} 条数据;不要激动，事物这时候还没有提交", update,update2);

        //老版用喇叭说一句"谢谢"
        jmsTemplate.convertAndSend("thanks","您的苹果请拿好，欢迎下次光临");

        if(hasMoney == 0){
            throw new RuntimeException("小明没带钱，想吃霸王餐");
        }

    }

}
