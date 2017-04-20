package edu.ruc.charts.utils;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Package: edu.ruc.charts.utils
 * @ClassName: SQLExecutor
 * @Description: SQl执行器工具类
 * @author: Tao
 * @date: Create in 2017-04-16 20:57
 **/
public class SQLExecutor {
    private static Logger log = LoggerFactory.getLogger(SQLExecutor.class);

    public static String runCsvDataset(SparkSession spark, String query, String filter) {
        log.debug("Function Info: {}", "SQLExecutor.runCsvDataset");
        String aJsonArray = "";
        String schemaString = "location month dayofyear year temperature";
        List<StructField> fields = new ArrayList<StructField>();
        StructField field = new StructField();
        for (String fieldName : schemaString.split(" ")) {
            if (fieldName.equals("month") || fieldName.equals("dayofyear") || fieldName.equals("year")) {
                field = DataTypes.createStructField(fieldName, DataTypes.IntegerType, true);
            } else if (fieldName.equals("location")) {
                field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
            } else if (fieldName.equals("temperature")) {
                field = DataTypes.createStructField(fieldName, DataTypes.FloatType, true);
            }
            fields.add(field);
        }
        try {
            StructType schema = DataTypes.createStructType(fields);
            JavaRDD<String> weatherRDD = spark.read().textFile("E://weather.csv").toJavaRDD().filter(new Function<String, Boolean>() {
                public Boolean call(String s) throws Exception {
                    return s.contains(filter);
                }
            });
            JavaRDD<Row> rowRDD = weatherRDD.map(new Function<String, Row>() {
                public Row call(String record) throws Exception {
                    String[] attributes = record.split(",");
                    return RowFactory.create(attributes[0], Integer.valueOf(attributes[1]), Integer.valueOf(attributes[2]), Integer.valueOf(attributes[3]), Float.valueOf(attributes[4].trim()));
                }
            });
            Dataset<Row> parquetDF = spark.createDataFrame(rowRDD, schema);
            parquetDF.createOrReplaceTempView("weather");
            Dataset<Row> weatherDF = spark.sql(query);
//            Dataset<Row> weatherDF = spark.sql("SELECT location, month, avg(temperature) as temp FROM weather GROUP BY location, month ORDER BY month");
            List<Row> weatherList = weatherDF.collectAsList();
            String aJsons = "";
            for (Row row : weatherList) {
                String aJson = "{";
                aJson += "\"location\":\"" + row.get(0) + "\",\"month\":" + row.get(1) + ",\"temp\":" + row.get(2);
                aJson += "}";
                aJsons += aJson + ",";
            }
            aJsonArray = "[" + aJsons.substring(0, aJsons.length() - 1) + "]";
        } catch (Exception e) {
            System.out.print("错误信息: " + e.getMessage());
        }
        return aJsonArray;
    }
}
