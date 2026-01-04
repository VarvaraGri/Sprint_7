package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.models.Order;

import static io.restassured.RestAssured.given;

public class OrderSteps {


    @Step("Send POST request to /api/v1/orders")
    public Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Send GET request to /v1/orders?courierId=1")
    public Response getOrderList() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get("/v1/orders?courierId=1");
    }

}
