import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class DemoSchema {

    @Test
    public void DemoScheme1() {
        given().queryParam("code", "sogo")
                .header("Cookie", "xq_a_token=0d524219cf0dd2d0a4d48f15e36f37ef9ebcbee1; xq_a_token.sig=P0rdE1K6FJmvC2XfH5vucrIHsnw; xq_r_token=7095ce0c820e0a53c304a6ead234a6c6eca38488; xq_r_token.sig=xBQzKLc4EP4eZvezKxqxXNtB7K0; __utma=1.879740215.1524279912.1524279912.1524279912.1; __utmc=1; __utmz=1.1524279912.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; u=321524279913185; Hm_lvt_1db88642e346389874251b5a1eded6e3=1524279913; device_id=159f6f8bd74e8324710aa668eeff12ad; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1524279927; __utmb=1.2.10.1524279912")
                .when().get("http://xueqiu.com/v4/stock/quote.json")
                .then().log().all()
                .statusCode(200)
                .body(matchesJsonSchema(new File("/tmp/json.schema")));
    }

    @Test
    public void DemoScheme2() {
        given().queryParam("code", "sogo")
                .header("Cookie", "xq_a_token=0d524219cf0dd2d0a4d48f15e36f37ef9ebcbee1; xq_a_token.sig=P0rdE1K6FJmvC2XfH5vucrIHsnw; xq_r_token=7095ce0c820e0a53c304a6ead234a6c6eca38488; xq_r_token.sig=xBQzKLc4EP4eZvezKxqxXNtB7K0; __utma=1.879740215.1524279912.1524279912.1524279912.1; __utmc=1; __utmz=1.1524279912.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; u=321524279913185; Hm_lvt_1db88642e346389874251b5a1eded6e3=1524279913; device_id=159f6f8bd74e8324710aa668eeff12ad; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1524279927; __utmb=1.2.10.1524279912")
                .when().get("http://xueqiu.com/v4/stock/quote.json")
                .then().log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema/json.schema"));
    }

    }