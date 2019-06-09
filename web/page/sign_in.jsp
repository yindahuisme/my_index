<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="/rec/js/vue.min.js"></script>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="/rec/js/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="/rec/js/jquery.min.js"></script>
    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="rec/js/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="/rec/js/bootstrap.min.js"></script>
</head>
<script type=text/javascript>
var sign_in='${regist_status}'
if (sign_in=="successfull") {
    alert("注册成功！");
    window.open("/user/log_in.do");
}
else if(sign_in=="existed"){
    alert("邮箱已注册！");
}



function signin() {
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
    alert(userName)
    $("#adminsignin").submit();

}
</script>
<body>
<div class="container" style="background-image: url('/rec/image/rect.png');height: 337px;width: 337px;">
    <h2>注册</h2>
    <form id="adminsignin" action="/user/signin.do" method="post">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" name="username" class="form-control" id="username"  placeholder="请输入账号">
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码">
        </div>
        <div onclick="signin()" class="btn btn-primary">注册</div>
        <a href="/user/log_in.do">返回</a>
    </form>
</div>
</body>
</html>