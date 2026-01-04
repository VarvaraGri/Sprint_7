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

import static org.hamcrest.CoreMatchers.*;

public class CreateCourierTest {

    private Courier courier;
    private final CourierSteps courierSteps = new CourierSteps();
    private boolean courierCreated = false;

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = new Courier();
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        courier.setLogin(RandomStringUtils.randomAlphabetic(5));
        courier.setPassword(RandomStringUtils.randomAlphabetic(4));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(5));
    }


    @Test
    public void shouldCreateCourier(){
        courierSteps.createCourier(courier)
                .then()
                .statusCode(201)
                .and()
                .body("ok", is(true));
        courierCreated = true;
    }

    @Test
    public void shouldNotCreateDoppelCourier(){
        courierSteps.createCourier(courier);
        courierCreated = true;
        courierSteps.createCourier(courier)
                .then()
                .statusCode(409)
                .and()
                .body("message", is("Этот логин уже используется"));
    }

    @Test
    public void shouldNotCreateCourierWithSameLogin(){
        courierSteps.createCourier(courier);
        courierCreated = true;
        String tmpPassword = courier.getPassword();
        courierSteps.createCourier(courier)
                .then()
                .statusCode(409)
                .and()
                .body("message", is("Этот логин уже используется"));
        courier.setPassword(tmpPassword);
    }

    @Test
    public void ifNoLoginShouldReturnMistakeBadRequest(){
        courier.setLogin("");
        courierSteps.createCourier(courier)
                .then()
                .statusCode(400)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void ifNoPasswordShouldReturnMistakeBadRequest(){
        courier.setPassword("");
        courierSteps.createCourier(courier)
                .then()
                .statusCode(400)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown(){
        if (courierCreated){
            courier.setId(courierSteps
                    .loginCourier(courier)
                    .then().extract().body().path("id"));
            courierSteps.deleteCourier(courier);
        }
    }
}
