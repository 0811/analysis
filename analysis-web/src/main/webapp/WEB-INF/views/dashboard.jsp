<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/resources/js/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="bootstrap-admin-with-small-navbar">
<!-- small navbar -->
<nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar-sm" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left bootstrap-admin-theme-change-size">
                        <li class="text">Change size:</li>
                        <li><a class="size-changer small">Small</a></li>
                        <li><a class="size-changer large active">Large</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li>
                            <a href="#">Reminders <i class="glyphicon glyphicon-bell"></i></a>
                        </li>
                        <li>
                            <a href="#">Settings <i class="glyphicon glyphicon-cog"></i></a>
                        </li>
                        <li>
                            <a href="#">Go to frontend <i class="glyphicon glyphicon-share-alt"></i></a>
                        </li>
                        <li class="dropdown">
                            <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i
                                    class="glyphicon glyphicon-user"></i> Username <i class="caret"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="presentation" class="divider"></li>
                                <li><a href="index.html">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

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
                        <li class="active"><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-hover="dropdown">Dropdown <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li role="presentation" class="dropdown-header">Dropdown header</li>
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="presentation" class="divider"></li>
                                <li role="presentation" class="dropdown-header">Dropdown header</li>
                                <li><a href="#">Separated link</a></li>
                                <li><a href="#">One more separated link</a></li>
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
<!-- left, vertical navbar -->
<div class="col-md-2 bootstrap-admin-col-left">
    <ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">

        <li class="active">
            <a href="dashboard.html"><i class="glyphicon glyphicon-chevron-right"></i> 概况</a>
        </li>
        <li >
            <a href="#"><i class="glyphicon glyphicon-chevron-right"></i> 应用趋势</a>
        </li>
        <li >
            <a href="#"><i class="glyphicon glyphicon-chevron-right"></i> 留存用户分析</a>
        </li>
        <li>
            <a href="forms.html"><i class="glyphicon glyphicon-chevron-right"></i>渠道分析</a>
        </li>
        <li>
            <a href="tables.html"><i class="glyphicon glyphicon-chevron-right"></i>用户参与度</a>
        </li>
        <li>
            <a href="buttons-and-icons.html"><i class="glyphicon glyphicon-chevron-right"></i> 终端属性</a>
        </li>
        <li>
            <a href="wysiwyg-editors.html"><i class="glyphicon glyphicon-chevron-right"></i> 错误分析</a>
        </li>
        <li>
            <a href="ui-and-interface.html"><i class="glyphicon glyphicon-chevron-right"></i> UI &amp; Interface</a>
        </li>
        <li>
            <a href="error-pages.html"><i class="glyphicon glyphicon-chevron-right"></i> Error pages</a>
        </li>
        <li>
            <a href="#"><span class="badge pull-right">83</span> Errors</a>
        </li>
        <li>
            <a href="#"><span class="badge pull-right">4,231</span> Logs</a>
        </li>
    </ul>
</div>

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
                        <th>#</th>
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
                        <button type="button" class="btn btn-default">新增用户</button>
                        <button type="button" class="btn btn-default">启动次数</button>
                        <button type="button" class="btn btn-default">累计日活</button>
                        <button type="button" class="btn btn-default">活跃用户</button>
                    </div>
                    <br>
                    <br>
                    <div id="chartDemo" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                </div>
            </div>
        </div>
    </div>




<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default bootstrap-admin-no-table-panel">
            <div class="panel-heading">
                <div class="text-muted bootstrap-admin-box-title">Statistics</div>
                <div class="pull-right"><span class="badge">View More</span></div>
            </div>
            <div class="bootstrap-admin-panel-content bootstrap-admin-no-table-panel-content collapse in">
                <div class="col-md-3">
                    <div class="easyPieChart" data-percent="73"
                         style="width: 110px; height: 110px; line-height: 110px;">73%
                        <canvas width="110" height="110"></canvas>
                    </div>
                    <div class="chart-bottom-heading"><span class="label label-info">Visitors</span></div>
                </div>
                <div class="col-md-3">
                    <div class="easyPieChart" data-percent="53"
                         style="width: 110px; height: 110px; line-height: 110px;">53%
                        <canvas width="110" height="110"></canvas>
                    </div>
                    <div class="chart-bottom-heading"><span class="label label-info">Page Views</span></div>
                </div>
                <div class="col-md-3">
                    <div class="easyPieChart" data-percent="83"
                         style="width: 110px; height: 110px; line-height: 110px;">83%
                        <canvas width="110" height="110"></canvas>
                    </div>
                    <div class="chart-bottom-heading"><span class="label label-info">Users</span></div>
                </div>
                <div class="col-md-3">
                    <div class="easyPieChart" data-percent="13"
                         style="width: 110px; height: 110px; line-height: 110px;">13%
                        <canvas width="110" height="110"></canvas>
                    </div>
                    <div class="chart-bottom-heading"><span class="label label-info">Orders</span></div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="text-muted bootstrap-admin-box-title">Panel without data</div>
                <div class="pull-right"><span class="badge">0</span></div>
            </div>
            <div class="panel-body">
                <div class="no-data">
                    Sorry, no data to display
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>

<!-- footer -->
<div class="navbar navbar-footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <footer role="contentinfo">
                    <p class="left">Bootstrap 3.x Admin Theme</p>

                    <p class="right">&copy; 2013 <a href="http://www.meritoo.pl" target="_blank">Meritoo.pl</a></p>
                </footer>
            </div>
        </div>
    </div>
</div>

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
        // Easy pie charts
        $('.easyPieChart').easyPieChart({animate: 1000});
    });

  $(document).ready(function() {
      getLineChart("chartDemo","runNumHourly.json")

  });

</script>
</body>
</html>