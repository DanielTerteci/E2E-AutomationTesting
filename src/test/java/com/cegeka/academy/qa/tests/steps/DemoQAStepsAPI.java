package com.cegeka.academy.qa.tests.steps;

import com.cegeka.academy.qa.database.model.User;
import com.cegeka.academy.qa.database.model.UserRowMapper;
import com.cegeka.academy.qa.json.model.AddBooksRequestBody;
import com.cegeka.academy.qa.json.model.AuthorizedRequestBody;
import com.cegeka.academy.qa.json.model.DeleteBookRequestBody;
import com.cegeka.academy.qa.json.model.GenerateTokenRequestBody;
import com.cegeka.academy.qa.json.model.GetBooksRequestBody;
import com.cegeka.academy.qa.json.model.IsbnModel;
import com.cegeka.academy.qa.json.model.PutBooksRequestBody;
import com.cegeka.academy.qa.json.model.RegisterUserRequestBody;
import com.cegeka.academy.qa.models.Book;
import com.google.common.net.HttpHeaders;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class DemoQAStepsAPI {
    private String token;
    private RegisterUserRequestBody registerUserRequestBody;
    private List<String> isbnList = List.of();
    private String addedBookISBN;
    private Response response;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${api.user}")
    private String apiUserUrl;
    @Value("${api.generateToken}")
    private String apiGenerateTokenUrl;
    @Value("${api.authorized}")
    private String apiAuthorizedUrl;
    @Value("${api.books}")
    private String apiBooksUrl;
    @Value("${api.book}")
    private String apiBookUrl;

    @When("Get all the books")
    public void getAllTheBooks() {
        response = RestAssured.given().get(apiBooksUrl);
        var data = response.as(GetBooksRequestBody.class);
        var books = data.getBooks();
        System.out.println("Number of books is: " + books.size());
        isbnList = books.stream().map(Book::getIsbn).collect(Collectors.toList());
    }

    @Then("Check the response code {int}")
    public void checkResponseCode(int code) {
        var statusCode = response.getStatusCode();
        Assert.assertEquals(code, statusCode);
    }

    @Given("Create user account")
    public void createUserAccount() {
        registerUserRequestBody = new RegisterUserRequestBody();
        var username = "User" + UUID.randomUUID().toString().substring(0, 5);
        var password = "Pass@" + new Random().nextInt(10000);
        registerUserRequestBody.setUserName(username);
        registerUserRequestBody.setPassword(password);

        response = RestAssured.given().contentType("application/json")
                .body(registerUserRequestBody)
                .post(apiUserUrl);
        var userID = response.jsonPath().getString("userID");
        registerUserRequestBody.setUserId(userID);

        var sql = "INSERT INTO users (USER_NAME, USER_PASSWORD, USER_ID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, username, password, userID);

        var selectSql = "SELECT USER_NAME, USER_PASSWORD, USER_ID FROM users WHERE USER_NAME = ?";
        var retrievedData = jdbcTemplate.queryForObject(selectSql, new Object[]{username}, new UserRowMapper());

        Assert.assertNotNull(retrievedData);
        Assert.assertEquals("Username does not match", retrievedData.getUserName(), username);
        Assert.assertEquals("Password does not match", retrievedData.getPassword(), password);
        Assert.assertEquals("UserID does not match", retrievedData.getUserId(), userID);
    }

    @And("Generate token for user")
    public void generateToken() {
        var generateToken = new GenerateTokenRequestBody();
        generateToken.setUserName(registerUserRequestBody.getUserName());
        generateToken.setPassword(registerUserRequestBody.getPassword());
        response = RestAssured.given().contentType("application/json")
                .body(generateToken)
                .post(apiGenerateTokenUrl);
        token = response.jsonPath().getString("token");
    }

    @When("Add a random book from books list to the user profile")
    public void addARandomBookToTheUserProfile() {
        var body = new AddBooksRequestBody();
        var randomIndex = new Random().nextInt(isbnList.size());
        var book = new IsbnModel(isbnList.get(randomIndex));
        addedBookISBN = isbnList.get(randomIndex);
        body.setUserId(registerUserRequestBody.getUserId());
        body.setCollectionOfIsbns(List.of(book));
        response = RestAssured.given().contentType("application/json")
                .body(body)
                .header(new Header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .post(apiBooksUrl);
    }

    @Then("Delete a book from the user profile")
    public void deleteABookFromTheUserProfile() {
        var deleteRequest = new DeleteBookRequestBody();
        deleteRequest.setUserId(registerUserRequestBody.getUserId());
        deleteRequest.setIsbn(addedBookISBN);
        response = RestAssured.given().contentType("application/json")
                .body(deleteRequest)
                .header(new Header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .delete(apiBookUrl);
    }

    @When("Put a book in the user profile")
    public void putABookInTheUserProfileAt() {
        var randomIndex = new Random().nextInt(isbnList.size());
        var putBookRequest = new PutBooksRequestBody();
        putBookRequest.setIsbn(isbnList.get(randomIndex));
        putBookRequest.setUserId(registerUserRequestBody.getUserId());
        response = RestAssured.given().contentType("application/json")
                .body(putBookRequest)
                .header(new Header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .put(apiBooksUrl + "/" + addedBookISBN);
    }

    @When("Check that user is authorized")
    public void checkThatUserIsAuthorizedAt() {
        var authorizedRequest = new AuthorizedRequestBody();
        authorizedRequest.setUserName(registerUserRequestBody.getUserName());
        authorizedRequest.setPassword(registerUserRequestBody.getPassword());
        response = RestAssured.given().contentType("application/json")
                .body(authorizedRequest)
                .header(new Header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .post(apiAuthorizedUrl);
    }

    @When("Delete all books from the user profile")
    public void deleteAllBooksAt() {
        response = RestAssured.given().contentType("application/json")
                .queryParam("UserId", registerUserRequestBody.getUserId())
                .header(new Header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .delete(apiBooksUrl);
    }

    @When("Get a book from book list")
    public void getABookFrom() {
        var randomIndex = new Random().nextInt(isbnList.size());
        var randomISBN = isbnList.get(randomIndex);
        response = RestAssured.given().contentType("application/json")
                .queryParam("ISBN", randomISBN)
                .header(new Header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .get(apiBookUrl);
    }

    @When("Get a user from database")
    public void getUserFromDb() {
        var getUser = new User();
        getUser.setUserId(registerUserRequestBody.getUserId());
        getUser.setPassword(registerUserRequestBody.getPassword());
        getUser.setUserName(registerUserRequestBody.getUserName());
        response = RestAssured.given().contentType("application/json")
                .body(getUser)
                .header(new Header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .get(apiUserUrl + "/" + registerUserRequestBody.getUserId());
    }

    @When("Delete user account")
    public void deleteUserAccountAt() {
        var deleteAccount = new DeleteBookRequestBody();
        deleteAccount.setUserId(registerUserRequestBody.getUserId());
        response = RestAssured.given().contentType("application/json")
                .body(deleteAccount)
                .header(new Header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .delete(apiUserUrl + "/" + registerUserRequestBody.getUserId());
    }
}