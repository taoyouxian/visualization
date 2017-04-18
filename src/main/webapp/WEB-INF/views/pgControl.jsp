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
    <jsp:include page="../inc.jsp"></jsp:include>
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
                Control.Load();
            });
        </script>
    </head>
<body style="background-image:url('https://bce.bdstatic.com/portal/img/ffffff-0_d8974688.gif')">
<jsp:include page="../layout/top.jsp"></jsp:include>
<div style="padding-top:100px; padding-bottom:20px">
    <div class="container" style="min-height:600px">
        <div class="row">
            <div class="col-lg-7">
                <div class="divPan">
                    <div class="divPanHeader">
                        <div class="divTitle">
                            <span class="spanTitle pull-left"><i class="fa fa-tv"></i> Preview</span> &nbsp;[ <span
                                id="labCount">0</span> ]
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
                            <span class="spanTitle pull-left"><i class="fa fa-cogs"></i> Control Panel</span>
                        </div>
                        <div class="divMatchs pull-right" style="padding-top:8px; padding-right:15px">
                            <b style="color:red">Location Switch：</b>
                            <select id="cmbLocation" onchange="Control.doOnPickSQL()"></select>
                        </div>
                    </div>
                    <div class="divPanBody">
                        <div class="divResult">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="divScore">
                                        <textarea rows="4" class="form-control" id="txtSQL"></textarea>
                                    </div>
                                    <div class="divAcBtns clearfix">
                                        <span class="pull-right AcBtn">
                                                <button class="btn btn-danger btn-block" onclick="Control.doClear()">
                                                    <i class="fa fa-trash-o"></i> Clear
                                                </button>
                                            </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="divAction">
                            <button class="btn btn-block btn-success" onclick="Control.doExcute()">
                                <i class="fa fa-upload"></i> Upload
                            </button>
                        </div>
                    </div>
                    <div class="divPanFooter">
                    </div>
                </div>
                <div class="divPan">
                    <div class="divPanHeader clearfix">
                        <div class="divTitle pull-left">
                            <span class="spanTitle pull-left"><i class="fa fa-warning"></i> Background Message</span>
                        </div>
                    </div>
                    <div class="divPanBody">
                        <div class="divResult">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="divScore">
                                        <textarea rows="10" class="form-control" id="txtMsg"></textarea>
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
<jsp:include page="../layout/bottom.jsp"></jsp:include>
</body>
</html>