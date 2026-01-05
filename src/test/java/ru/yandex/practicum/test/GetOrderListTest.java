package ru.yandex.practicum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.steps.OrderSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest {
    private OrderSteps orderSteps = new OrderSteps();

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    @DisplayName("Check code of /v1/orders?courierId=1")
    @Description("Test for /v1/orders?courierId=1 endpoint for getting order list")
    public void shouldReturnCouriersAllOrders(){

        orderSteps.getOrderList()
                .then()
                .body("orders", notNullValue());
    }

}
