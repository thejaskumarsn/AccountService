package com.revolut.transfer.AccountService;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceTest {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	public void test1() {

		//RestAssured.baseURI = "http://localhost:8080/account/addAccount";
		String myJson1 = "{\"accountName\":\"Rob\",\"accountBalance\":10000}";
		String myJson2 = "{\"accountName\":\"Dave\",\"accountBalance\":10000}";
		Response res1 = given().contentType("application/json").body(myJson1).when().post("/account/addAccount");
		Response res2 = given().contentType("application/json").body(myJson2).when().post("/account/addAccount");
		
		Assert.assertEquals(res1.getBody().asString(), "Account added sucessfully");
		Assert.assertEquals(res2.getBody().asString(), "Account added sucessfully");
	}

	@Test
	public void test2() {
		//RestAssured.baseURI = "http://localhost:8080/account/addAccount";
		get("/account/getAllAccounts").then().statusCode(200).assertThat().body("accountName", hasItems("Rob"));
		get("/account/getAllAccounts").then().statusCode(200).assertThat().body("accountName", hasItems("Dave"));
	}
	
	@Test
	public void test3() {

		//RestAssured.baseURI = "http://localhost:8080/account/addAccount";
		String myJson1 = "{\"fromAccountName\":\"Rob\",\"toAccountName\":\"Dave\",\"amount\":500}";
		
		Response res = given().contentType("application/json").body(myJson1).when().
				post("/account/transferAmount");
		Assert.assertEquals(res.getBody().asString(), "Amount Transferred");
		
	}

}
