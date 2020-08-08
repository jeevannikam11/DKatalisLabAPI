package com.dkatalis.APIDemo.StepDefHelper;

import com.dkatalis.APIDemo.Wrapper.Data;
import com.dkatalis.APIDemo.Wrapper.PageData;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import io.cucumber.java.sl.In;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class UserStepDefHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserStepDefHelper.class);

    public Integer a = 0;
    public Integer b = 0;
    public Integer c = 0;

    final String BASEPATH = "https://reqres.in/api/users?";

    List<String> responseList = new ArrayList<String>();

    public void getNumbers(String a, String b) {

        this.a = Integer.parseInt(a);
        this.b = Integer.parseInt(b);
    }

    public void getRequiredMethods(String method) {

        if (method.equalsIgnoreCase("add")) {
            this.c = this.a + this.b;
        }
    }

    public boolean output(String op) {

        if (Integer.parseInt(op) == c) {
            return true;
        } else {
            return false;
        }
    }

    public String getApiResponse(String param, String value) {
        try {
            RequestSpecification REQUEST = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).when();
            RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
            RestAssured.replaceFiltersWith(ImmutableList.of());
            RestAssured.useRelaxedHTTPSValidation();
            RestAssured.replaceFiltersWith(ResponseLoggingFilter.responseLogger(), new RequestLoggingFilter(LogDetail.ALL));

            Response response = (Response) REQUEST.baseUri(BASEPATH).queryParam(param, value).get();
            System.out.println(response.prettyPrint());
            String jsonString = response.asString();
            System.out.println("JSON Output is ::::::::::: " + jsonString);
            this.responseList.add(jsonString);
            jsonString.length();
            return jsonString;
        } catch (Exception e) {
            System.out.println("Exception occurred in method :: " + e);
            e.printStackTrace();
            return "Exception occurred in getApiResponse method ....";
        }

    }

    public String compareBothTheResponsesLineByLine() {

        String status = "";

        try {
            String responseOne = this.responseList.get(0);
            String responseTwo = this.responseList.get(1);

            Gson gson = new Gson();
            PageData pageData = gson.fromJson(responseOne, PageData.class);
            PageData pageData1 = gson.fromJson(responseTwo, PageData.class);

            List<Data> dataListOne = new ArrayList<Data>();
            List<Data> dataListTwo = new ArrayList<Data>();

            for (Data data : pageData.getData()) {
                dataListOne.add(data);
            }

            for (Data data : pageData1.getData()) {
                dataListTwo.add(data);
            }

            for (int i = 0; i <= dataListOne.size() - 1; i++) {
                if (dataListOne.get(i).equals(dataListTwo.get(i))) {
                    System.out.println("https://reqres.in/api/users/" + dataListOne.get(i).getId() + " equals " + "https://reqres.in/api/users/" + dataListTwo.get(i).getId());
                    status = "Both the objects are same .....";
                } else {
                    System.out.println("https://reqres.in/api/users/" + dataListOne.get(i).getId() + " not equals " + "https://reqres.in/api/users/" + dataListTwo.get(i).getId());
                    status = "Both the objects are different .....";
                }

            }
        } catch (Exception e) {
            System.out.println("Exception occurred in method :: " + e);
            e.printStackTrace();
        }
        return status;
    }
}
