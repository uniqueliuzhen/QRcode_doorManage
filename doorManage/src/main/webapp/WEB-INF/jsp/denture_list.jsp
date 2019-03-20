<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>义齿物料出库</title>
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
    <link href="../css/plugins/ionRangeSlider/ion.rangeSlider.css" rel="stylesheet">
    <link href="../css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css" rel="stylesheet">
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
    <script src="../js/plugins/ionRangeSlider/ion.rangeSlider.min.js"></script>
    
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
                                <h3 style="margin-right: 10px" >义齿物料出库</h3>
                                <div class="hr-line-dashed"></div>
                            </div>
                            <div class="col-sm-12">
                                <form role="form" class="form-horizontal">
                                    <div class="form-group col-sm-10">
                                    	<label class="control-label" style="float: left;" >编号</label>
                                        <div class="col-sm-2"  >
                                        	<input type="text" id="no" class="form-control form-group-sm ">
                                        </div>
	                                   	<label class="control-label" style="float: left;" >百分比</label>
                                        <div class="col-sm-3 margin-left10" style="margin-top: -15px" >
                                        	<div style="float: left;" id="percent"></div>
                                        </div>
                                        <label class="control-label" style="float: left;" >材料</label>
                                        <div class="col-sm-2"  >
                                        	<input type="text" id="material" class="form-control form-group-sm ">
                                        </div>
                                        <label class="control-label" style="float: left;" >供应商</label>
                                        <div class="col-sm-2"  >
                                        	<input type="text" id="supplier" class="form-control form-group-sm ">
                                        </div>
                                        
                                    </div>
                                    <a id="download" href=""></a>
                                    <div class="form-group col-sm-2" >
                                        <button class="btn btn-primary " type="button" id="search" ><strong>搜 索</strong></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <table class="table table-hover top-space txt_-center">
                            <thead>
                            <tr>
                                <th width="10% ">序号</th>
                                <th width="10%">材料</th>
                                <th width="10%">百分比</th>
                                <th width="20%">供应商</th>
                                <th width="10%">位置</th>
                                <th width="40%">操作</th>
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

            $("#percent").ionRangeSlider({
                min: 0,
                max: 100,
                type: 'double',
                postfix: "%",
                prettify: false,
                hasGrid: true
            });
            
            $(function () {
                var $toastlast;
                $("#no").focus();
                search(1); 
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
                        var no = $("#no").val();
                        var material = $("#material").val();
                        var supplier = $("#supplier").val();
                        var percent = $("#percent").attr("value").split(";");
                        $.ajax({
                            type: "get",
                            dataType: "json",
                            url: url + '/denture/queryPage',
                            async:false,
                            data: {
                                pageNum:pageNum,
                                pageSize:pageSize,
                                percentStart:percent[0],
                                percentEnd:percent[1],
                                no:no,
                                material:material,
                                supplier:supplier
                            },
                            success: function (result) {
                                if(null != result && "" != result && result.success){
                                	$.each(result.data.data,function (n,value) {
                                        var trs = "";
                                        trs += '<tr><td>'+value.no+'</td><td>'+ value.material +'</td><td>'+ value.percent +'</td><td style="word-wrap:break-word;word-break:break-all;" >'+value.supplier+'</td><td>'+value.location+'</td><td><button class="btn btn-info role" type="button" onclick="edit(&quot;'+value.id+'&quot;)" style="margin-right: 10px" ><i class="fa fa-paste padding-right5" ></i>使用</button><button class="btn btn-primary role" type="button" onclick="update(&quot;'+value.id+'&quot;)" ><i class="fa fa-edit padding-right5" ></i>修改</button><button class="btn btn-danger margin-left10 role" type="button" onclick="del(&quot;'+value.id+'&quot;)" ><i class="fa fa-eraser" ></i>删除</button><button class="btn btn-info margin-left10" type="button" onclick="lister(&quot;'+value.picture+'&quot;)" ><i class="fa fa-paste padding-right5" ></i>图片详情</button></td></tr>'
                                        $("tbody").append(trs);
                                    })
                                    allcount = result.data.pageInfo.total;
                                    pageSize = result.data.pageInfo.pageSize;
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
             var no = $("#no").val();
             var material = $("#material").val();
             var supplier = $("#supplier").val();
             var percent = $("#percent").attr("value").split(";");
             $.ajax({
                 type: "get",
                 dataType: "json",
                 url: url + '/denture/queryPage',
                 async:false,
                 data: {
                     pageNum:num,
                     pageSize:pageSize,
                     percentStart:percent[0],
                     percentEnd:percent[1],
                     no:no,
                     material:material,
                     supplier:supplier
                 },
                 success: function (result) {
                	 if(null != result && "" != result && result.success){
                         $.each(result.data.data,function (n,value) {
                             var trs = "";
                             trs += '<tr><td>'+value.no+'</td><td>'+ value.material +'</td><td>'+ value.percent +'</td><td style="word-wrap:break-word;word-break:break-all;" >'+value.supplier+'</td><td>'+value.location+'</td><td><button class="btn btn-info role" type="button" onclick="edit(&quot;'+value.id+'&quot;)" style="margin-right: 10px" ><i class="fa fa-paste padding-right5" ></i>使用</button><button class="btn btn-primary role" type="button" onclick="update(&quot;'+value.id+'&quot;)" ><i class="fa fa-edit padding-right5" ></i>修改</button><button class="btn btn-danger margin-left10 role" type="button" onclick="del(&quot;'+value.id+'&quot;)" ><i class="fa fa-eraser" ></i>删除</button><button class="btn btn-info margin-left10" type="button" onclick="lister(&quot;'+value.picture+'&quot;)" ><i class="fa fa-paste padding-right5" ></i>图片详情</button></td></tr>'
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

        function edit(id) {
            location.href = "/Manage/denture/denture_update?id="+id;
        }

        function update(id) {
            location.href = "/Manage/denture/denture_edit?id="+id;
        }
        
        $("#save").click(function () {
            location.href = "/Manage/album/album_add";
        });

        function lister(file){
        	window.open(url + "/file/" + file);
        }

        function del(id){
        	console.log(id)
        	$.ajax({
                type: "get",
                dataType: "json",
                url: url + '/denture/delete',
                async:false,
                data: {
                    id:id
                },
                success: function (result) {
                	console.log(result)
                	if(null != result && "" != result && 0 == result.status){
                		alert("删除成功！");
                		window.location.reload();
                	}else{
                		alert("删除失败！");
                	}
                },
                error:function(){
                	alert("删除失败！");
                }
            });
        }
        
        
    </script>

</body>

</html>
