<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>在线看图</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.css" rel="stylesheet">
    <link href="../css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
    

    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    
    <style>
        .lightBoxGallery img {
            margin: 5px;
            width: 160px;
        }
    </style>

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="lightBoxGallery" id="main" >

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 全局js -->
    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="../js/content.js"></script>
	<script src="../js/jquery.blueimp-gallery.min.js"></script>

	<script type="text/javascript">
		var file = decodeURI(getQueryString("file"));
		var str = file.split(",");
		str.pop();
		var images = "";
		$.each(str,function (n,value) {
			images += '<a href="/Onlinemusic/file/'+str[n]+'" title="'+str[n]+'" data-gallery=""><img style="width:200px;height:200px" src="/Onlinemusic/file/'+str[n]+'"></a>'	
		});
		images = images + '<div id="blueimp-gallery" class="blueimp-gallery"><div class="slides"></div><h3 class="title"></h3><a class="prev">‹</a><a class="next">›</a><a class="close">×</a><a class="play-pause"></a><ol class="indicator"></ol></div>'
		$("#main").append(images);
	</script>

</body>


</html>
