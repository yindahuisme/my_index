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


      <%--移动端适配优先--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
  </head>

  <%--页内样式--%>
<style>
    /*h5,button,a,h3标签超出隐藏*/
h5,h3,button,a{
  overflow: hidden;
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
      <div  style="overflow-y: scroll; max-height: 700px; width: 180px;height: 400px;position: absolute; z-index: 1;">
        <ul id="friend_list" class="list-group " style=" width: 150px;">
          <li class="list-group-item" >First item</li>
          <li class="list-group-item">Second item</li>
          <li class="list-group-item">Third item</li>
          <li class="list-group-item">First item</li>
          <li class="list-group-item">Second item</li>
          <li class="list-group-item">Third item</li>
          <li class="list-group-item">First item</li>
          <li class="list-group-item">Second item</li>
          <li class="list-group-item">Third item</li>
          <li class="list-group-item">First item</li>
          <li class="list-group-item">Second item</li>
          <li class="list-group-item">Third item</li>
        </ul>


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
            <div id="chat_state2" style="visibility: hidden; overflow-y: scroll; margin-top: 0px;width: 420px;height: 375px; margin-left: 65px;border: 5px dashed whitesmoke;position: absolute;z-index: 1;">
            <ul style=" list-style: none;padding-left: 0px;">
            <%--对方聊天--%>
                 <div class="row" style="margin-bottom: 10px;width: 350px;">
                <div class="col-sm-2" style="height: 40px;">
                  <img src="/rec/image/rect.png" class="rounded-circle" style=" width: 40px;height: 40px;">
                </div>
                <div class="col-sm-10 alert alert-success" style="word-break:break-all; margin: 0px;">
                  <h5>
                    hello named sdfsfdkghjgjgjgjhfjyjyukkjhgmhgshsfmfhmjmhm, 计算框架是滴嘎嘎fadgfgshfshsfd大使馆的asfsfsgagdsgsd   发的伤口fdhfdhdnghjnjgfghdjgjhdg飒飒sscsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
                  </h5>
                </div>
              </div>
                <%--我方聊天--%>
              <div class="row" style="margin: 0px 0px 10px 35px;width: 350px;">
                <div class="col-10 alert alert-success" style="word-break:break-all; margin: 0px;">
                  <h5>
                    hello named sdfsfdkghjgjgjgjhfjyjyukkjhgmhgmfhmjmhm, 计算框架fdsfssfhdhshgsh是滴嘎嘎大使馆的asfsfsgagdsgsd   发的伤口fdhfdhdnghjnjgfghdjgjhdg飒飒sscsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
                  </h5>
                </div>
                <div class="col-2" style="height: 40px;padding:0px 0px 0px 18px;">
                  <img src="/rec/image/bg.jpg" class="rounded-circle" style=" width: 40px;height: 40px;">
                </div>
              </div>
            </ul>
            </div>




          </div>


                <%--加好友界面--%>
          <div class="carousel-item " style="height: 590px;">
            <div style="height: 40px;"></div>
            <h5 style="height: 24px;text-align: center;">加好友</h5>
            <div style="height: 40px;"></div>
            <img src="/rec/image/rect_chat.png" >
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
      <div id="index_chat_div" class="carousel-indicators btn-group-vertical" style="position: relative; width: 80px;height: 400px; background-image: url('/rec/image/site_aside_bg.png'); margin-top: 100px;margin-left: 60px;">
          <button type="button" data-target="#demo" data-slide-to="0" class="btn btn-light"><h5>好友</h5></button>
          <button type="button" data-target="#demo" data-slide-to="1" class="btn btn-light"><h6>加好友</h6></button>
          <button type="button" data-target="#demo" data-slide-to="2" class="btn btn-light"><h5>设置</h5></button>

      </div>
</div>
</div>
</div>
</div>
</body>


  <script src="/rec/js/index.js"></script>
<script>

    // 为好友列表添加点击事件
   var target= document.getElementById("friend_list");
   var first_child=target.firstElementChild;
   var last_child=target.lastElementChild;
   while(first_child!=last_child){
       first_child.addEventListener("click",function (evt) {
           click_start_chat(evt.target);
       });
       first_child=first_child.nextElementSibling;
   }
    last_child.addEventListener("click",function (evt) {
        click_start_chat(evt.target);
    });



</script>


</html>
