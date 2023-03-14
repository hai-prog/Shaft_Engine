package shaft_engine_api;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class RestfulBooker {
    SHAFT.API api ;
    @BeforeClass
    public void setUP()
    {
     api = new SHAFT.API(System.getProperty("restfulBookerbaseUrl"));
    }

    @Test
    public void createToken()
    {
        String body = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }
                """;
        api.post("auth").setContentType(ContentType.JSON).setRequestBody(body).perform();
        api.assertThatResponse().body().contains("token");
    }

    @Test
    public void getBookings()
    {
        api.get("booking").perform();
        api.assertThatResponse().extractedJsonValue("id").isNotNull().perform();
    }

    @Test
    public void getSingleBook()
    {
        api.get("booking/1").perform();
        //api.assertThatResponse()

    }

    @Test
    public void createBooking()
    {   String fName="haidy";
        String lName = "osama";

        JSONObject jsonObject =  new JSONObject();
        jsonObject.put("firstname",fName);
        jsonObject.put("lastname",lName);
        jsonObject.put("additionalneeds", "Breakfast");
        JSONObject data =  new JSONObject();
          data.put("checkin","2016-11-11");
          data.put("checkout", "2021-11-02");
        jsonObject.put("bookingdates",data);
        jsonObject.put("totalprice", 589);
        jsonObject.put("depositpaid", true);



        api.post("booking").setContentType(ContentType.JSON).setRequestBody(jsonObject).perform();

    }

    @Test
    public void deleteBooking()
    {
        // create
        String fName="haidy";
        String lName = "osama";

        JSONObject jsonObject =  new JSONObject();
        jsonObject.put("firstname",fName);
        jsonObject.put("lastname",lName);
        jsonObject.put("additionalneeds", "Breakfast");
        JSONObject data =  new JSONObject();
        data.put("checkin","2016-11-11");
        data.put("checkout", "2021-11-02");
        jsonObject.put("bookingdates",data);
        jsonObject.put("totalprice", 589);
        jsonObject.put("depositpaid", true);

        api.post("booking").setContentType(ContentType.JSON).setRequestBody(jsonObject).perform();
        String bookingId = api.getResponseJSONValue("bookingid");

       // auth

        String body = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }
                """;
        api.post("auth").setContentType(ContentType.JSON).setRequestBody(body).perform();
        String token = api.getResponseJSONValue("token");

        // delete

         api.delete("booking/" + bookingId).addHeader("Cookie", "token="+ token).setTargetStatusCode(201).perform();
    }

}
