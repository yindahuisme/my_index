<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>注册</title>
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
    if (userName.length>12) {
        alert("名字长度不能大于12！");
        return;
    }
    $("#adminsignin").submit();

}
</script>
<body>
<div class="container" style="background-image: url('/rec/image/rect.png');height: 337px;width: 337px;">
    <h2>注册</h2>
    <form id="adminsignin" action="/user/signin.do" method="post">
        <div class="form-group">
            <label id="username_tip" for="username"><h5>用户名：</h5></label>
            <input type="text" name="username" onkeyup="sign_ajax(this.value)" class="form-control" id="username"  placeholder="请输入账号">
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

<script type="text/javascript">
    // 注册用户名输入框添加按键事件
    function sign_ajax(str) {

        $.ajax(
            {data:{"sign_input_string":str},
                type:"POST",
                url : "/ajax/sign_inputHint.do",
                success:function (msg) {
                    document.getElementById("username_tip").innerHTML=msg;
                },
                error : function() {
                    document.getElementById("username_tip").innerHTML="wrong";

                },
                done : function() {
                    document.getElementById("username_tip").innerHTML="done";

                }

            }
        )
    }

</script>
</html>