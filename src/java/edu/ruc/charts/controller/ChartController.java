package edu.ruc.charts.controller;

import edu.ruc.charts.service.ChartMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @version V1.0
 * @Package: edu.ruc.charts.controller
 * @ClassName: ChartController
 * @Description: Chart的接入端
 * @author: Tao
 * @date: Create in 2017-04-16 14:52
 **/
@Controller
@RequestMapping("/Chart")
public class ChartController {
    private static Logger log = LoggerFactory.getLogger(ChartController.class);

    @Autowired
    private ChartMain chartMain;

    public ChartController() {
    }

    @ResponseBody
    @RequestMapping(value = "/doExcute", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String doExcuteSQL(@RequestParam(value = "query") String query, @RequestParam(value = "filter") String filter, @RequestParam(value = "action") String action) throws IOException {
        log.debug("Function Info: {}", "ChartController.doExcute");
        String aJson = "";
        if (action.equals("jdbc")) {
            aJson = chartMain.runJdbcQuery(query, filter);
        } else if (action.equals("tempView")) {
            aJson = chartMain.runViewQuery(query, filter);
        } else if (action.equals("parquet")) {
            aJson = chartMain.runParquetQuery(query, filter);
        } else {

        }
        return aJson;
    }

    @RequestMapping("/index")
    public String viewChart() {
        return "pgControl";
    }
}
