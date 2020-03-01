package com.dkatalis.APIDemo.TestInitializer;

import com.dkatalis.APIDemo.Wrapper.Data;
import com.dkatalis.APIDemo.Wrapper.PageData;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class MyTest {

    public static void main(String[] args) {

        RequestSpecification REQUEST = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when();
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.replaceFiltersWith(ImmutableList.of());
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.replaceFiltersWith(ResponseLoggingFilter.responseLogger(), new RequestLoggingFilter(LogDetail.ALL));
        String BASEPATH = RestAssured.baseURI = "https://reqres.in/api/users";

        Response response = REQUEST.get("https://reqres.in/api/users?page=1");
        System.out.println(response.prettyPrint());
        String jsonString = response.asString();
        System.out.println("JSON Output is 1 ::::::::::: " + jsonString);

        Response response1 = REQUEST.get("https://reqres.in/api/users?page=2");
        System.out.println(response1.prettyPrint());
        String jsonString1 = response1.asString();
        System.out.println("JSON Output is 2 ::::::::::: " + jsonString1);

        Gson gson = new Gson();
        PageData pageData = gson.fromJson(jsonString, PageData.class);
        PageData pageData1 = gson.fromJson(jsonString1, PageData.class);

        List<Data> list1 = new ArrayList<Data>();
        List<Data> list2 = new ArrayList<Data>();

        for (Data data : pageData.getData()){

            list1.add(data);
        }

        for (Data data : pageData1.getData()){

            list2.add(data);
        }

        for (int i =0; i<=list1.size()-1; i++){
            if(list1.get(i).equals(list2.get(i))){
                System.out.println("Both the objects are same .....");

                System.out.println("Object 1 :: " + list1.get(i));
                System.out.println("Object 2 :: " + list2.get(i));
            }else{
                System.out.println("Both the objects are different .....");

                System.out.println("Object 1 :: " + list1.get(i));
                System.out.println("Object 2 :: " + list2.get(i));
            }
        }



    }
}
