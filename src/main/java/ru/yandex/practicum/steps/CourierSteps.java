package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.models.Courier;

import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.steps.Endpoints.*;

public class CourierSteps {

    @Step("Send POST request to /api/v1/courier")
    public Response createCourier(Courier courier){

        return given()
                .header("Content-type", "application/json")
                .baseUri(BASEURI)
                .body(courier)
                .when()
                .post(CREATECOURIERHANDLE);
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response loginCourier(Courier courier){

        return given()
                .header("Content-type", "application/json")
                .baseUri(BASEURI)
                .body(courier)
                .when()
                .post(LOGINCOURIERHANDLE);
    }

    @Step("Send DELETE request to /api/v1/courier/{id}")
    public Response deleteCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASEURI)
                .pathParams("id", courier.id)
                .when()
                .delete(DELETECOURIERHANDLE);
    }
}
