场景：小明买苹果，老板给小明一个苹果，同时在自己的账本里面记录一条账单，同时和小明说一声："您的苹果请拿好，欢迎下次光临"

sql:
```mysql
-- 商品表
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '水果名字',
  `qty` int(11) NOT NULL COMMENT '数量',
  `fct` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lct` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '苹果', 100, '2019-07-03 17:50:54', '2019-07-03 17:50:54');
INSERT INTO `goods` VALUES (2, '香蕉', 50, '2019-07-03 17:51:09', '2019-07-03 17:51:09');

-- 账单表
CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `customer` varchar(50) NOT NULL COMMENT '顾客名',
  `goods` varchar(50) NOT NULL COMMENT '商品名',
  `amount` decimal(12,2) DEFAULT NULL COMMENT '收款金额',
  `fct` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```

url入口：
```bash
#老板查询当前水果数量和销售情况
curl http://localhost:8080/detail

#小明买苹果
curl http://localhost:8080/buyApple
```

不足点：
分布式事物连接可能是懒加载，第一次访问耗时可能较长2.2s左右。之后就正常了，25ms左右。也就说系统需要预热一下。

