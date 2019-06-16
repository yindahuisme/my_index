<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="/rec/js/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="/rec/js/jquery.min.js"></script>
    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="/rec/js/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="/rec/js/bootstrap.min.js"></script>
</head>

<script type=text/javascript>

    var log_in='${log_status}';
  if(log_in=="no"){
        alert("密码错误！");
    }
    else if(log_in=="notexisted"){
        alert("用户不存在！")
    }




    function login() {
        var userName = $("#username").val();
        var password = $("#password").val();

        if (userName == "") {
            alert("用户名不能为空！");
            return;
        }
        if (password == "") {
            alert("密码不能为空！");
            return;
        }
        $("#adminsignin").submit();

    }
</script>

<body>
<div class="container" style="background-image: url('/rec/image/rect.png');height: 337px;width: 337px;">
    <h2>登陆</h2>
    <form id="adminsignin" action="/user/login.do" method="post">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" name="username" class="form-control" id="username" placeholder="请输入账号">
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码">
        </div>
        <div onclick="login()" class="btn btn-primary">登陆</div>
        <a href="/user/sign_in.do">注册</a>
    </form>
</div>
</body>
</html>