import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.lessThan;

//equalTo 和 hasItems是Hamcrest matchers的方法，所以需要静态导入org.hamcrest.Matchers。

public class Demo1 {
    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;
    //全局内容，添加通用的内容
    @BeforeClass
    public static void quanju(){
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.proxy("192.168.1.103",8888);
        requestSpecification =new RequestSpecBuilder().build();
        requestSpecification.cookie("test","tester");
        requestSpecification.header("test1","tester1");
        responseSpecification =new ResponseSpecBuilder().build();
        responseSpecification.statusCode(200);

    }
    @Test
    public void testDemo1() {
        given()
                .queryParam("wd", "testerhome")
                .when().get("http://www.baidu.com")
                .then().log().all().statusCode(200);
    }
    //response->json
    @Test
    public void testJson() {
        given()
                .when().get("http://localhost:8080/lotto.json")
                .then().log().all()
                    .statusCode(200)
                    .body("lotto.lottoId",equalTo(5))
                    .body("lotto.winners.winnerId",hasItems(23,54))
                    .body("lotto.winners[0].winnerId",equalTo(23));
    }
    //response->xml
    @Test
    public void testXML() {
        given()
                .when()
                    .get("http://localhost:8080/shopping.xml")
                .then()
                .log().all()
                .body("shopping.category.find { it.@type == 'groceries' }.item", hasItems("Chocolate", "Coffee"));
    }
    //传值
    @Test
    public void testExtract() {
        given()
                .queryParam("wd", "testerhome")
                .when().get("http://www.baidu.com")
                .then().log().all().statusCode(200);
    }

    /**request->json
     * 1.发送Json内容
     * 2.对响应时间进行断言
     */

    @Test
    public void testJsonData(){
        //useRelaxedHTTPSValidation();
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("1",1);
        map.put("2","test");
        map.put("array",new String[]{"123","456"} );
        given()
                //.proxy("192.168.31.180",8888)
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(map)
                .log().all()
                .when().get("http://www.baidu.com")
                .then().spec(responseSpecification).time(lessThan(1000L));
    }

}
