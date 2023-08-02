package com.gorest.testsuite;


import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    public UserAssertionTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void Test01() {
        response.body("size", equalTo(20));
    }

    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void Test02() {
        response.body("find{it.id == 4104813}.name", equalTo("Gov. Rahul Nambeesan"));
    }

    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void Test03() {
        response.body("[2].name", equalTo("Mohan Iyer"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. BodhanGuha, Karthik Dubashi IV)
    @Test
    public void Test04() {
        response.body("name", hasItems("Jagadish Ahuja", "Anshula Dhawan"));
    }

    //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void Test05() {
        response.body("find{it.id == 4104812}.email", equalTo("namboothiri_daksha@murphy.test"));
    }

    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void Test06() {
        response.body("[3].status", equalTo("active"));
    }
    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void Test07(){
        response.body("[5].gender",equalTo("female"));
    }
}