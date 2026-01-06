package ru.yandex.practicum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.models.Courier;
import ru.yandex.practicum.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
    private Courier courier;
    private final CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(5));
        courier.setPassword(RandomStringUtils.randomAlphabetic(4));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(5));
        courierSteps.createCourier(courier);
    }


    @Test
    @DisplayName("Check status code of /v1/courier/login")
    @Description("Basic test for /v1/courier/login endpoint")
    public void shouldLoginCourier(){
        courierSteps.loginCourier(courier)
                .then()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Check mistake code of /v1/courier/login, when logging without login")
    @Description("Test for /v1/courier/login endpoint for mistake massage when logging without login")
    public void ifNoLoginShouldReturnMistake(){
        String tmpLogin = courier.getLogin();
        courier.setLogin("");
        courierSteps.loginCourier(courier)
                .then()
                .statusCode(400)
                .and()
                .body("message", is("Недостаточно данных для входа"));
        courier.setLogin(tmpLogin);
    }

    @Test
    @DisplayName("Check mistake code of /v1/courier/login, when logging without password")
    @Description("Test for /v1/courier/login endpoint for mistake massage when logging without password")
    public void ifNoPasswordShouldReturnMistake(){
        String tmpPassword = courier.getPassword();
        courier.setPassword("");
        courierSteps.loginCourier(courier)
                .then()
                .statusCode(400)
                .and()
                .body("message", is("Недостаточно данных для входа"));
        courier.setPassword(tmpPassword);
    }

    @Test
    @DisplayName("Check mistake code of /v1/courier/login, when logging with wrong login")
    @Description("Test for /v1/courier/login endpoint for mistake massage when logging with wrong login")
    public void ifWrongLoginShouldReturnMistake(){
        String tmpLogin = courier.getLogin();
        courier.setLogin(RandomStringUtils.randomAlphabetic(5));
        courierSteps.loginCourier(courier)
                .then()
                .statusCode(404)
                .and()
                .body("message", is("Учетная запись не найдена"));
        courier.setLogin(tmpLogin);
    }

    @Test
    @DisplayName("Check mistake code of /v1/courier/login, when logging with wrong password")
    @Description("Test for /v1/courier/login endpoint for mistake massage when logging with wrong password")
    public void ifWrongPasswordShouldReturnMistake(){
        String tmpPassword = courier.getPassword();
        courier.setPassword(RandomStringUtils.randomAlphabetic(4));
        courierSteps.loginCourier(courier)
                .then()
                .statusCode(404)
                .and()
                .body("message", is("Учетная запись не найдена"));
        courier.setPassword(tmpPassword);
    }

    @After
    public void tearDown(){
        courier.setId(courierSteps
                .loginCourier(courier)
                .then().extract().body().path("id"));
        courierSteps.deleteCourier(courier);
    }
}
