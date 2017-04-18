import edu.ruc.charts.model.HelloSpring;
import edu.ruc.charts.service.ChartMain;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @version V1.0
 * @Package: PACKAGE_NAME
 * @ClassName: SpringTest
 * @Description: Spring框架的测试
 * @author: Tao
 * @date: Create in 2017-04-16 13:53
 **/
public class SpringTest {

    @Test
    public void TestSpring() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            HelloSpring hs = (HelloSpring) context.getBean("hellospring");
            hs.print();
        } catch (Exception e) {
            System.out.println("Error Info: " + e.getMessage());
        }
    }

    @Test
    public void TestChartMain() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            ChartMain cMain = (ChartMain) context.getBean("chMain");
//            cMain.runInterfaceQuery("", "");
        } catch (Exception e) {
            System.out.println("Error Info: " + e.getMessage());
        }
    }

}
