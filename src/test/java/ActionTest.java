import edu.ruc.charts.utils.db.DBUtil;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @version V1.0
 * @Package: PACKAGE_NAME
 * @ClassName: ActionTest
 * @Description: SparkSQL执行的分类比较
 * @author: Tao
 * @date: Create in 2017-04-18 23:37
 **/
public class ActionTest {
    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        SparkSession spark = SparkSession.builder().appName("Java Spark SQL data sources example").config("spark.some.config.option", "some-value").getOrCreate();
        long s = System.currentTimeMillis();
        try {
//            Dataset<Row> weatherDF = doExcuteByParquet(spark);
            doExcuteByParquet(spark);
//            doExcuteByJdbc();
//              doExcuteByTempView(spark);
//            doExcuteByJdbc(spark);
//           Dataset<Row> weatherDF =  doExcuteByJdbc(spark);
        } catch (Exception e) {
            System.out.println("Error Info: " + e.getMessage());
        }
        long t = System.currentTimeMillis();
        spark.stop();
        long t1 = System.currentTimeMillis();
        System.out.println("Program Run Time： " + (t - s) + "ms");
        System.out.println("Parquet Run Time： " + (t1 - s1) + "ms");
        // doExcuteByAction in Single Node in Windows, 8G, SSD
        // real_estate: 2496ms, 7908ms; 3910ms, 9677ms; 4514ms, 11095ms; 410ms, 5916ms(Common Jdbc)
        // SELECT city, year, avg(soldPrice) FROM real_estate where city = 'Savage' GROUP BY city, year ORDER BY year
        // weather: 3029ms, 8537ms; 4036ms, 9251ms; 4322ms, 10397ms(Common Jdbc)
        // SELECT location, month, avg(temperature) FROM weather where location = 'BRBRGTWN' GROUP BY location, month ORDER BY month
    }

    public static ResultSet doExcuteByJdbc() {
        ResultSet rs = DBUtil.Select("SELECT city, month, avg(soldPrice) FROM real_estate where city = 'Savage' GROUP BY city, month ORDER BY month");
        return rs;
    }

    public static Dataset<Row> doExcuteByTempView(SparkSession spark) {
        Dataset<Row> wDF = spark.read().option("header", "true").csv("/tyx/weather.csv");
        wDF.createOrReplaceTempView("real_estate");
        Dataset<Row> weatherDF = spark.sql("SELECT location, month, avg(temperature) FROM weather where location = 'BRBRGTWN' GROUP BY location, month ORDER BY month");
        return weatherDF;
    }

    public static Dataset<Row> doExcuteByParquet(SparkSession spark) {
        Dataset<Row> parquetDF = spark.read().parquet("weather");
        parquetDF.createOrReplaceTempView("weather");
        Dataset<Row> weatherDF = spark.sql("SELECT location, month, avg(temperature) FROM weather where location = 'BRBRGTWN' GROUP BY location, month ORDER BY month");
        return weatherDF;
    }

    public static Dataset<Row> doExcuteByJdbc(SparkSession spark) {
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "postgres");
        connectionProperties.put("password", "zenvisage");
        connectionProperties.put("driver", "org.postgresql.Driver");
        Dataset<Row> jdbcDF = spark.read().jdbc("jdbc:postgresql://localhost:5432/postgres", "real_estate", connectionProperties);
        RelationalGroupedDataset rgDataset = jdbcDF.select("city", "month", "soldPrice").filter("city = 'Savage'").groupBy("city", "month");
        Dataset<Row> weatherDF = rgDataset.avg("soldPrice").orderBy("month");
//        Dataset<Row> jdbcDF = spark.read().jdbc("jdbc:postgresql://localhost:5432/postgres", "weather", connectionProperties);
//        RelationalGroupedDataset rgDataset = jdbcDF.select("location", "month", "temperature").filter("location = 'BRBRGTWN'").groupBy("location", "month");
//        Dataset<Row> weatherDF = rgDataset.avg("temperature").orderBy("month");
        return weatherDF;
    }

    @Test
    public void testJdbc() {
        try {

        } catch (Exception e) {
            System.out.println("Error Info: " + e.getMessage());
        }
    }

    @Test
    public void testTempView() {
        try {

        } catch (Exception e) {
            System.out.println("Error Info: " + e.getMessage());
        }
    }

    @Test
    public void testParquet() {
        try {

        } catch (Exception e) {
            System.out.println("Error Info: " + e.getMessage());
        }
    }
}
