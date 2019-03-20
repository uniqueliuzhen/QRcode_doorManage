<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>查看会员</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.css" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/toastr.min.css" rel="stylesheet">
    <link href="../css/project.css" rel="stylesheet" >
    <link href="../css/fileinput.min.css" rel="stylesheet" >
    <link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css">
    

    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

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
                                                <h3 style="margin-right: 10px" >查看会员</h3>
                                            </div>
                                            <div class="hr-line-dashed"></div>
                                        </div>
                                        <div class="form-group" style="margin-top: 10px" >
                                            <label for="username" class="col-md-2 control-label" >用户名*</label>
                                            <div class="col-md-9" >
                                                <input class="form-control" type="text" placeholder="请输入用户名" id="username" >
                                            </div>
                                        </div>
                                        <div class="form-group" style="margin-top: 10px" >
                                            <label for="certid" class="col-md-2 control-label" >身份证号*</label>
                                            <div class="col-md-9" >
                                                <input class="form-control" type="text" placeholder="请输入身份证号" id="certid" >
                                            </div>
                                        </div>
                                        <div class="form-group" style="margin-top: 10px" >
                                            <label for="gender" class="col-md-2 control-label" >性别*</label>
                                            <div class="col-md-4" >
                                                <select class="form-control" id="gender" >
											      <option value="男" >男</option>
											      <option value="女" >女</option>
											    </select>
                                            </div>
                                            <label for="birth" class="col-md-2 control-label" >出生日期*</label>
                                            <div class="col-md-3" >
                                                <input class="form-control" type="text" placeholder="请输入出生日期" id="birth" >
                                            </div>
                                        </div>
                                        <div class="form-group" style="margin-top: 10px" >
                                            <label for="place" class="col-md-2 control-label" >籍贯*</label>
                                            <div class="col-md-9" >
                                                <input class="form-control" type="text" placeholder="请输入籍贯" id="place" >
                                            </div>
                                        </div>

										<div class="col-md-11">
                                            <div class="form-group" >
                                                <div class="col-md-3 control-label" style="float:right" >
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
		
    	var id = getQueryString("id");
    	
    	$(function(){
    		
    		$.ajax({
                type: "post",
                dataType: "json",
                url:"/Onlinemusic/user/queryById",
                async:false,
                data:{
                	id:id
                },
                success: function (result){
                    if(null != result && "" != result && 0 == result.status){
    					$("#username").val(result.data.username)                	
    					$("#certid").val(result.data.certid)                	
    					$("#birth").val(result.data.birth)                	
    					$("#place").val(result.data.place)                	
    					$("#gender option[value='"+result.data.gender+"']").attr("selected",true);
                    }else{
                        alert(result.msg);
                    }
                },
                error:function (result) {
                    toast("查询失败","查询失败","error");
                }
            });
    	});

        $("#reset").on("click",function(){
        	window.location.href = history.go(-1);
        });

    </script>

</body>

</html>
