package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.models.Courier;

import static io.restassured.RestAssured.given;

public class CourierSteps {

    @Step("Send POST request to /api/v1/courier")
    public Response createCourier(Courier courier){

        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response loginCourier(Courier courier){

        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Send DELETE request to /api/v1/courier/{id}")
    public Response deleteCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .pathParams("id", courier.id)
                .when()
                .delete("/api/v1/courier/{id}");
    }
}
