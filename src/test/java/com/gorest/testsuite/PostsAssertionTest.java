package com.gorest.testsuite;


import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase {
    static ValidatableResponse response;

    public PostsAssertionTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 25
    @Test
    public void test01() {
        response.body("size", equalTo(25));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demittocentum.”
    @Test
    public void test02() {
        response.body("find{it.id==57454}.title", equalTo("Aut vicinus custodia aureus nisi tyrannus ara suadeo."));
    }
    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test03(){
        response.body("id" , hasItem(57624));
    }
    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test04(){
        response.body("id", hasItems(57623,57622,57621));
    }

    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”

    @Test
    public void test05(){
        response.body("find{it.id == 57624}.body", equalTo("Calculus laudantium amiculum. Deleo et votum. Aggredior spes supellex. Deinde commemoro a. Ad arceo arbor. Coniuratio adnuo supra. Censura speculum utpote. Stips vergo vindico. Viduo avarus adultus. Quo sonitus unus. Vos thesaurus ipsum. Argumentum demitto bestia. Non adeptio toties. Contego succurro taceo. Terreo accipio ambitus. Alii defigo sit."));

    }

}