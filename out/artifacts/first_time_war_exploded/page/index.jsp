<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>尹大虎的主页</title>
    <link rel="stylesheet" href="..\css\index.css" type="text/css" />
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="/rec/js/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="/rec/js/jquery.min.js"></script>
    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="/rec/js/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="/rec/js/bootstrap.min.js"></script>
      <%--vue ajax--%>
   <%--<script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>--%>

      <%--移动端适配优先--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
  </head>

  <%--页内样式--%>
<style>
    /*h5,button,a,h3标签超出隐藏*/
h5,h3,button,a{
  overflow: hidden;
    margin: 0px;
}
  .row{
    padding: 0px;
    margin: 0px 10px 0px 0px;
  }
  .row div{
    padding: 0px;
  }
</style>

<body>



<!-- 网站header，firsttime-->
<div class="container" style="width: 900px;height: 680px;">
<ul class="nav justify-content-end " style="margin-left:90px; height: 60px;width: 720px; background-image: url('/rec/image/site_head_bg.png')" >
  <li style="background-image: url('/image/site_img.png')"></li>
    <a href="/user/log_in.do" style="margin-left: 650px; font-size: 1.3rem;height: 24px;"><h5>登出</h5></a>
  <li class="nav-item" style=" height: 24px;width: 60px;">
    <h5 class="nav-link" style="overflow: hidden;width: 60px;height: 30px; padding: 0px;margin: 0px 0px 0px;">你好:</h5>
  </li>

  <li class="nav-item" style=" height: 24px;width: 80px;">
    <h6 class="nav-link" id="username" style="height: 30px; padding: 0px;margin: 0px 0px 0px;">
     <script>
       var name='${username}';
       document.write(name)
     </script>
    </h6>
  </li>
</ul>


<!--网站功能区-->
<div class="container" style="margin: 20px 0px 0px 0px;height: 600px;width:900px;padding: 0px;">

    <%--网格布局--%>
  <div class="row" style="height: 600px;margin: 0px;background-image: url(/rec/image/common_bg.png);">
      <%--左部分--%>
    <div class="col-2">
      <div style="text-align: center; width: 160px;height: 40px; margin-top: 60px">
        <h5>好友列表</h5>
      </div>
      <div id="friends_list" style="overflow-y: scroll; max-height: 700px; width: 180px;height: 400px;position: absolute; z-index: 1;">
            <%--user--%>
          <div onclick="click_start_chat(this)" data-target="#demo" data-slide-to="0" style="margin-bottom: 2px;">
              <div class=" alert alert-info" style="word-break:break-all; margin: 0px;">
                  <h5 style="text-align: center">
                      hello
                  </h5>
              </div>
          </div>


    </div>
    </div>


          <%--中间--%>
    <div class="col-8" >

      <div id="demo" class="carousel slide" style="margin-left: 65px; height: 590px;width: 550px;">
        <div class="carousel-inner" style="height: 590px;">


            <%--聊天界面--%>
          <div class="carousel-item active" style="height: 590px;">
            <div style="height: 40px;"></div>
              <h5 id="chat_title" style="height: 24px;text-align: center">聊天</h5>
            <div style="height: 40px;"></div>
            <img src="/rec/image/rect_chat.png" style="position: absolute; z-index: 0;">

              <%--点击好友聊天前--%>
              <div id="chat_state1" style="visibility: visible; margin-top: 190px;width: 420px;height: 55px; margin-left: 65px;position: absolute;z-index: 1;">
                <h3 style="text-align: center"><b>选择一个好友开始聊天吧！</b></h3>

              </div>



              <%--开始聊天阶段--%>
              <div id="chat_state2" style=" visibility: hidden;height: 475px;width: 420px; margin-left: 65px;position: absolute;z-index: 1;">

           <%--聊天--%>
            <div class="pre-scrollable" id="scroll_div" style="  margin-top: 0px;height: 375px;border: 5px dashed whitesmoke;">
            <ul id="chat_area" style=" list-style: none;padding-left: 0px;">
                <%--<div style="height: 25px;text-align: center"><h5>2019.06.12 06.36.51</h5></div>--%>
                <%--<div class="row" style="margin-bottom: 10px;width: 350px;">--%>
                <%--<div class="col-sm-2" style="height: 40px;">--%>
                  <%--<img src="/rec/image/rect.png" class="rounded-circle" style=" width: 40px;height: 40px;">--%>
                <%--</div>--%>
                <%--<div class="col-sm-10 alert alert-success" style="word-break:break-all; margin: 0px;">--%>
                  <%--<h5>--%>
                    <%--hello--%>
                  <%--</h5>--%>
                <%--</div>--%>
              <%--</div>--%>


                <%--<div style="height: 25px;text-align: center"><h5>2019.06.12 06.36.51</h5></div>--%>
                <%--<div class="row" style="margin: 0px 0px 10px 35px;width: 350px;">--%>
                  <%--<div class="col-10 alert alert-success" style="word-break:break-all; margin: 0px;">--%>
                    <%--<h5>--%>
                        <%--hello--%>
                    <%--</h5>--%>
                <%--</div>--%>
                <%--<div class="col-2" style="height: 40px;padding:0px 0px 0px 18px;">--%>
                  <%--<img src="/rec/image/bg.jpg" class="rounded-circle" style=" width: 40px;height: 40px;">--%>
                <%--</div>--%>
              <%--</div>--%>
            </ul>
            </div>

               <%--发送消息输入--%>
               <div class="row" style=" margin-top: 30px">
                  <input id="chat_input" class="form-control col-9"  placeholder="请输入内容">
                    <button class="btn btn-primary col-3" onclick="send_msg_ajax()"><h5>发送</h5></button>
               </div>
              </div>



          </div>


                <%--加好友界面--%>
          <div class="carousel-item " style="height: 590px;">
            <div style="height: 40px;"></div>
              <%--加好友输入框--%>
              <div class="row" style="height: 40px;">
                  <div class="col-2"></div>
                  <input id="search_input" type="text" class="form-control col-8" oninput="search_ajax(this.value)" style="height: 40px;padding:0px 0px 0px 5px;" placeholder="请输入被管理的用户名">
                  <div class="col-2"></div>

              </div>
            <div style="height: 32px;"></div>
            <img src="/rec/image/rect_chat.png" style="position: absolute; z-index: 0;">

                <%--搜索列表--%>
              <div id="textHint" style="overflow-y: scroll; height: 475px;width: 420px; margin-left: 65px;border: 5px dashed whitesmoke;position: absolute;z-index: 1;">
                  <div style=" margin-top: 190px;width: 350px;height: 55px;position: absolute;z-index: 1;">
                  <h3 style="text-align: center"><b>在这里添加/删除好友哦！</b></h3>
                  </div>

              </div>
              </div>



                <%--设置界面--%>
          <div class="carousel-item " style="height: 590px;">
            <div style="height: 40px;"></div>
            <h5 style="height: 24px;text-align: center">设置</h5>
            <div style="height: 40px;"></div>
            <img src="/rec/image/rect_chat.png" >
          </div>
        </div>
      </div>
    </div>


          <%--右边布局--%>
    <div class="col-2" >
      <div id="index_chat_div" class=" carousel-indicators btn-group-vertical" style=" position: relative; width: 80px;height: 400px; background-image: url('/rec/image/site_aside_bg.png'); margin-top: 100px;margin-left: 60px;">
          <button type="button" data-target="#demo" data-slide-to="0" class="btn btn-light"><h5>好友</h5></button>
          <button type="button" data-target="#demo" data-slide-to="1" class="btn btn-light"><h6>管理好友</h6></button>
          <button type="button" data-target="#demo" data-slide-to="2" class="btn btn-light"><h5>设置</h5></button>

      </div>
</div>
</div>
</div>
</div>


<div style="float: left;visibility: hidden" id="chat_num"></div>
</body>




<script type="text/javascript">

      // var len=document.getElementById("chat_area").innerHTML;
      // attributes.setAttribute("page",len);
    //删除好友
    function del_friend_ajax(friend_name) {
        var name='${username}';
        $.ajax(
            {data:{
                    "current_user_name":name,
                    "destination_name":friend_name
                },
                type:"POST",
                url : "/ajax/del_friend.do",
                success:function (msg) {
                var input_value=document.getElementById("search_input").value;
                   search_ajax(input_value);
                   flush_friends_list();

                },
            }
        )
    }


    //添加好友
    function add_friend_ajax(friend_name) {
        var name='${username}';
        $.ajax(
            {data:{
                    "current_user_name":name,
                    "destination_name":friend_name
                },
                type:"POST",
                url : "/ajax/add_friend.do",
                success:function (msg) {
                    var input_value=document.getElementById("search_input").value;
                    search_ajax(input_value);
                    flush_friends_list();

                },
            }
        )
    }




    //刷新聊天内容
    function flush_msg_ajax(destination_username) {
        var name='${username}';

        $.ajax(
            {data:{
                    "current_user_name":name,
                    "destination_name":destination_username
                },
                type:"POST",
                url : "/ajax/flush_msg.do",
                success:function (msg) {
                // var len=attributes.getAttribute("chat_num");
                    var len=document.getElementById("chat_num").innerHTML;
                if(len!= msg.length.toString()) {
                    // alert("len:"+len+"msg:"+msg.length.toString())

                    document.getElementById("chat_area").innerHTML = msg;
                    document.getElementById("scroll_div").scrollTop = document.getElementById("scroll_div").scrollHeight;
                // attributes.setAttribute("chat_num",msg.length);
                    document.getElementById("chat_num").innerHTML = msg.length;
                }

                },
                error : function() {
                    document.getElementById("chat_area").innerHTML="wrong";

                },
                done : function() {
                    document.getElementById("chat_area").innerHTML="done";

                }

            }
        )
    }
//每1s刷新一次
    function flush_msg() {
        var des_name=document.getElementById("chat_title").innerHTML;
        if(des_name!="聊天"){
            flush_msg_ajax(des_name)

        }
    }
   setInterval(flush_msg,1000)


    //发消息事件
    function send_msg_ajax() {
        var message=document.getElementById("chat_input").value;
        //消息不为空
        if(message=="")
            return;
        var name='${username}';

        $.ajax(
            {data:{"message":message,
                    "current_user_name":name,
                    "destination_username":document.getElementById("chat_title").innerHTML
                },
                type:"POST",
                url : "/ajax/send_msg.do",
                success:function (msg) {
                    document.getElementById("chat_input").value=""
                    flush_msg_ajax(document.getElementById("chat_title").innerHTML);

                    },
                error : function() {
                    document.getElementById("chat_area").innerHTML="wrong";

                },
                done : function() {
                    document.getElementById("chat_area").innerHTML="done";

                }

            }
        )
    }



    //初始化，加载好友列表
    function flush_friends_list () {
        var name='${username}';
        $.ajax(
            {data:{"current_user_name":name
                },
                type:"POST",
                url : "/ajax/friends_list.do",
                success:function (msg) {
                    document.getElementById("friends_list").innerHTML=msg;
                },
                error : function() {
                    document.getElementById("friends_list").innerHTML="wrong";

                },
                done : function() {
                    document.getElementById("friends_list").innerHTML="done";

                }

            }
        )
    }
    window.onload=flush_friends_list();


    // 搜索输入框添加按键事件
    function search_ajax(str) {
        var name='${username}';
        $.ajax(
            {data:{"search_string":str,
                "current_user_name":name
                },
                type:"POST",
                url : "/ajax/searchHint.do",
                success:function (msg) {
                    document.getElementById("textHint").innerHTML=msg;
                },
                error : function() {
                    document.getElementById("textHint").innerHTML="wrong";

                },
                done : function() {
                    document.getElementById("textHint").innerHTML="done";

                }

            }
        )
    }




    // 聊天界面切换
    function click_start_chat(obj){

        var children= obj.firstElementChild;
        while (children.tagName!="H5")
        {
            children=children.firstElementChild;
        }

        var talkingto =children.innerHTML;
        document.getElementById("chat_title").innerHTML=talkingto;
        document.getElementById("chat_state1").style.visibility="hidden";
        document.getElementById("chat_state2").style.visibility="visible";

       flush_msg_ajax(talkingto);

    }


    //为提交按钮添加事件send_msg_ajax()
    $(document).ready(
        function(){
            document.onkeydown = function(){
                var oEvent = window.event;
                if (oEvent.keyCode==13) {
                    send_msg_ajax()
                }
            }
        });


    // 为好友列表添加点击事件
    // var target= document.getElementById("friend_list");
    // var first_child=target.firstElementChild;
    // var last_child=target.lastElementChild;
    // while(first_child!=last_child){
    //     first_child.addEventListener("click",function (evt) {
    //         click_start_chat(evt.target);
    //     });
    //     first_child=first_child.nextElementSibling;
    // }
    //  last_child.addEventListener("click",function (evt) {
    //      click_start_chat(evt.target);
    //  });
</script>
</html>
