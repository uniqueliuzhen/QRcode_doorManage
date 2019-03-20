<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>义齿物料入库</title>
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
                                                <h3 style="margin-right: 10px" >义齿物料入库</h3>
                                            </div>
                                            <div class="hr-line-dashed"></div>
                                        </div>
                                        <div class="form-group" style="margin-top: 10px" >
                                            <label for="no" class="col-md-2 control-label" >编号*</label>
                                            <div class="col-md-9" >
                                                <input class="form-control" type="text" placeholder="请输入编号" id="no" >
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
                                            <div class="col-md-4" >
                                                <input class="form-control" type="text" placeholder="请输入百分比" value="100" id="percent" >
                                            </div>
                                            
										</div>

										<div class="form-group" style="margin-top: 10px">
											<label for="meetingTime" class="col-md-2 control-label">上传照片*</label>
											<div class="col-md-9">
												<input id="upload" type="file" name="file" class="form-control">
											</div>
										</div>
										<div class="col-md-11">
                                            <div class="form-group" >
                                                <div class="col-md-5 control-label" style="float:right" >
                                                    <button type="button" class="btn btn-primary" id="save" >添加</button>
                                                    <button type="button" class="btn btn-primary" style="margin-left: 20px" id="save_batch" data-toggle="modal" data-target="#myModal" >批量添加</button>
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
    
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-md">
            <div class="modal-content">
                <form action="#" class="m-t ">
                    <div class="modal-footer del-bottom modal-body" >
                        <div class="margin-bottom20" style="overflow: hidden;">
                        	<input id="uploadfile" type="file" name="file" class="form-control" style="width: 50%;float: left;" >
                        </div>
						<a id="download" ></a>
                        <button type="button" class="btn btn-info" id="t_save" >下载例子</button>
                        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    
    <script>
    	var user = JSON.parse($.cookie("user"));
    	var result_array = [];
    	var array = [];
    	
    	$("#uploadfile").fileinput({
            language: 'zh', //设置语言
            uploadUrl: "/Manage/denture/uploadbatch?userid="+user.id, //上传的地址(访问接口地址)
            allowedFileExtensions: ['xls','xlsx'],//接收的文件后缀
            uploadAsync: true, //默认异步上传
            showUpload: false, //是否显示上传按钮
            showRemove : false, //显示移除按钮
            showPreview : false, //是否显示预览
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        }).on("filebatchselected", function(event, files) {
        	$('#uploadfile').fileinput('upload');//上传操作
        }).on("fileuploaded", function(event, data) {
        	$("#uploadfile").val("");
        	$('#myModal').modal('hide');
        	toast("批量上传成功","批量上传成功","success");
        });
    	
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
				var percent = $("#percent").val();
                
                for (var i = 0; i < result_array.length; i++) {
                    for (var y = 0; y < array.length; y++) {
                        if(result_array[i].id == array[y]){
                            result_array.remove(i);
                        }
                    }
                }
                
                var images = result_array[0].file;
                /* for (var i = 0; i < result_array.length; i++) {
                    images += result_array[i].file + ",";
                } */

                $.ajax({
                    type: "post",
                    dataType: "json",
                    url:"/Manage/denture/save",
                    async:false,
                    contentType: 'application/json',
                    data: JSON.stringify({
                    	no:no,
                    	material:material,
                    	supplier:supplier,
                    	location:location,
                    	percent:percent,
                        picture:images
                    }),
                    success: function (result){
                        if(null != result && "" != result && 0 == result.status){
                            toast("添加成功","添加成功","success");
                            $("#save").addClass("disabled");
                        }else{
                            alert(result.msg);
                        }
                    },
                    error:function (result) {
                        toast("添加失败","添加失败","error");
                    }
                })
            }
        });

        $("#reset").on("click",function(){
        	self.location=document.referrer;
        });
		
        $("#t_save").on("click",function(){
        	console.log(url + "/denture/download")
        	$("#download").attr("href",url + "/denture/download");
        	document.getElementById("download").click();
        })
        
    </script>

</body>

</html>
