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
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">--%>
    <!-- Ionicons -->
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">--%>
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

    <jsp:include page="../header.jsp"/>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <jsp:include page="../siderbar.jsp"/>

    <!-- =============================================== -->


    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>app管理
                <small>查询app</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">app</a></li>
                <li class="active">list</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">


            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <form action="/app/saveOrUpdate" method="post">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">更新app</h4>
                            </div>
                            <div class="modal-body">
                                <div class="box box-default">
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label for="name">应用名</label>
                                            <input type="input" class="form-control" name="name" id="name"
                                                   placeholder="应用名">
                                        </div>


                                        <div class="form-group">
                                            <label for="info">应用描述</label>
                                            <input type="input" class="form-control" name="info" id="info"
                                                   placeholder="应用描述">
                                        </div>
                                        <input type="hidden" id="update-id" name="id" value=""/>
                                    </div>
                                    <!-- /.box-body -->


                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" id="saveApp" class="btn btn-primary">Save</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


            <div class="row">

                <div class="col-xs-12">


                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">app列表</h3>
                            <button type="button" class="btn btn-sm btn-info btn-flat pull-right" id="addModel"
                                    data-toggle="modal" data-target="#myModal">新增应用
                            </button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example" class="table table-bordered table-striped table-hover">
                                <thead>
                                <tr>
                                    <th>appId</th>
                                    <th>应用名</th>
                                    <th>描述</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="app" items="${appList}" varStatus="status">
                                <tr id="tr_${app.id}">
                                    <td>${app.id}</td>
                                    <td>${app.name}</td>
                                    <td>${app.info}</td>
                                    <td>${user.createAt}</td>
                                    <td>
                                        <button type="button" class="btn-primary" _id="${app.id}" id="updateModel"
                                                data-toggle="modal" data-target="#myModal">更新
                                        </button>
                                        <button type="button" class="btn-danger" _id="${app.id}">删除
                                        </button>
                                    </td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <!-- /.box-body -->

                    </div>
                    <!-- /.box -->


                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>

    </div>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->


</div>

<jsp:include page="../footer.jsp"/>

<!-- Control Sidebar -->
<jsp:include page="../control-siderbar.jsp"/>
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

<!-- DataTables -->
<script src="${ctx}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctx}/plugins/datatables/dataTables.bootstrap.min.js"></script>

<!-- SlimScroll -->
<script src="${ctx}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${ctx}/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${ctx}/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${ctx}/dist/js/demo.js"></script>
<script>
    $(function () {
        $("#example").DataTable();

        $('.btn-danger').on('click', function () {
            var id = $(this).attr("_id");
            delModel(id, "/app/del");
        });


        $('#myModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget) // Button that triggered the modal
            var id = button.attr('_id')
            var modal = $(this)
            $.ajax({
                type: "get",
                url: "/app/getAppById",
                data: "id=" + id,
                async: false,
                dataType: "json",
                success: function (data) {
                    modal.find('#update-id').val(data.id);
                    modal.find('#name').val(data.name);
                    modal.find('#info').val(data.info)
                }
            });

        })


        function delModel(id, url) {
            $.ajax({
                type: "post",
                url: url,
                data: "id=" + id,
                async: false,
                dataType: "json",
                success: function (data) {
                    if (data.code == '0') {
                        $('#tr_' + id).remove();
                    }
                }
            });
        }


    });
</script>
</body>
</html>
