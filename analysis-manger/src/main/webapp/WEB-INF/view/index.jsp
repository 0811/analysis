<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/static" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>fores管理后台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${ctx}/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${ctx}/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <jsp:include page="header.jsp"/>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <jsp:include page="siderbar.jsp"/>

    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="box">
            <div class="col-md-12">
                <div class="box-header with-border">
                    <h3 class="box-title">今日概况</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered">
                        <%--<tr class="bg-light-blue disabled color-palette">--%>
                        <tr>
                            <th>指标</th>
                            <th>新增用户</th>
                            <th>活跃用户</th>
                            <th>新用户占比</th>
                            <th>启动(次数 | 人均)</th>
                            <th>平均使用时长</th>
                            <th>日活跃率</th>
                        </tr>

                        <tr>
                            <td>今日</td>
                            <td>${today_usageOverride.newNum}</td>
                            <td>${today_usageOverride.runNum}</td>
                            <td>6.9%</td>
                            <td>158878| 2.4</td>
                            <td>${today_usageOverride.duration}</td>
                            <td>- -</td>
                        </tr>
                        <tr>
                            <td>今日</td>
                            <td>4658</td>
                            <td>67515</td>
                            <td>6.9%</td>
                            <td>158878| 2.4</td>
                            <td>01:02</td>
                            <td>- -</td>
                        </tr>

                    </table>
                </div>
                <!-- /.box-body -->
            </div>


            <div class="col-md-6">
                <div class="box-header with-border">
                    <h3 class="box-title">应用摘要</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered">
                        <tr class="bg-light-blue disabled color-palette">
                            <th>指标</th>
                            <th>新增用户</th>
                            <th>活跃用户</th>
                            <th>新用户占比</th>
                            <th>启动(次数 | 人均)</th>
                            <th>平均使用时长</th>
                            <th>日活跃率</th>
                        </tr>

                        <tr>
                            <td>今日</td>
                            <td>4658</td>
                            <td>67515</td>
                            <td>6.9%</td>
                            <td>158878| 2.4</td>
                            <td>01:02</td>
                            <td>- -</td>
                        </tr>
                        <tr>
                            <td>今日</td>
                            <td>4658</td>
                            <td>67515</td>
                            <td>6.9%</td>
                            <td>158878| 2.4</td>
                            <td>01:02</td>
                            <td>- -</td>
                        </tr>

                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <div class="col-md-6">
                <div class="box-header with-border">
                    <h3 class="box-title">活跃概况</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered">
                        <tr class="bg-light-blue disabled color-palette">
                            <th>指标</th>
                            <th>新增用户</th>
                            <th>活跃用户</th>
                            <th>新用户占比</th>
                            <th>启动(次数 | 人均)</th>
                            <th>平均使用时长</th>
                            <th>日活跃率</th>
                        </tr>

                        <tr>
                            <td>今日</td>
                            <td>4658</td>
                            <td>67515</td>
                            <td>6.9%</td>
                            <td>158878| 2.4</td>
                            <td>01:02</td>
                            <td>- -</td>
                        </tr>
                        <tr>
                            <td>今日</td>
                            <td>4658</td>
                            <td>67515</td>
                            <td>6.9%</td>
                            <td>158878| 2.4</td>
                            <td>01:02</td>
                            <td>- -</td>
                        </tr>

                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="footer.jsp"/>

    <!-- Control Sidebar -->
    <jsp:include page="control-siderbar.jsp"/>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.0 -->
<script src="${ctx}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${ctx}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${ctx}/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${ctx}/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${ctx}/dist/js/demo.js"></script>
</body>
</html>
