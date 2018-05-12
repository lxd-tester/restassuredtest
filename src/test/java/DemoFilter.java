import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Base64;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class DemoFilter {
    @BeforeClass
    public static void setUp(){
        useRelaxedHTTPSValidation();
        RestAssured.proxy("127.0.0.1",8888);
        RestAssured.filters((req,res,ctx)->{
            req.header("testerhome","tester");
            Response resOrigin=ctx.next(req,res);
            return resOrigin;
        });

    }

    @Test
    public void testFilter(){
        given().filter( (req, res, ctx)->{
            Response resOrigin=ctx.next(req, res);
            ResponseBuilder responseBuilder=new ResponseBuilder().clone(resOrigin);
            //responseBuilder.setBody("{ \"SOGO\": { \"name\" : \"搜狗\"} }");
            String decodedContent=new String(
                    Base64.getDecoder().decode(
                            resOrigin.body().asString().trim()
                    )
            );
            responseBuilder.setBody(decodedContent);
            Response resNew=responseBuilder.build();

            return resNew;
        })
                //.get("https://xueqiu.com/v4/stock/quote.json?code=SOGO")
                .get("http://127.0.0.1:8080/base64.json").prettyPeek()
                .then()
                .statusCode(200)
                .body("topic.id", equalTo(6040));
    }
}
