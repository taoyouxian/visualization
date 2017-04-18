<%--
  Created by IntelliJ IDEA.
  User: Tao
  Date: 2017/4/16
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
          name='viewport'/>
    <link href="../applibs/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet"/>
    <link href="../applibs/bootstrap-3.3.5-dist/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="../applibs/icons/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <script src="../applibs/sdk/jQuery-2.1.3.min.js" type="text/javascript"></script>
    <script src="../applibs/bootstrap-3.3.5-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <head>
        <title>Spark SQL to Charts</title>
        <style>
            body {
                background: #F1F2F6;
                margin: 0;
                padding: 0;
                background: #f5f5f5;
                color: #777;
                font: 13px/1.5 hiragino sans gb, microsoft yahei, simsun;
            }

            .AppFrame {
            }

            .AppFrame ul {
                list-style: none;
                padding: 0px;
                margin: 0px;
            }

            .AppFrame ul li {
                padding: 10px;
                border-bottom: 1px solid #dbdbdb;
            }

            .AppFrame ul li .chart {
                height: 400px;
            }
        </style>
        <script></script>
    </head>
<body>
<div class="AppFrame">
    <ul>
        <li>
            <button class="btn btn-block btn-sm btn-primary" onclick="Post()">
                Video Post 数据
            </button>
        </li>
        <li>
            <div class="col-xs-12">
                <div class="chart"></div>
            </div>
        </li>
    </ul>
</div>
<div id="divMain" class="text-red" style="padding:10px; margin-top:10px;">
    <span class=" label  label-danger">提交显示</span>
</div>
<div class="AppFrame">
    <ul>
        <li>
            <textarea rows="3" class="form-control" style="width:100%" placeholder="Video返回结果" id="divMsg"></textarea>
        </li>
    </ul>
</div>
</body>
</html>
