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

    public String runInterfaceQuery(String query, String method) throws IOException {
        Json j = new Json();
        log.debug("Function Info: {}", "ChMain.runInterfaceQuery");
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data sources example")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();
        query = "SELECT location,month ,avg(temperature) FROM weather where location = 'BRBRGTWN' GROUP BY location, month ORDER BY month";
        try {
            String aRes = SQLExecutor.runCsvDataset(spark, query);
            j.setObj(aRes);
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        } finally {
            spark.stop();
        }
        String json = JSON.toJSONStringWithDateFormat(j, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

}
