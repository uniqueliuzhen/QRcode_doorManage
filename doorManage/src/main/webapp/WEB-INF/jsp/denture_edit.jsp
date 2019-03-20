<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>义齿物料信息修改</title>
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
                                                <h3 style="margin-right: 10px" >义齿物料信息修改</h3>
                                            </div>
                                            <div class="hr-line-dashed"></div>
                                        </div>
                                        <div class="form-group" style="margin-top: 10px" >
                                            <label for="no" class="col-md-2 control-label" >编号*</label>
                                            <div class="col-md-9" >
                                                <input class="form-control" type="text" placeholder="请输入义齿材料编号" id="no" >
                                            </div>
                                        </div>
                                        <div class="form-group" style="margin-top: 10px" >
                                            <label for="material" class="col-md-2 control-label" >材料*</label>
                                            <div class="col-md-3" >
                                                <input class="form-control" type="text" placeholder="请输入材料" id="material" >
                                            </div>
                                            <label for="supplier" class="col-md-2 control-label" >供应商*</label>
                                            <div class="col-md-4" >
                                                <input class="form-control" type="text" placeholder="请输入供应商" id="supplier" >
                                            </div>
                                        </div>
										<div class="form-group" style="margin-top: 10px">
											<label for="location" class="col-md-2 control-label" >所在位置*</label>
                                            <div class="col-md-3" >
                                                <input class="form-control" type="text" placeholder="请输入所在位置" id="location" >
                                            </div>
                                            <label for="mobile" class="col-md-2 control-label" >百分比*</label>
                                            <!-- <div class="col-md-2" >
                                                <input class="form-control" type="text" placeholder="请输入百分比" id="percentOld" >
                                            </div> -->
                                            <div class="col-md-4" >
                                                <input class="form-control" type="text" placeholder="请输入百分比" id="percentNew" >
                                            </div>
										</div>

										<div class="form-group" style="margin-top: 10px">
											<label for="meetingTime" class="col-md-2 control-label">上传照片*</label>
											<div class="col-md-9">
												<input id="upload" type="file" name="file" class="form-control">
											</div>
											<input id="images" type="hidden" >
										</div>

										<div class="col-md-11">
                                            <div class="form-group" >
                                                <div class="col-md-3 control-label" style="float:right" >
                                                    <button type="button" class="btn btn-primary" id="save" >确定修改</button>
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
    	var user = JSON.parse($.cookie("user"));
    	var result_array = [];
    	var array = [];
        $(document).ready(function () {
        	
        	Array.prototype.remove=function(obj){
                for(var i =0;i <this.length;i++){
                    var temp = this[i];
                    if(!isNaN(obj)){
                        temp=i;
                    }
                    if(temp == obj){
                        for(var j = i;j <this.length;j++){
                            this[j]=this[j+1];
                        }
                        this.length = this.length-1;
                    }
                }
            }
        	
            $(".kv-fileinput-caption").css("margin-bottom","3px");
            $(".btn-file").css("margin-left","5px");
            	
            $.ajax({
                type: "get",
                dataType: "json",
                url:"/Manage/denture/queryById",
                async:false,
                data: {
                    id:id
                },
                success: function (result){
                    if(null != result && "" != result && 0 == result.status){
                    	$("#no").val(result.data.no);
                    	$("#material").val(result.data.material);
                    	$("#supplier").val(result.data.supplier);
                    	$("#location").val(result.data.location);
                    	$("#percentNew").val(result.data.percent);
                    	$("#images").val(result.data.picture);
                    	if(!(result.data.percent > 0)){
                    		$("#save").addClass("disabled");
                    	}
                    }else{
                        alert(result.msg);
                    }
                },
                error:function (result) {
                    toast("查询失败","查询失败","error");
                }
            })
        });
        
        $("#upload").fileinput({
            language: 'zh', //设置语言
			uploadUrl: "/Manage/user/upload", //上传的地址(访问接口地址)
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            uploadAsync: true, //默认异步上传
            showUpload: false, //是否显示上传按钮
            showRemove : false, //显示移除按钮
            showPreview : true, //是否显示预览
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary mybtn", //按钮样式
            autoReplace:false,
            dropZoneEnabled: false,//是否显示拖拽区域
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
        }).on("filebatchselected", function(event, files) {
            $(this).fileinput("upload");
        }).on("fileuploaded", function(event, data,previewId,index) {
            filename = data.response.data;  //文件上传成功返回的文件名，可返回自定义文件名
        }).on("filesuccessremove",function(event,id){
            array.push(id);
        }).on("filebatchuploadcomplete",function(event, files, extra){
            var _array = $(".file-preview-success");
            result_array.push({ file: filename, id: $(_array[_array.length - 1]).attr("id") })
        });


        $("#save").click(function(){
            if(!$("#save").hasClass("disabled")){
                var no = $("#no").val();
				var material = $("#material").val();
				var supplier = $("#supplier").val();
				var location = $("#location").val();
				var percentNew = $("#percentNew").val();
				
                for (var i = 0; i < result_array.length; i++) {
                    for (var y = 0; y < array.length; y++) {
                        if(result_array[i].id == array[y]){
                            result_array.remove(i);
                        }
                    }
                }
                
                var images;
                if(result_array.length > 0){
                	images = result_array[0].file;	
                }else{
                	images = $("#images").val();
                }

                if(!percentNew){
                	$("percentNew").focus();
                	alert("输入百分比");
                	return;
                }
                
                
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url:"/Manage/denture/update",
                    async:false,
                    contentType: 'application/json',
                    data: JSON.stringify({
                    	percent:percentNew,
                    	location:location,
                    	material:material,
                    	supplier:supplier,
                    	no:no,
                    	id:id,
                        picture:images,
                        status:1
                    }),
                    success: function (result){
                        if(null != result && "" != result && 0 == result.status){
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
        	self.location = url + "/denture/denture_list"; 
        });
    </script>

</body>

</html>
