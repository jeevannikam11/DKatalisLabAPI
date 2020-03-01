package com.dkatalis.APIDemo.TestInitializer;

import com.google.common.collect.ImmutableList;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class Initializer {

    public static RequestSpecification REQUEST;
    public static String BASEPATH;

    public void setup(String environment) {
        REQUEST = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when();
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.replaceFiltersWith(ImmutableList.of());
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.replaceFiltersWith(ResponseLoggingFilter.responseLogger(), new RequestLoggingFilter(LogDetail.ALL));
        BASEPATH = RestAssured.baseURI = "https://reqres.in/api/users";
    }
}
