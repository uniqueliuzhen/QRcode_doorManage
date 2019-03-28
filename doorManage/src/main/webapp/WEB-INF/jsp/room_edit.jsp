<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>修改机房</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.css" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/toastr.min.css" rel="stylesheet">
    <link href="../css/project.css" rel="stylesheet" >
    <link href="../css/fileinput.min.css" rel="stylesheet" >

    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <!-- 自定义js -->
    <script type="text/javascript" src="../js/content.js"></script>
    <script type="text/javascript" src="../js/toastr.min.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
	<script type="text/javascript" src="../js/fileinput.min.js" ></script>
    <script type="text/javascript" src="../js/zh.min.js" ></script>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="container-fluid" >
                                    <form role="form" class="form-horizontal">
                                        <div class="col-md-12">
                                            <div class="form-group" style="margin-top: 10px" >
                                                <h3 style="margin-right: 10px" >修改机房</h3>
                                            </div>
                                            <div class="hr-line-dashed"></div>
                                        </div>
                                        <div class="form-group" style="margin-top: 10px" >
                                            <label for="name" class="col-md-2 control-label" >名称*</label>
                                            <div class="col-md-9" >
                                                <input class="form-control" type="text" placeholder="请输入名称" id="name" >
                                            </div>
                                        </div>
										<div class="col-md-11">
                                            <div class="form-group" >
                                                <div class="col-md-5 control-label" style="float:right" >
                                                    <button type="button" class="btn btn-primary" id="save" >修改</button>
                                                    <button type="button" class="btn btn-white" style="margin-left: 20px" id="reset" >返回</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <script>
    	var user = JSON.parse($.cookie("user"));
   		var id = getQueryString("id");
    	$(function(){
    		$.ajax({
                type: "get",
                dataType: "json",
                url:"/doorManage/room/queryById",
                async:false,
                contentType: 'application/json',
                data: {
                	id:id
                },
                success: function (result){
                    if(null != result && "" != result && result.success){
                    	console.log(result.data.entity)
						$("#name").val(result.data.entity.roomName);                        
                    }else{
                        alert(result.msg);
                    }
                },
                error:function (result) {
                    toast("查询失败","查询失败","error");
                }
            })
    	})
    	
    	
        $("#save").click(function(){
            if(!$("#save").hasClass("disabled")){
                var name = $("#name").val();
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url:"/doorManage/room/update",
                    async:false,
                    contentType: 'application/json',
                    data: JSON.stringify({
                    	id:id,
                    	roomName:name
                    }),
                    success: function (result){
                        if(null != result && "" != result && result.success){
                            toast("修改成功","修改成功","success");
                            $("#save").addClass("disabled");
                        }else{
                            alert(result.msg);
                        }
                    },
                    error:function (result) {
                        toast("修改失败","修改失败","error");
                    }
                })
            }
        });

        $("#reset").on("click",function(){
        	self.location=document.referrer;
        });
		
    </script>

</body>

</html>
