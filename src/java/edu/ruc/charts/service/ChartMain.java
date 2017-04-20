package edu.ruc.charts.service;

import com.alibaba.fastjson.JSON;
import edu.ruc.charts.model.base.Json;
import edu.ruc.charts.utils.SQLExecutor;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @version V1.0
 * @Package: edu.ruc.charts.service
 * @ClassName: ChMain
 * @Description: 业务逻辑类
 * @author: Tao
 * @date: Create in 2017-04-16 11:45
 **/
public class ChartMain {

    private static Logger log = LoggerFactory.getLogger(ChartMain.class);

    public String runJdbcQuery(String query, String filter) throws IOException {
        Json j = new Json();
        log.debug("Function Info: {}", "ChMain.runInterfaceQuery");
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data sources example")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();
        if (query == "" || query == null)
            query = "SELECT location, month, avg(temperature) as temp FROM weather where location = 'KOSEOUL' GROUP BY location, month ORDER BY month";
        if (filter == "" || filter == null)
            filter = "KOSEOUL";
        try {
            long s = System.currentTimeMillis();
            String aRes = SQLExecutor.runCsvDataset(spark, query, filter);
            long t = System.currentTimeMillis();
            j.setObj(aRes);
            j.setMsg("读取数据成功，耗时：" + (t - s) + "ms");
            log.debug("Time Consumption: {}", (t - s) + "ms");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        } finally {
            spark.stop();
        }
        String aRes = JSON.toJSONStringWithDateFormat(j, "yyyy-MM-dd HH:mm:ss");
        return aRes;
    }

    public String runParquetQuery(String query, String filter) throws IOException {
        Json j = new Json();
        log.debug("Function Info: {}", "ChMain.runInterfaceQuery");
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data sources example")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();
        if (query == "" || query == null)
            query = "SELECT location, month, avg(temperature) as temp FROM weather where location = 'KOSEOUL' GROUP BY location, month ORDER BY month";
        if (filter == "" || filter == null)
            filter = "KOSEOUL";
        try {
            long s = System.currentTimeMillis();
            String aRes = SQLExecutor.runCsvDataset(spark, query, filter);
            long t = System.currentTimeMillis();
            j.setObj(aRes);
            j.setMsg("读取数据成功，耗时：" + (t - s) + "ms");
            log.debug("Time Consumption: {}", (t - s) + "ms");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        } finally {
            spark.stop();
        }
        String aRes = JSON.toJSONStringWithDateFormat(j, "yyyy-MM-dd HH:mm:ss");
        return aRes;
    }

    public String runViewQuery(String query, String filter) throws IOException {
        Json j = new Json();
        log.debug("Function Info: {}", "ChMain.runInterfaceQuery");
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data sources example")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();
        if (query == "" || query == null)
            query = "SELECT location, month, avg(temperature) as temp FROM weather where location = 'KOSEOUL' GROUP BY location, month ORDER BY month";
        if (filter == "" || filter == null)
            filter = "KOSEOUL";
        try {
            long s = System.currentTimeMillis();
            String aRes = SQLExecutor.runCsvDataset(spark, query, filter);
            long t = System.currentTimeMillis();
            j.setObj(aRes);
            j.setMsg("读取数据成功，耗时：" + (t - s) + "ms");
            log.debug("Time Consumption: {}", (t - s) + "ms");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        } finally {
            spark.stop();
        }
        String aRes = JSON.toJSONStringWithDateFormat(j, "yyyy-MM-dd HH:mm:ss");
        return aRes;
    }

}
