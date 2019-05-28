
<%@ page import="static java.util.Arrays.sort" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%--
  Created by IntelliJ IDEA.
  User: yindahu
  Date: 2018/10/14
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>尹大虎的主页</title>
    <link rel="stylesheet" href="..\css\index.css" type="text/css" />
  </head>
  <body>
  <!--
  网站头部，网站名，登录状态
  -->
  <header>
    <!--
    网站名
    -->
<div id="site_image" class="inline_block">
  <img src="rec\image\site_img.png">
</div>
    <!--
    登录状态
    -->
    <div id="log_regist" class="inline_block">
      <!--
      未登录
      -->
      <div class="log_regist_active">
        <!--
        登陆
        -->
        <a href="page\log_in.html">
          登录
        </a>
        |
        <!--
        注册
        -->
        <a href="page\sign_in.html">
          注册
        </a>
      </div>
      <!--
      登陆后
      -->
      <div >

      </div>
    </div>
  </div>
  </header>



  <!--
  网站主体
  -->
  <article>
    <!--
    左侧导航栏
    -->
 <aside>
   <ul class="aside_top">
     <li class="aside_ico">
       <!--
聊天
-->
       <img src="..\rec\image\site_aside_chat.png">
     </li>
     <li class="aside_ico">
       <!--
朋友圈
-->
       <img src="..\rec\image\site_aside_share.png">
     </li>
   </ul>

 </aside>

    <!--
    chat主体
    -->
    <div class="main_active">
      <!--
      left
      -->
      <div style="position:absolute;width:400px;height:700px;left:0px;" class="inline_block">
        <img src="rec\image\site_middle_left.png">
      </div>
      <!--
      right
      -->
      <div style="position:absolute;width:540px;height:700px;right:0px;" class="inline_block">
        <img src="rec\image\site_middle_right.png">
      </div>

    </div>

  </article>
  </body>
</html>
