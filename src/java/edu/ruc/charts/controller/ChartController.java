package edu.ruc.charts.controller;

import edu.ruc.charts.service.ChartMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/doExcute", method = RequestMethod.GET)
    public String doExcuteSQL(@RequestParam(value = "query") String arg) throws IOException {
        log.debug("Function Info: {}", "ChartController.doExcute");
        return chartMain.runInterfaceQuery(arg, "SQLSearch");
    }

    @RequestMapping("/index")
    public String viewChart() {
        return "pgControl";
    }
}
