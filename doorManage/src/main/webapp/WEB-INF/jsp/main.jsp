<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>门禁管理系统 - 主页</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="../css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <!--<i class="fa fa-area-chart"></i>-->
                                        <strong class="font-bold">门禁管理系统</strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                    </li>
                    <li class="msg" >
                        <a href="#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">机房管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/doorManage/room/room_list">机房列表</a>
                            </li>
                        </ul>
                    </li>
                    <li class="msg" id="user" >
                        <a href="#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">用户管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem role" href="/doorManage/user/user_list">用户列表</a>
                            </li>
                        </ul>
                    </li>
                    <li class="line dk msg"></li>
                </ul>
            </div>
        </nav>
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>

                    </div>

                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" id="username" href="#">

                            </a>
                        </li>

                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#" id="loginOut" >
                                退出
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" src="/doorManage/user/index_v1" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/plugins/jquery.metisMenu.js"></script>
    <script src="../js/plugins/jquery.slimscroll.min.js"></script>
    <script src="../js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="../js/hAdmin.js"></script>
    <script type="text/javascript" src="../js/index.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>

    <script>
        $(document).ready(function () {
            var user = JSON.parse($.cookie("user"));
            if(null == user || null == user.username){
                window.location.href = "/doorManage";
            }else {
                $("#username").text(user.username);
            }
	 		
            if(user.id == 1){
            	$("#role").attr("href","/doorManage/user/role_msg");
            }else{
            	$(".role").hide();
            }
            
        });

        $("#loginOut").click(function () {
        	$.cookie('user','',{expires:-1,path:'/'});
            window.location.href = "/doorManage";
        })


    </script>

</body>

</html>
