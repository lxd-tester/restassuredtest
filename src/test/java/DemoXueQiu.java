import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class DemoXueQiu {
    @Test
    public void testXueQiu1() {
        useRelaxedHTTPSValidation();
        given().log().all().proxy("127.0.0.1",8888)
                .queryParam("code", "sogo")
                .header("Cookie", "xq_a_token=229a3a53d49b5d0078125899e528279b0e54b5fe; xq_a_token.sig=oI-FfEMvVYbAuj7Ho7Z9mPjGjjI; xq_r_token=8a43eb9046efe1c0a8437476082dc9aac6db2626; xq_r_token.sig=Efl_JMfn071_BmxcpNvmjMmUP40; Hm_lvt_1db88642e346389874251b5a1eded6e3=1523698288; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1523698288; u=831523698289146; __utma=1.1365012804.1523698290.1523698290.1523698290.1; __utmc=1; __utmz=1.1523698290.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmt=1; __utmb=1.1.10.1523698290; device_id=159f6f8bd74e8324710aa668eeff12ad")
                .when()
                .get("https://xueqiu.com/stock/search.json")
                .then()
                .log().all()
                .statusCode(200)
                .body("stocks.name",equalTo("搜狗"))
                .body("stocks.name", hasItems("搜狗"))
                .body("stocks.code",hasItems("SOGO"))
                .body("stocks.stock_id",hasItems(1029472));
    }

    //准备写一个post请求的接口验证
    @Test
    public void testXueQiu2() {
        given().log().all()
                .queryParam("code", "sogo")
                .header("Cookie", "xq_a_token=229a3a53d49b5d0078125899e528279b0e54b5fe; xq_a_token.sig=oI-FfEMvVYbAuj7Ho7Z9mPjGjjI; xq_r_token=8a43eb9046efe1c0a8437476082dc9aac6db2626; xq_r_token.sig=Efl_JMfn071_BmxcpNvmjMmUP40; Hm_lvt_1db88642e346389874251b5a1eded6e3=1523698288; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1523698288; u=831523698289146; __utma=1.1365012804.1523698290.1523698290.1523698290.1; __utmc=1; __utmz=1.1523698290.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmt=1; __utmb=1.1.10.1523698290; device_id=159f6f8bd74e8324710aa668eeff12ad")
                .when()
                .get("https://xueqiu.com/stock/search.json")
                .then()
                .log().all()
                .statusCode(200)
                .body("stocks.name", hasItems("搜狗"))
                .body("stocks.code",hasItems("SOGO"))
                .body("stocks.stock_id",hasItems("1029472"));
    }
}
