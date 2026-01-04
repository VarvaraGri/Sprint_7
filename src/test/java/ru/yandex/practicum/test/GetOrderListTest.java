package ru.yandex.practicum.test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.models.Courier;
import ru.yandex.practicum.steps.CourierSteps;
import ru.yandex.practicum.steps.OrderSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest {
    private OrderSteps orderSteps = new OrderSteps();

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void shouldReturnCouriersAllOrders(){

        orderSteps.getOrderList()
                .then()
                .body("orders", notNullValue());
    }

}
