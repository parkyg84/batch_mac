package com.yonggoo.batch_mac.balance.check_mgt.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "create")
public class Order {
    private int orderId;
    private int userId;

}
