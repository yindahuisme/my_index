package firsttime.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import firsttime.entries.User;
import firsttime.service.IUserService;
import firsttime.util.File_tool;
import firsttime.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
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
    //发送消息时线程要用到，不建议写在这
    public static String msg;
    public static File d_file;
    public static File m_file;

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
            self_file=  (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+current_username.trim()+".txt").substring(1);
        }else {
            self_file=  (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+current_username.trim()+".txt");
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


        //如果是客服,添加提示语
        //全查
        List<User> users=iuserService.getUsersByName("");
        StringBuilder users_info=new StringBuilder();
        users_info.append("当前系统用户有:");
        for(User user:users){
         users_info.append(user.getUserName()+",");
        }
        users_info.delete(users_info.length()-1,users_info.length());
        users_info.append(";点击右侧‘管理好友’，添加他们，开始聊天吧！");

        if(destination_name.trim().equals("机器人客服")){
            result.append("   <div style=\"float:left;width:100%; height: 25px;text-align: center\"><h5 style=\"color: orangered;\">"+sdf.format(new Date())+"</h5></div>\n" +
                    "                <div style=\"margin-bottom: 10px;width: 350px;\">\n" +
                    "                <div style=\"float:left;width: 15%;height: 40px;\">\n" +
                    "                  <img src=\"/rec/image/head_icon1.png\" class=\"rounded-circle\" style=\" width: 40px;height: 40px;\">\n" +
                    "                </div>\n" +
                    "                <div class=\" alert alert-warning\" style=\"float:right;width: 85%;word-break:break-all; margin: 0px;\">\n" +
                    "                  <h5>\n" +users_info+
                    "\n" +
                    "                  </h5>\n" +
                    "                </div>\n" +
                    "              </div>");
        }



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
                    result.append("   <div style=\"float:left;width:100%; height: 25px;text-align: center\"><h5>"+time+"</h5></div>\n" +
                            "                <div style=\"margin-bottom: 10px;width: 350px;\">\n" +
                            "                <div style=\"float:left;width: 15%;height: 40px;\">\n" +
                            "                  <img src=\"/rec/image/head_icon1.png\" class=\"rounded-circle\" style=\" width: 40px;height: 40px;\">\n" +
                            "                </div>\n" +
                            "                <div class=\" alert alert-success\" style=\"float:right;width: 85%;word-break:break-all; margin: 0px;\">\n" +
                            "                  <h5>\n" +message+
                            "\n" +
                            "                  </h5>\n" +
                            "                </div>\n" +
                            "              </div>");
                }else{
                    result.append("   <div style=\"float:left;width:100%; height: 25px;text-align: center\"><h5>"+time+"</h5></div>\n" +
                            "                <div style=\"margin: 0px 0px 10px 35px;width: 350px;\">\n" +
                            "                  <div class=\" alert alert-secondary\" style=\"float:left;width: 85%;word-break:break-all; margin: 0px;\">\n" +
                            "                    <h5>\n" +message+
                            "\n" +
                            "                    </h5>\n" +
                            "                </div>\n" +
                            "                <div  style=\"float:right;width: 15%;height: 40px;padding:0px 0px 0px 15px;\">\n" +
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
        msg=request.getParameter("message");

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

         d_file=new File(destination_file);
         m_file=new File(self_file);


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

        //如果目标为机器人客服
        if(destination_username.trim().equals("机器人客服")){
            new Thread(()->{
                //    http://api.qingyunke.com/
                //    智能机器人助手
                String requesturl = "http://api.qingyunke.com/api.php?key=free&appid=0&msg="+msg.trim();
                HttpUriRequest hrequest = new HttpGet(requesturl);

                CloseableHttpResponse hresponse = null;
                try {
                    hresponse = HttpClients.createDefault().execute(hrequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(hresponse.getStatusLine().getStatusCode()==200){

                    HttpEntity entity = hresponse.getEntity();
                    StringBuilder result=new StringBuilder();
                    String content="";
                    int len=0;
                    byte[] buffer=new byte[1024];
                    try {
                        while ((len=entity.getContent().read(buffer))!=-1){
                         result.append(new String(buffer,0,len,"utf-8"));
                    }
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                    StringTokenizer tokenizer=new StringTokenizer(result.toString().substring(1,result.toString().length()-1),",");

                    while (tokenizer.hasMoreTokens()){
                        Integer status=Integer.valueOf(tokenizer.nextToken().substring(9));
                        if(status==0){
                            //正常状态
                            String temp=tokenizer.nextToken();
                            content=temp.substring(11,temp.length()-1);
                            break;
                        }else{
                            content="机器人挂了，请稍后再试试吧！";break;
                        }
                    }

                    SimpleDateFormat Format=new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");

                    try {
                        //自己消息
                        File_tool.append_content(m_file,"%%##**"+Format.format(new Date())+(destination_username.trim().length()+50)+destination_username.trim()+content);
                        //对方消息
                        File_tool.append_content(d_file,"%%##**"+Format.format(new Date())+(current_username.trim().length()+10)+current_username.trim()+content);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }}).start();
        }
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
        //添加机器人客服
        result.append("<div onclick=\"click_start_chat(this)\" data-target=\"#demo\" data-slide-to=\"0\" style=\"margin-bottom: 2px;\">\n" +
                "              <div class=\" alert alert-warning\" style=\"word-break:break-all; margin: 0px;\">\n" +
                "                  <h5 style=\"text-align: center\">\n" +"机器人客服"+
                "\n" +
                "                  </h5>\n" +
                "              </div>\n" +
                "          </div>");
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
