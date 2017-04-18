<%--
  Created by IntelliJ IDEA.
  User: Tao
  Date: 2017/4/16
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
          name='viewport'/>
    <link rel="shortcut icon" type="image/x-icon"
          href="../statics/image/hhuc.ico"/>
    <link href="../statics/applibs/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet"/>
    <link href="../statics/applibs/bootstrap-3.3.5-dist/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="../statics/applibs/icons/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="../statics/css/common/admin.css" rel="stylesheet" type="text/css"/>
    <link href="../statics/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="../statics/applibs/sdk/jQuery-2.1.3.min.js" type="text/javascript"></script>
    <script src="../statics/applibs/bootstrap-3.3.5-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../statics/js/charts/echarts.min.js" type="text/javascript"></script>
    <script src="../statics/js/charts/macarons.js" type="text/javascript"></script>
    <script src="../statics/js/charts/echarts-wordcloud.min.js" type="text/javascript"></script>
    <script src="../statics/js/Chart/Chart.js"></script>
    <script src="../statics/js/Control/Control.js?v=1"></script>
    <head>
        <title>Spark SQL to Charts</title>
        <style>
            body {
                padding: 0px;
                margin: 0px;
                background-color: #f0f0f0;
                color: #666;
                font-family: 微软雅黑, Verdana;
                font-size: 14px
            }

            a, a:link, a:visited, a:active {
                color: #1a9eff;
                text-decoration: none;
            }

            a:hover {
                cursor: pointer;
                color: #0d78c7;
                text-decoration: none;
                font-weight: bold;
            }
        </style>
        <script>
            $(document).ready(function () {

            });
        </script>
    </head>
<body style="background-image:url('https://bce.bdstatic.com/portal/img/ffffff-0_d8974688.gif')">
<div class="divTop">
    <div class="container">
        <div class="divNav clearfix">
            <div class="pull-left divLogo">
                <img src="../statics/image/hhucLogo.png"
                     style="border-radius:60px; width:60px; height:60px; background-color:#fff; "/>
            </div>
            <div class="pull-left divCaption">
                <div style="">Echarts视图控制台</div>
                <div style=" font-size:0.5em">Fast-Forwarding to Desired Visualization with SQL</div>
            </div>
        </div>
    </div>
    <div class="divSplit" style="border-top:1px solid #ddd; border-bottom:1px solid #fff"></div>
</div>
<div style="padding-top:100px; padding-bottom:20px">
    <div class="container" style="min-height:600px">
        <div class="row">
            <div class="col-lg-7">
                <div class="divPan">
                    <div class="divPanHeader">
                        <div class="divTitle">
                            <span class="spanTitle pull-left"><i class="fa fa-tv"></i> 预览</span> &nbsp;[ <span
                                id="labCount"></span> ]
                        </div>
                    </div>
                    <div class="divPanBody">
                        <div class="divPreview"></div>
                    </div>
                    <div class="divPanFooter"></div>
                </div>
            </div>
            <div class="col-lg-5">
                <div class="divPan">
                    <div class="divPanHeader clearfix">
                        <div class="divTitle pull-left">
                            <span class="spanTitle pull-left"><i class="fa fa-cogs"></i> 控制面板</span>
                        </div>
                        <div class="divMatchs pull-right" style="padding-top:8px; padding-right:15px">
                            <b style="color:red">类型切换：</b>
                            <select id="cmbMatch" onchange="Control.doOnPickSQL()"></select>
                        </div>
                    </div>
                    <div class="divPanBody">
                        <div class="divResult">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="divScore">
                                        <textarea rows="6" class="form-control" id="txtSQL"></textarea>
                                    </div>
                                    <div class="divAcBtns clearfix">
                                        <span class="pull-right AcBtn">
                                                <button class="btn btn-danger btn-block" onclick="Control.doClear()">
                                                    <i class="fa fa-trash-o"></i> 清空
                                                </button>
                                            </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="divAction">
                            <button class="btn btn-block btn-success" onclick="Control.doExcute()">
                                <i class="fa fa-upload"></i> 提交
                            </button>
                        </div>
                    </div>
                    <div class="divPanFooter">
                    </div>
                </div>
                <div class="divPan">
                    <div class="divPanHeader clearfix">
                        <div class="divTitle pull-left">
                            <span class="spanTitle pull-left"><i class="fa fa-warning"></i> 后台消息</span>
                        </div>
                    </div>
                    <div class="divPanBody">
                        <div class="divResult">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="divScore">
                                        <textarea rows="12" class="form-control" id="txtMsg"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="divBottom">
    <div class="container">
        <div class="col-lg-9 col-md-9">
            <div class="divMas"><i class="fa fa-rss-square"></i> 技术团队</div>
            <div class="divDtl">
                Copyright © 2017 www.hhuc.edu.cn Inc. All Rights Reserved. 河海大学物联网工程学院
            </div>
        </div>
        <div class="col-lg-3 col-md-3">
            <div class="divMas"><i class="fa fa-qrcode"></i> 微信支持</div>
            <div class="divDtl">
                <img style="width:100%" src="../statics/image/wxpay.png"/>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>