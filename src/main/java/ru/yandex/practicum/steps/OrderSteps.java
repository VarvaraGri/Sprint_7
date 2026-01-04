package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.models.Order;

import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.steps.Endpoints.*;

public class OrderSteps {


    @Step("Send POST request to /api/v1/orders")
    public Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASEURI)
                .body(order)
                .when()
                .post(CREATEORDERHANDLE);
    }

    @Step("Send GET request to /v1/orders?courierId=1")
    public Response getOrderList() {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASEURI)
                .when()
                .get(GETORDERLISTHANDLE);
    }

    @Step("Send CANCEL request to /api/v1/orders/cancel")
    public Response cancelOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASEURI)
                .body(order)
                .when()
                .put(CANCELORDERHANDLE);
    }
}
