package ru.yandex.practicum.steps;

public class Endpoints {
    public static final String BASEURI = "https://qa-scooter.praktikum-services.ru/";
    public static final String CREATECOURIERHANDLE = "/api/v1/courier";
    public static final String LOGINCOURIERHANDLE = "/api/v1/courier/login";
    public static final String DELETECOURIERHANDLE = "/api/v1/courier/{id}";
    public static final String CREATEORDERHANDLE = "/api/v1/orders";
    public static final String CANCELORDERHANDLE = "/api/v1/orders/cancel";
    public static final String GETORDERLISTHANDLE = "/v1/orders?courierId=1";
}
