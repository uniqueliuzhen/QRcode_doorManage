<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>用户列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.css" rel="stylesheet">
    <link href="../css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="../css/project.css" rel="stylesheet">
    <link href="../css/fileinput.min.css" rel="stylesheet">

    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <!-- 自定义js -->
    <script src="../js/content.js"></script>

    <script src="../js/toastr.min.js"></script>

    <script type="text/javascript" charset="utf-8" src="../js/extendPagination.js" ></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script src="../js/fileinput.min.js" ></script>
    <script src="../js/zh.min.js" ></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
    <link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="form-group col-md-12" style="margin-top: 10px" >
                                <h3 style="margin-right: 10px" >用户列表</h3>
                                <div class="hr-line-dashed"></div>
                            </div>
                            <div class="col-sm-12">
                                <form role="form" class="form-inline">
                                    <div class="form-group col-sm-8">
                                        <label>用户名称</label>
                                        <input type="text" id="name" class="form-control form-group-sm margin-left10">
                                    </div>
                                    <a id="download" href=""></a>
                                    <div class="form-group col-sm-4" >
                                        <button class="btn btn-primary " type="button" id="search" ><strong>搜 索</strong></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <table class="table table-hover top-space txt_-center">
                            <thead>
                            <tr>
                                <th width="10% ">序号</th>
                                <th width="20%">用户名</th>
                                <th width="15%">账号</th>
                                <th width="15%">状态</th>
                                <th width="25%">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                            <div class="piece text-right margin-right50"><div id="callBackPager"></div></div><br>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
            var user = JSON.parse($.cookie("user"));
            var pageNum = 1;
            var pageSize = 10;
            var allcount;

            $(function () {
                var $toastlast;
                search();
            });

            function callBackPagination() {

                var totalCount = allcount, showCount = 10, limit = 10;

                $('#callBackPager').extendPagination({

                    totalCount: totalCount,

                    showCount: showCount,

                    limit: limit,

                    callback: function (curr, limit, totalCount) {
                        pageNum = curr;
                        $("tbody").html("");
                        var username = $("#name").val();
                        $.ajax({
                            type: "get",
                            dataType: "json",
                            url: url + '/user/queryPage',
                            async:false,
                            data: {
                                pageNum:pageNum,
                                pageSize:pageSize,
                                username:username
                            },
                            success: function (result) {
                                if(null != result && "" != result && result.success){
                                	$.each(result.data.pageInfo.list,function (n,value) {
                                        var trs = "";
                                        var stats = "";
                                        var status = "未审核";
                                        if(value.status == '2'){
                                            stats = "disabled";
                                            status = "已审核";
                                        }
                                        trs += '<tr><td>'+value.id+'</td><td style="word-wrap:break-word;word-break:break-all;" >'+ value.username +'</td><td>'+value.account+'</td><td>'+status+'</td><td><button class="btn btn-info" type="button" onclick="auth(&quot;'+value.id+'&quot;)" style="margin-right: 10px" '+stats+' ><i class="fa fa-paste padding-right5" ></i>审核</button><button class="btn btn-success" type="button" onclick="edit(&quot;'+value.id+'&quot;)" ><i class="fa fa-edit" ></i>修改</button><button class="btn btn-danger margin-left10" type="button" onclick="del(&quot;'+value.id+'&quot;)" ><i class="fa fa-eraser" ></i>删除</button></td></tr>'
                                        $("tbody").append(trs);
                                    })
                                    allcount = result.data.rowCount;
                                    pageSize = result.data.size;
                                    
                                }else{
                                    alert(result.msg);
                                }
                            }
                        });
                    }
                });
            }


         function search(){
             $("tbody").html("");
             var username = $("#name").val();
             $.ajax({
                 type: "get",
                 dataType: "json",
                 url: url + '/user/queryPage',
                 async:false,
                 data: {
                     pageNum:pageNum,
                     pageSize:pageSize,
                     username:username
                 },
                 success: function (result) {
                	 if(null != result && "" != result && result.success){
                         $.each(result.data.pageInfo.list,function (n,value) {
                             var trs = "";
                             var stats = "";
                             var status = "未审核";
                             if(value.status == '2'){
                                 stats = "disabled";
                                 status = "已审核";
                             }
                             trs += '<tr><td>'+value.id+'</td><td style="word-wrap:break-word;word-break:break-all;" >'+ value.username +'</td><td>'+value.account+'</td><td>'+status+'</td><td><button class="btn btn-info" type="button" onclick="auth(&quot;'+value.id+'&quot;)" style="margin-right: 10px" '+stats+' ><i class="fa fa-paste padding-right5" ></i>审核</button><button class="btn btn-success" type="button" onclick="edit(&quot;'+value.id+'&quot;)" ><i class="fa fa-edit" ></i>修改</button><button class="btn btn-danger margin-left10" type="button" onclick="del(&quot;'+value.id+'&quot;)" ><i class="fa fa-eraser" ></i>删除</button></td></tr>'
                             $("tbody").append(trs);
                         })
                         allcount = result.data.rowCount;
                         pageNum = result.data.pageNum;
                         pageSize = result.data.size;
                         callBackPagination();
                     }else{
                         alert(result.msg);
                     }
                 }
             });
         }

        $("#search").click(function () {
            var name = $("#name").val();
            if(null != $.trim(name) && "" != $.trim(name) ){
                search(1);
                $("#name").val(name);
            }else{
                search(1);
                $("#name").val("");
            }
        });
        
        function del(id){
        	$.ajax({
                type: "post",
                dataType: "json",
                url:"/doorManage/user/delete",
                data :{
                	id:id
                },
                async:false,
                success: function (result){
                    if(null != result && "" != result && 0 == result.status){
                    	toast("删除成功","删除成功","success");	
                    }else{
                        alert(result.msg);
                    }
                },
                error:function (result) {
                	toast("删除失败","删除失败","error");
                }
            });    	
        }
        
        function auth(id){
        	$.ajax({
                type: "post",
                dataType: "json",
                url:"/doorManage/user/auth",
                data :{
                	id:id
                },
                async:false,
                success: function (result){
                    if(null != result && "" != result && 0 == result.status){
                    	toast("审核成功","审核成功","success");	
                    }else{
                        alert(result.msg);
                    }
                },
                error:function (result) {
                	toast("审核失败","审核失败","error");
                }
            });    	
        }

        function edit(id){
        	location.href = "/electric/user/user_update?id="+id;
        }
        
    </script>

</body>

</html>
