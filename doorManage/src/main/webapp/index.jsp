<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>门禁管理系统</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg" >

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>

            <h1 class="logo-name">DM</h1>

        </div>
        <div >
            <h2>门禁管理系统</h2>
            <form class="m-t" role="form" action="#">
                <div class="form-group">
                    <input type="text" class="form-control" id="account" placeholder="账号" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" placeholder="密码" required="">
                </div>
                <div class="form-group form-inline" >
		            <button type="button" id="login" class="btn btn-primary m-b" style="margin-right: 25px" >登 录</button>
		        	<button type="button" id="regist" class="btn btn-primary m-b">注 册</button>
				</div>
            </form>
        </div>
    </div>

    <!-- 全局js -->
    <script type="text/javascript" src="../doorManage/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../doorManage/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../doorManage/js/jquery.cookie.js"></script>
    <script>

        function login() {
            var account = $("#account").val();
            var password = $("#password").val();

            if(account  != null && password != null && account.replace(/(^\s*)|(\s*$)/g, "").length > 0 && password.replace(/(^\s*)|(\s*$)/g, "").length >0){
                $.ajax({
                    type: 'get',
                    url: '/doorManage/user/login',
                    data: {
                    	account:account,
                        password:password
                    },
                    dataType: "json",
                    success: function (result) {
                    	console.log(result)
                    	if(null != result && "" != result && result.success){
                            $.cookie("user",JSON.stringify(result.data),{expires:1,path:'/'});
                            window.location.href = "/doorManage/user/main";
                        }else{
                            alert("登录出错");
                        }
                    },
                    error: function (XMLHttpRequest) {
                    	 alert("登录出错");
                    }
                });
                
            }
        }

        $(function(){
            document.onkeydown = function(e){
                var ev = document.all ? window.event : e;
                if(ev.keyCode==13) {
                    login();
                }
            }
        });

        $("#login").click(function () {
            login();
        })
        
        $("#regist").on("click",function(){
        	window.location.href = "/doorManage/user/regist";
        });
        
    </script>

    <script>
        //防止页面后退
        history.pushState(null, null, document.URL);
        window.addEventListener('popstate', function () {
            history.pushState(null, null, document.URL);
        });
    </script>

</body>

</html>
