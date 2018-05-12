import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static junit.framework.TestCase.assertEquals;

public class DemoDubbo {
    public static DemoService demoService;
    @BeforeClass
    public static void setup(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo/consumer.xml"});
        context.start();
        demoService = (DemoService) context.getBean("demoService");
    }

    @Test
    public void testHelloWorld(){
        System.out.println(demoService.sayHello("123"));
        assertEquals(demoService.sayHello(""), "seveniruby@testerhome say hello to ");
       // assertEquals(demoService.sayHello("World"), "Hello World");

    }

    }
