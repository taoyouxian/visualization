import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * @version V1.0
 * @Package: PACKAGE_NAME
 * @ClassName: ParquetTest
 * @Description: Parquet文件
 * @author: Tao
 * @date: Create in 2017-04-19 23:09
 **/
public class ParquetTest {
    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data sources example")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();
        long s = System.currentTimeMillis();
        try {
            String source = "real_estate";
            Dataset<Row> wDF = spark.read().option("header", "true").csv("/tyx/" + source + ".csv");
//            Path path = new Path(source);
//            Configuration conf = new Configuration();
//            try {
//                FileSystem fs = path.getFileSystem(conf);
//                if (fs.exists(path)) {
//                } else {
            wDF.write().parquet(source);
//                }
//            } catch (Exception er) {
//                System.out.println("Error Info: " + er.getMessage());
//            }
        } catch (Exception e) {
            System.out.println("Error Info: " + e.getMessage());
        }
        long t = System.currentTimeMillis();
        spark.stop();
        long t1 = System.currentTimeMillis();
        System.out.println("Program Run Time： " + (t - s) + "ms");
        System.out.println("Parquet Run Time： " + (t1 - s1) + "ms");
        // Create Parquet in fifteen Node in Cloud Platform, 15G
        // cmu_clean: 5053ms, 10196ms
        // flights: 9912ms, 14935ms
        // real_estate: 10296ms, 15624ms
        // sales: 5318ms, 10172ms
        // weather: 8260ms, 13128ms
    }
}
