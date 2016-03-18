<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<html>
<head>
    <title>统计分析</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link rel="stylesheet" media="screen" href="${ctx}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" media="screen" href="${ctx}/resources/css/bootstrap-theme.min.css">

    <!-- Bootstrap Admin Theme -->
    <link rel="stylesheet" media="screen" href="${ctx}/resources/css/bootstrap-admin-theme.css">
    <link rel="stylesheet" media="screen" href="${ctx}/resources/css/bootstrap-admin-theme-change-size.css">

    <!-- Vendors -->
    <link rel="stylesheet" media="screen" href="${ctx}/resources/vendors/easypiechart/jquery.easy-pie-chart.css">
    <link rel="stylesheet" media="screen" href="${ctx}/resources/vendors/easypiechart/jquery.easy-pie-chart_custom.css">
    <script type="text/javascript" src="${ctx}/resources/js/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="bootstrap-admin-with-small-navbar">
<!-- small navbar -->
<jsp:include page="navbar.jsp"/>

<!-- main / large navbar -->
<nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
     role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".main-navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="about.html">统计分析</a>
                </div>
                <div class="collapse navbar-collapse main-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-hover="dropdown">sample1<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li role="presentation" class="dropdown-header">sample1</li>
                                <li><a href="#">sample1</a></li>
                                <li><a href="#">sample2</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-hover="dropdown">IOS<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li role="presentation" class="dropdown-header">IOS</li>
                                <li><a href="#">android</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
        </div>
    </div>
    <!-- /.container -->
</nav>

<div class="container">
    <!-- left, vertical navbar & content -->
    <div class="row">

        <jsp:include page="menu.jsp"/>

        <!-- content -->
        <div class="col-md-10">

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">今日数据</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>新增用户</th>
                                    <th>活跃用户</th>
                                    <th>新增用户占比</th>
                                    <th>启动次数</th>
                                    <th>平均单次使用时长</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>今日数据</td>
                                    <td>${today_usageOverride.newNum}</td>
                                    <td>${today_usageOverride.activeNum}</td>
                                    <td>${today_usageOverride.newNum}</td>
                                    <td>${today_usageOverride.runNum}</td>
                                    <td>${today_usageOverride.duration}</td>
                                </tr>
                                <tr>
                                    <td>昨日数据</td>
                                    <td>${yest_usageOverride.newNum}</td>
                                    <td>${yest_usageOverride.activeNum}</td>
                                    <td>${yest_usageOverride.newNum}</td>
                                    <td>${yest_usageOverride.runNum}</td>
                                    <td>${yest_usageOverride.duration}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">时段分析</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                                <button type="button" class="btn btn-default"
                                        onclick="getLineChart('usageHourlyDiv','newNumHourly.json')">新增用户
                                </button>
                                <button type="button" class="btn btn-default"
                                        onclick="getLineChart('usageHourlyDiv','runNumHourly.json')">启动次数
                                </button>
                                <button type="button" class="btn btn-default"
                                        onclick="getLineChart('usageHourlyDiv','activeNumHourly.json')">累计日活
                                </button>
                                <button type="button" class="btn btn-default"
                                        onclick="getLineChart('usageHourlyDiv','activeActiveHourly.json')">活跃用户
                                </button>
                            </div>
                            <br>
                            <br>

                            <div id="usageHourlyDiv" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">整体趋势</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                                <button type="button" class="btn btn-default"
                                        onclick="getDailyLineChart('usageDailyDiv','newNumDaily.json')">新增用户
                                </button>
                                <button type="button" class="btn btn-default"
                                        onclick="getDailyLineChart('usageDailyDiv','runNumDaily.json')">启动次数
                                </button>
                                <button type="button" class="btn btn-default">累计用户</button>
                                <button type="button" class="btn btn-default"
                                        onclick="getDailyLineChart('usageDailyDiv','activeNumDaily.json')">活跃用户
                                </button>
                                <button type="button" class="btn btn-default">平均使用时长</button>
                            </div>
                            <br>
                            <br>

                            <div id="usageDailyDiv" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="footer.jsp"/>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/twitter-bootstrap-hover-dropdown.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-admin-theme-change-size.js"></script>
<script type="text/javascript" src="${ctx}/resources/vendors/easypiechart/jquery.easy-pie-chart.js"></script>

<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/custom-chart.js"></script>
<script type="text/javascript">
    $(function () {
        $('.easyPieChart').easyPieChart({animate: 1000});
    });

    $(document).ready(function () {
        getLineChart("usageHourlyDiv", "runNumHourly.json")
        getDailyLineChart("usageDailyDiv", "runNumDaily.json")

    });

</script>
</body>
</html>