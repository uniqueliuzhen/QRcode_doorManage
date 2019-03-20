<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>记录图表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.css" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/toastr.min.css" rel="stylesheet">
    <link href="../css/project.css" rel="stylesheet" >

    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <!-- 自定义js -->
    <script type="text/javascript" src="../js/content.js"></script>
    <script type="text/javascript" src="../js/toastr.min.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
	<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
	
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
                                    <div id="container" style="height: 530px"></div>
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
    	var x = [];
    	var y = [];
    	$.ajax({
            type: "get",
            dataType: "json",
            url:"/Manage/record/use",
            async:false,
            success: function (result){
                if(null != result && "" != result && 0 == result.status){
                	$.each(result.data,function (n,value) {
                		x.push(value.material);
                		y.push(value.percent);
                    })
                }else{
                    alert(result.msg);
                }
            },
            error:function (result) {
                toast("查询失败","查询失败","error");
            }
        })
        
    	var dom = document.getElementById("container");
    	var myChart = echarts.init(dom);
    	var app = {};
    	option = null;
    	option = {
    	    xAxis: {
    	        type: 'category',
    	        data: x ,
    	        axisTick: {
                    alignWithLabel: true
                }
    	    },
    	    yAxis: {
    	        type: 'value',
    	        axisLabel: {
    	                formatter: '{value} %'
    	        },
    	    },
    	    tooltip : {
    	        trigger: 'axis',
    	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    	        }
    	    },
    	    series: [{
    	        data: y,
    	        type: 'bar',
    	        barWidth: '60%',
    	        itemStyle:{
                    normal:{
                        color:'#4ad2ff'
                    }
                }
    	    }]
    	};
    	;
    	if (option && typeof option === "object") {
    	    myChart.setOption(option, true);
    	}
    	
    </script>

</body>

</html>
