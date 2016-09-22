package com.cat.jsh.common;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Sort {

    public static final Order ASC = Order.ASC;
    public static final Order DESC = Order.DESC;
    @Getter
    private String column;
    @Getter
    private String order;

    private Sort(String column, Order order) {
        this.column = column;
        this.order = order.toString();
    }

    public static Sort of(String column, Order order) {
        return new Sort(column, order);
    }

    private enum Order {
        ASC, DESC
    }

}
