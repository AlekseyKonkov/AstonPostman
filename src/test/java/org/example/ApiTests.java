import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetRequest() {
        given().
                when().
                get("/get?key=value").
                then().
                statusCode(200).
                body("args.key", equalTo("value"));
    }

    @Test
    public void testPostRequest() {
        given().
                contentType("application/json").
                body(requestData()).
                when().
                post("/post").
                then().
                statusCode(200).
                body("data.key", equalTo("value"));
    }

    @Test
    public void testPutRequest() {
        given().
                contentType("application/json").
                body(requestData()).
                when().
                put("/put").
                then().
                statusCode(200).
                body("data.key", equalTo("value"));
    }

    @Test
    public void testDeleteRequest() {
        given().
                when().
                delete("/delete").
                then().
                statusCode(200).
                body(not(hasKey("data"))); // Проверяет, что ключ "data" отсутствует
    }



    private Map<String, Object> requestData() {
        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");
        return data;
    }
}
