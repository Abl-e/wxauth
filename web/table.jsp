<%--
  User: 唐国翔
  Date: 2017/7/27 0027
  Time: 12:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>表格页面</title>
    <!--css样式-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-table.css" rel="stylesheet">
    <!--js-->
    <%--<script src="${pageContext.request.contextPath}/js/jquery.min-1.8.3.js" type="text/javascript"></script>--%>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table.js"></script>
    <script src="${pageContext.request.contextPath}/js/table.js"></script>
</head>
<body>
<div class="panel-body" style="padding-bottom:0px;">
    <table id="tb_departments"></table>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $('#tb_departments').bootstrapTable({
            url: '${pageContext.request.contextPath}/newsList',         //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            //toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "desc",                   //排序方式
            //queryParams: oTableInit.queryParams,//传递参数（*）
            queryParams:function(params){

            },
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [5,10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            //strictSearch: true,
            //showColumns: true,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //height: ,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: true,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            locale: "zh-CN",                   //中文支持
            columns: [{
                checkbox: false
            }, {
                field: 'id',
                title: '编号',
                align: 'center',
                sortable: true
            }, {
                field: 'account',
                title: '账户',
                align: 'center',
                sortable: true
            }, {
                field: 'password',
                title: '密码',
                align: 'center',
                sortable: true
            },{
                field: 'nickname',
                title: '昵称',
                align: 'center',
                sortable: true
            },{
                field: 'openid',
                title: 'openid',
                align: 'center',
                sortable: true
            }]
        });
    });



</script>
</html>