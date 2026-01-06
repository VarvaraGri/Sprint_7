package ru.yandex.practicum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.models.Order;
import ru.yandex.practicum.steps.OrderSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private Order order;
    private OrderSteps orderSteps = new OrderSteps();
    private final String parameterColorBlack;
    private final String parameterColorGray;

    public CreateOrderTest(String parameterColorBlack, String parameterColorGray) {
        this.parameterColorBlack = parameterColorBlack;
        this.parameterColorGray = parameterColorGray;
    }


    @Parameterized.Parameters(name = "Тестовые данные: {0}")
    public static Object[] getParametersForColor(){
        return new Object[][] {
                {null, null},
                {"BLACK", null},
                {null, "GRAY"},
                {"BLACK", "GRAY"}
        };
    }

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        order = new Order();
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        order.setFirstName("Alex");
        order.setLastName("Push");
        order.setAddress("Moskva");
        order.setMetroStation(4);
        order.setPhone("+7 800 355 35 35");
        order.setRentTime(5);
        order.setComment("Do Lukomorya");
        order.setColor(parameterColorBlack, parameterColorGray);
    }

    @Test
    @DisplayName("Check code of /v1/orders")
    @Description("Test for /v1/orders endpoint for creating order with different color")
    public void differentColorInOrder(){
        order.track =
        orderSteps.createOrder(order)
                .then()
                .statusCode(201)
                .and()
                .body("track", notNullValue())
                .and()
                .extract().body().path("track");
    }

    @After
    public void tearDown(){
        orderSteps.cancelOrder(order);
    }
}
