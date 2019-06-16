package firsttime.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import firsttime.entries.User;
import firsttime.service.IUserService;
import firsttime.util.File_tool;
import firsttime.util.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/ajax")
public class ajax_controller {
    @Resource
    private IUserService iuserService;

    @RequestMapping(value="/del_friend.do")
    public void del_friend(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //编码
        response.setCharacterEncoding("utf-8");
//        获取请求数据
        String current_username = request.getParameter("current_user_name");
        String destination_name = request.getParameter("destination_name");

        //获得当前用户
        User current_user=iuserService.getUserByName(current_username);
        User destination=iuserService.getUserByName(destination_name);


        //获得当前用户的好友列表
        String friends=current_user.getFriends();
        StringTokenizer tokenizer=new StringTokenizer(friends,"%%##");
        int i=tokenizer.countTokens();

        //暂存结果
        StringBuilder builder=new StringBuilder();
        while (--i!=-1)
        {
            String temp=tokenizer.nextToken();
            if(!temp.equals(destination_name))
            builder.append("%%##"+temp);
        }
        current_user.setFriends(builder.toString());


        //获得目标用户的好友列表
        String des_friends=destination.getFriends();
        StringTokenizer token=new StringTokenizer(des_friends,"%%##");
        int m=token.countTokens();

        //暂存结果
        StringBuilder des_builder=new StringBuilder();
        while (--m!=-1)
        {
            String temp=token.nextToken();
            if(!temp.equals(current_username))
                des_builder.append("%%##"+temp);
        }
        destination.setFriends(des_builder.toString());

        //更新数据库
        iuserService.updateByPrimaryKeySelective(current_user);
        iuserService.updateByPrimaryKeySelective(destination);

    }


    @RequestMapping(value="/add_friend.do")
    public void add_friend(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //编码
        response.setCharacterEncoding("utf-8");
//        获取请求数据
        String current_username = request.getParameter("current_user_name");
        String destination_name = request.getParameter("destination_name");
//获得当前用户
        User current_user=iuserService.getUserByName(current_username);
        User destination=iuserService.getUserByName(destination_name);
        //获得当前用户的好友列表
        String friends=current_user.getFriends();
        String des_friends=destination.getFriends();

        current_user.setFriends(friends+"%%##"+destination_name);
        destination.setFriends(des_friends+"%%##"+current_username);
        //更新数据库
        iuserService.updateByPrimaryKeySelective(current_user);
        iuserService.updateByPrimaryKeySelective(destination);
    }


    @RequestMapping(value="/flush_msg.do")
    public void flush_msg(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //编码
        response.setCharacterEncoding("utf-8");
//        获取请求数据
        String current_username = request.getParameter("current_user_name");
        String destination_name=request.getParameter("destination_name");

        PrintWriter writer=  response.getWriter();
        //得到文件路径
        String self_file="";
        Properties sysProperty=System.getProperties();
        if(sysProperty.getProperty("os.name").toLowerCase().startsWith("win"))
        {
                 self_file    =  (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+current_username.trim()+".txt").substring(1);
        }else {
            self_file    =  (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+current_username.trim()+".txt");
        }

        File m_file=new File(self_file);

        //如果文件不存在
        if(!m_file.exists())
        {
            m_file.createNewFile();
        }


        BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(m_file));
        StringBuilder builder=new StringBuilder();
        int len=0;
        byte[] buffer=new byte[1024];
        while ((len=bufferedInputStream.read(buffer))!=-1){
            builder.append(new String(buffer,0,len,File_tool.encoding));
        }
        StringTokenizer tokenizer=new StringTokenizer(builder.toString(),"%%##**");

        //暂存返回结果，覆盖文件内容
        StringBuilder result=new StringBuilder();
        StringBuilder over_file=new StringBuilder();
        boolean flag=false;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
        while (tokenizer.hasMoreTokens()){

            String msg_item=tokenizer.nextToken();
            Log.log_info(this,"msg_item:"+msg_item);
            //取出元素
            String time=msg_item.substring(0,19);
            //判断时间是否已经久远
            if(flag||new Date().getTime()-sdf.parse(time).getTime()<864000000) {
                flag=true;

                int name_length = Integer.valueOf(msg_item.substring(19, 21)) > 50 ? Integer.valueOf(msg_item.substring(19, 21)) - 50 : Integer.valueOf(msg_item.substring(19, 21)) - 10;
                String name = msg_item.substring(21, 21 + name_length);
                String message = msg_item.substring(21 + name_length);

                over_file.append("%%##**"+msg_item);

                //如果不是当前聊天user,continue
                if(!destination_name.trim().equals(name))
                continue;
                //判断我方输入，敌方输入
                if(Integer.valueOf(msg_item.substring(19, 21))>50)
                {
                    result.append("   <div style=\"height: 25px;text-align: center\"><h5>"+time+"</h5></div>\n" +
                            "                <div class=\"row\" style=\"margin-bottom: 10px;width: 350px;\">\n" +
                            "                <div class=\"col-sm-2\" style=\"height: 40px;\">\n" +
                            "                  <img src=\"/rec/image/head_icon1.png\" class=\"rounded-circle\" style=\" width: 40px;height: 40px;\">\n" +
                            "                </div>\n" +
                            "                <div class=\"col-sm-10 alert alert-success\" style=\"word-break:break-all; margin: 0px;\">\n" +
                            "                  <h5>\n" +message+
                            "\n" +
                            "                  </h5>\n" +
                            "                </div>\n" +
                            "              </div>");
                }else{
                    result.append("   <div style=\"height: 25px;text-align: center\"><h5>"+time+"</h5></div>\n" +
                            "                <div class=\"row\" style=\"margin: 0px 0px 10px 35px;width: 350px;\">\n" +
                            "                  <div class=\"col-10 alert alert-success\" style=\"word-break:break-all; margin: 0px;\">\n" +
                            "                    <h5>\n" +message+
                            "\n" +
                            "                    </h5>\n" +
                            "                </div>\n" +
                            "                <div class=\"col-2\" style=\"height: 40px;padding:0px 0px 0px 18px;\">\n" +
                            "                  <img src=\"/rec/image/head_icon.png\" class=\"rounded-circle\" style=\" width: 40px;height: 40px;\">\n" +
                            "                </div>\n" +
                            "              </div>");
                }


            }
        }

        //覆盖文件
        Writer wri = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(m_file), File_tool.encoding));
        wri.write(over_file.toString());
        wri.flush();

        //返回结果
        writer.print(result.toString());

        //释放流
        bufferedInputStream.close();
        wri.close();
    }


    @RequestMapping(value="/send_msg.do")
    public void send_msg(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //编码
        response.setCharacterEncoding("utf-8");
//        获取请求数据
        String current_username = request.getParameter("current_user_name");
        String destination_username=request.getParameter("destination_username");
        String msg=request.getParameter("message");

         PrintWriter writer=  response.getWriter();
         //得到文件路径

        String self_file;
         String destination_file;
         //判断环境
        Properties sysProperty=System.getProperties();
        if(sysProperty.getProperty("os.name").toLowerCase().startsWith("win")){
            destination_file = (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+destination_username.trim()+".txt").substring(1);
            self_file = (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+current_username.trim()+".txt").substring(1);
        }else {
            destination_file = (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+destination_username.trim()+".txt");
            self_file = (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+current_username.trim()+".txt");
        }

        File d_file=new File(destination_file);
        File m_file=new File(self_file);


        if(!d_file.exists())
        {
            d_file.createNewFile();
        }

        Log.log_info(this,"destination_file"+destination_file);

        //追加内容,utf8

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
        //对方消息
       File_tool.append_content(d_file,"%%##**"+dateFormat.format(new Date())+(current_username.trim().length()+50)+current_username.trim()+msg);
        //自己消息
        File_tool.append_content(m_file,"%%##**"+dateFormat.format(new Date())+(destination_username.trim().length()+10)+destination_username.trim()+msg);

    }


    @RequestMapping(value="/friends_list.do")
    public void friends_list(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //编码
        response.setCharacterEncoding("utf-8");
//        获取请求数据
        String current_username = request.getParameter("current_user_name");
        User current_user=iuserService.getUserByName(current_username);
        //获得当前用户的好友列表
        String friends=current_user.getFriends();
        StringTokenizer tokenizer=new StringTokenizer(friends,"%%##");
        PrintWriter writer=response.getWriter();

        //保存结果
        StringBuilder result=new StringBuilder();
        while (tokenizer.hasMoreTokens())
        {
            result.append("<div onclick=\"click_start_chat(this)\" data-target=\"#demo\" data-slide-to=\"0\" style=\"margin-bottom: 2px;\">\n" +
                    "              <div class=\" alert alert-info\" style=\"word-break:break-all; margin: 0px;\">\n" +
                    "                  <h5 style=\"text-align: center\">\n" +tokenizer.nextToken()+
                    "\n" +
                    "                  </h5>\n" +
                    "              </div>\n" +
                    "          </div>");
        }
        writer.print(result.toString());

    }

    @RequestMapping(value="/searchHint.do")
    public void searchHint(HttpServletRequest request,HttpServletResponse response) throws IOException {
       //编码
        response.setCharacterEncoding("utf-8");
//        获取请求数据
        String current_username=request.getParameter("current_user_name");
        String name=request.getParameter("search_string");

//        查找数据库,dao
        List<User> users= iuserService.getUsersByName(name);
        User current_user=iuserService.getUserByName(current_username);


        PrintWriter writer=response.getWriter();
        //获得当前用户的好友列表
        String friends=current_user.getFriends();
        StringTokenizer tokenizer=new StringTokenizer(friends,"%%##");
        int i=tokenizer.countTokens();
        String[] friend_list=new String[i];

       while (--i!=-1)
        {
            friend_list[i]=tokenizer.nextToken();
        }


        //输入为空
        if(name.equals(""))
        {
            writer.print("<div id=\"chat_state1\" style=\"visibility: visible; margin-top: 190px;width: 350px;height: 55px;position: absolute;z-index: 1;\">\n" +
                    "                  <h3 style=\"text-align: center\"><b>在这里添加/删除好友哦！</b></h3>\n" +
                    "                  </div>");
            return;
        }
        //没有匹配到
        if(users.isEmpty()) {
            writer.print("<div id=\"chat_state1\" style=\"visibility: visible; margin-top: 190px;width: 350px;height: 55px;position: absolute;z-index: 1;\">\n" +
                    "                  <h3 style=\"text-align: center\"><b>没有匹配到。。。</b></h3>\n" +
                    "                  </div>");
            return;
        }


        //输入了自己的用户名
        if(name.equals(current_username)){

            writer.print("<div id=\"chat_state1\" style=\"visibility: visible; margin-top: 190px;width: 350px;height: 55px;position: absolute;z-index: 1;\">\n" +
                    "                  <h3 style=\"text-align: center\"><b>这是你自己啊！</b></h3>\n" +
                    "                  </div>");
            return;
        }

        //html搜索结果，h5style
        StringBuilder result=new StringBuilder();
            for(User user:users) {
                //如果是自己
                if(user.getUserName().equals(current_username))
                    continue;
               boolean is_friend=false;
                for (int n=0;n<friend_list.length;n++) {

                    if (user.getUserName().equals(friend_list[n]))
                    {
                        is_friend=true;
                    }
                }
                if (is_friend){
                    result.append(
                            "                      <div class=\"row\" style=\"margin-bottom: 10px;width: 350px;\">\n" +
                                    "                          <bottom class=\"col-3 btn btn-danger\" onclick=\"del_friend_ajax(\'"+user.getUserName()+"\')\" style=\"height: 40px;\">\n" +
                                    "                              <h5 style=\"height: 40px;\">移除</h5>\n" +
                                    "                          </bottom>\n" +
                                    "                          <div class=\"col-9 alert alert-info\" style=\"word-break:break-all; margin: 0px;\">\n" +
                                    "                              <h5 style=\"text-align: center\">\n" +user.getUserName()+
                                    "\n" +
                                    "                              </h5>\n" +
                                    "                          </div>\n" +
                                    "                      </div>\n");
                }else {
                    result.append(
                            "                  <div class=\"row\" style=\"margin-bottom: 10px;width: 350px;\">\n" +
                                    "                      <bottom class=\"col-3 btn btn-info\" onclick=\"add_friend_ajax(\'"+user.getUserName()+"\')\" style=\"height: 40px;\">\n" +
                                    "                         <h5 style=\"height: 40px;\">添加</h5>\n" +
                                    "                      </bottom>\n" +
                                    "                      <div class=\"col-9 alert alert-info\" style=\"word-break:break-all; margin: 0px;\">\n" +
                                    "                          <h5 style=\"text-align: center\">\n" +user.getUserName()+
                                    "\n" +
                                    "                          </h5>\n" +
                                    "                      </div>\n" +
                                    "                  </div>\n");
                }


            }
        writer.print(result.toString());


    }

    @RequestMapping(value="/sign_inputHint.do")
    public void sign_inputHint(HttpServletRequest request,HttpServletResponse response) throws IOException {
       response.setCharacterEncoding("utf-8");
        String name=request.getParameter("sign_input_string");
        System.out.println(name);
      User users= iuserService.getUserByName(name);
        PrintWriter writer=response.getWriter();

        if(name.equals(""))
        {
            writer.print("<h5>用户名：</h5>");
            return;
        }
        if(users==null)
            writer.print("<h5 style=\"color: green;\">用户名：可以使用</h5>");
        else
            writer.print("<h5 style=\"color: red;\">用户名：已被占用</h5>");
    }


}
