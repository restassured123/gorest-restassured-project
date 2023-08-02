package com.gorest.testsuite;


import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

public class PostsExtractionTest extends TestBase {
    static ValidatableResponse response;

    public PostsExtractionTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test01() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test02() {
        List<String> records = response.extract().path("record");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + records.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test03() {
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test04() {
        List<Integer> allUserId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + allUserId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test05() {
        List<String> records = response.extract().path("record");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + records);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void test06() {
        List<String> title = response.extract().path("findAll{it.user_id ==4136850}.title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 4136850 are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Extract the body of all records whose id = 2671
    @Test
    public void test07(){
        List<?> body = response.extract().path("findAll{it.id==57624}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of body of all records whose id = 39654 are : " + body);
        System.out.println("------------------End of Test---------------------------");

    }


}
