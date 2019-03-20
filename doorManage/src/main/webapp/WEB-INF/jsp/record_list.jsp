<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>使用列表</title>
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
    <link rel="stylesheet" href="../css/plugins/datapicker/datepicker3.css">
    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <!-- 自定义js -->
    <script src="../js/content.js"></script>
    <script src="../js/toastr.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="../js/extendPagination.js" ></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/bootstrap-datepicker.js"></script>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="form-group col-md-12" style="margin-top: 10px" >
                                <h3 style="margin-right: 10px" >记录列表</h3>
                                <div class="hr-line-dashed"></div>
                            </div>
                            <div class="col-sm-12">
                                <form role="form" class="form-horizontal">
                                    <div class="form-group col-sm-10">
                                    	<label class="control-label" style="float: left;" >材料</label>
                                        <div class="col-sm-2"  >
                                        	<input type="text" id="material" class="form-control form-group-sm ">
                                        </div>
	                                   	<label class="control-label" style="float: left;" >用户</label>
                                        <div class="col-sm-2" >
                                        	<input type="text" id="user" class="form-control form-group-sm ">
                                        </div>
                                        <label class="col-sm-2 control-label" style="float: left;">时间范围</label>
			                            <div class="col-sm-4 input-daterange input-group" style="margin-left: 10px" id="datepicker">
			                                <input type="text" class="input-sm form-control" id="beginTime" />
			                                <span class="input-group-addon">到</span>
			                                <input type="text" class="input-sm form-control" id="endTime" />
			                            </div>
                                    </div>
                                    <div class="form-group col-sm-2" >
                                        <button class="btn btn-primary " type="button" id="search" ><strong>搜 索</strong></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <table class="table table-hover top-space txt_-center">
                            <thead>
                            <tr>
                                <th width="10% ">编号</th>
                                <th width="25%">材料</th>
                                <th width="20%">百分比</th>
                                <th width="25%">用户</th>
                                <th width="20%">时间</th>
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
                search(1);
                $('.input-daterange').datepicker({
                    keyboardNavigation: false,
                    forceParse: false,
                    autoclose: true
                });
                
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
                        var material = $("#material").val();
                        var user = $("#user").val();
                        var beginTime = $("#beginTime").val();
                        var endTime = $("#endTime").val();
                        
                        $.ajax({
                            type: "get",
                            dataType: "json",
                            url: url + '/record/queryPage',
                            async:false,
                            data: {
                                pageNum:pageNum,
                                pageSize:pageSize,
                                user:user,
                                material:material,
                                endTime:endTime,
                                beginTime:beginTime
                            },
                            success: function (result) {
                                if(null != result && "" != result && result.success){
                                	$.each(result.data.data,function (n,value) {
                                     var trs = "";
                                	 trs += '<tr><td>'+(n+1)+'</td><td style="word-wrap:break-word;word-break:break-all;" >'+ value.material +'</td><td>'+value.percent+'</td><td>'+value.user+'</td><td>'+value.ftime+'</td></tr>'
                                        $("tbody").append(trs);
                                    })
                                    allcount = result.data.rowCount;
                                    pageSize = result.data.size;
                                }else{
                                    alert(result.errorMsg);
                                }
                            }
                        });
                    }
                });
            }


         function search(num){
             $("tbody").html("");
             var material = $("#material").val();
             var user = $("#user").val();
             var beginTime = $("#beginTime").val();
             var endTime = $("#endTime").val();
             console.log(beginTime)
             $.ajax({
                 type: "get",
                 dataType: "json",
                 url: url + '/record/queryPage',
                 async:false,
                 data: {
                     pageNum:num,
                     pageSize:pageSize,
                     user:user,
                     material:material,
                     beginTime:beginTime,
                     endTime:endTime
                 },
                 success: function (result) {
                	 if(null != result && "" != result && result.success){
                         $.each(result.data.data,function (n,value) {
                             var trs = "";
                        	 trs += '<tr><td>'+(n+1)+'</td><td style="word-wrap:break-word;word-break:break-all;" >'+ value.material +'</td><td>'+value.percent+'</td><td>'+value.user+'</td><td>'+value.ftime+'</td></tr>'
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
           search(1);
        });

    </script>

</body>

</html>
