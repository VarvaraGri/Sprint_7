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
                .baseUri(BASE_URI)
                .body(order)
                .when()
                .post(CREATE_ORDER_HANDLE);
    }

    @Step("Send GET request to /v1/orders?courierId=1")
    public Response getOrderList() {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .when()
                .get(GET_ORDER_LIST_HANDLE);
    }

    @Step("Send CANCEL request to /api/v1/orders/cancel")
    public Response cancelOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(order)
                .when()
                .put(CANCEL_ORDER_HANDLE);
    }
}
