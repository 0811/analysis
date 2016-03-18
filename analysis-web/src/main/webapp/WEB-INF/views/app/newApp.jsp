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
    <link rel="stylesheet" media="screen" href="css/bootstrap-admin-theme-change-size.css">

    <!-- Vendors -->
    <link rel="stylesheet" media="screen" href="${ctx}/resources/vendors/bootstrap-datepicker/css/datepicker.css">
    <link rel="stylesheet" media="screen" href="${ctx}/resources/css/datepicker.fixes.css">
    <link rel="stylesheet" media="screen"
          href="${ctx}/resources/vendors/uniform/themes/default/css/uniform.default.min.css">
    <link rel="stylesheet" media="screen" href="${ctx}/resources/css/uniform.default.fixes.css">
    <link rel="stylesheet" media="screen" href="${ctx}/resources/vendors/chosen.min.css">
    <link rel="stylesheet" media="screen" href="${ctx}/resources/vendors/selectize/dist/css/selectize.bootstrap3.css">
    <link rel="stylesheet" media="screen"
          href="${ctx}/resources/vendors/bootstrap-wysihtml5-rails-b3/vendor/assets/stylesheets/bootstrap-wysihtml5/core-b3.css">


    <%--<!-- Bootstrap Admin Theme -->--%>
    <%--<link rel="stylesheet" media="screen" href="${ctx}/resources/css/bootstrap-admin-theme.css">--%>
    <%--<link rel="stylesheet" media="screen" href="${ctx}/resources/css/bootstrap-admin-theme-change-size.css">--%>


    <![endif]-->
</head>
<body class="bootstrap-admin-with-small-navbar">
<!-- small navbar -->
<jsp:include page="../navbar.jsp"/>

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

        <jsp:include page="../menu.jsp"/>

        <!-- content -->
        <div class="col-md-10">

            <div class="col-lg-12">
                <div class="panel panel-default bootstrap-admin-no-table-panel">
                    <div class="panel-heading">
                        <div class="text-muted bootstrap-admin-box-title">新增应用</div>
                    </div>
                    <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                        <form action="/app/save" method="post" class="form-horizontal">
                            <fieldset>
                                <legend>新增应用</legend>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="name">应用名称</label>

                                    <div class="col-lg-10">
                                        <input class="form-control" id="name" name="name" type="text">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="info">应用描述</label>

                                    <div class="col-lg-10">
                                        <input class="form-control" id="info" name="info" type="text">
                                    </div>
                                </div>

                                <button type="submit" class="btn btn-primary">保存</button>
                                <button type="reset" class="btn btn-default">取消</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="../footer.jsp"/>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/twitter-bootstrap-hover-dropdown.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap-admin-theme-change-size.js"></script>
<script type="text/javascript" src="${ctx}/resources/vendors/easypiechart/jquery.easy-pie-chart.js"></script>


<script type="text/javascript">


</script>
</body>
</html>
