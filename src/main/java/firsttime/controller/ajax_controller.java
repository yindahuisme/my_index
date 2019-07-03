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
    //������Ϣʱ�߳�Ҫ�õ���������д����
    public static String msg;
    public static File d_file;
    public static File m_file;

    @RequestMapping(value="/del_friend.do")
    public void del_friend(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //����
        response.setCharacterEncoding("utf-8");
//        ��ȡ��������
        String current_username = request.getParameter("current_user_name");
        String destination_name = request.getParameter("destination_name");

        //��õ�ǰ�û�
        User current_user=iuserService.getUserByName(current_username);
        User destination=iuserService.getUserByName(destination_name);


        //��õ�ǰ�û��ĺ����б�
        String friends=current_user.getFriends();
        StringTokenizer tokenizer=new StringTokenizer(friends,"%%##");
        int i=tokenizer.countTokens();

        //�ݴ���
        StringBuilder builder=new StringBuilder();
        while (--i!=-1)
        {
            String temp=tokenizer.nextToken();
            if(!temp.equals(destination_name))
            builder.append("%%##"+temp);
        }
        current_user.setFriends(builder.toString());


        //���Ŀ���û��ĺ����б�
        String des_friends=destination.getFriends();
        StringTokenizer token=new StringTokenizer(des_friends,"%%##");
        int m=token.countTokens();

        //�ݴ���
        StringBuilder des_builder=new StringBuilder();
        while (--m!=-1)
        {
            String temp=token.nextToken();
            if(!temp.equals(current_username))
                des_builder.append("%%##"+temp);
        }
        destination.setFriends(des_builder.toString());

        //�������ݿ�
        iuserService.updateByPrimaryKeySelective(current_user);
        iuserService.updateByPrimaryKeySelective(destination);

    }


    @RequestMapping(value="/add_friend.do")
    public void add_friend(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //����
        response.setCharacterEncoding("utf-8");
//        ��ȡ��������
        String current_username = request.getParameter("current_user_name");
        String destination_name = request.getParameter("destination_name");
//��õ�ǰ�û�
        User current_user=iuserService.getUserByName(current_username);
        User destination=iuserService.getUserByName(destination_name);
        //��õ�ǰ�û��ĺ����б�
        String friends=current_user.getFriends();
        String des_friends=destination.getFriends();

        current_user.setFriends(friends+"%%##"+destination_name);
        destination.setFriends(des_friends+"%%##"+current_username);
        //�������ݿ�
        iuserService.updateByPrimaryKeySelective(current_user);
        iuserService.updateByPrimaryKeySelective(destination);
    }


    @RequestMapping(value="/flush_msg.do")
    public void flush_msg(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        //����
        response.setCharacterEncoding("utf-8");
//        ��ȡ��������
        String current_username = request.getParameter("current_user_name");
        String destination_name=request.getParameter("destination_name");

        PrintWriter writer=  response.getWriter();
        //�õ��ļ�·��
        String self_file="";
        Properties sysProperty=System.getProperties();
        if(sysProperty.getProperty("os.name").toLowerCase().startsWith("win"))
        {
            self_file=  (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+current_username.trim()+".txt").substring(1);
        }else {
            self_file=  (this.getClass().getClassLoader().getResource("./").getPath()+"static/chat/"+current_username.trim()+".txt");
        }

        File m_file=new File(self_file);

        //����ļ�������
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

        //�ݴ淵�ؽ���������ļ�����
        StringBuilder result=new StringBuilder();
        StringBuilder over_file=new StringBuilder();
        boolean flag=false;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");


        //����ǿͷ�,�����ʾ��
        //ȫ��
        List<User> users=iuserService.getUsersByName("");
        StringBuilder users_info=new StringBuilder();
        users_info.append("��ǰϵͳ�û���:");
        for(User user:users){
         users_info.append(user.getUserName()+",");
        }
        users_info.delete(users_info.length()-1,users_info.length());
        users_info.append(";����Ҳ࡮������ѡ���������ǣ���ʼ����ɣ�");

        if(destination_name.trim().equals("�����˿ͷ�")){
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
            //ȡ��Ԫ��
            String time=msg_item.substring(0,19);
            //�ж�ʱ���Ƿ��Ѿ���Զ
            if(flag||new Date().getTime()-sdf.parse(time).getTime()<864000000) {
                flag=true;

                int name_length = Integer.valueOf(msg_item.substring(19, 21)) > 50 ? Integer.valueOf(msg_item.substring(19, 21)) - 50 : Integer.valueOf(msg_item.substring(19, 21)) - 10;
                String name = msg_item.substring(21, 21 + name_length);
                String message = msg_item.substring(21 + name_length);

                over_file.append("%%##**"+msg_item);

                //������ǵ�ǰ����user,continue
                if(!destination_name.trim().equals(name))
                continue;
                //�ж��ҷ����룬�з�����
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

        //�����ļ�
        Writer wri = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(m_file), File_tool.encoding));
        wri.write(over_file.toString());
        wri.flush();

        //���ؽ��
        writer.print(result.toString());

        //�ͷ���
        bufferedInputStream.close();
        wri.close();
    }


    @RequestMapping(value="/send_msg.do")
    public void send_msg(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //����
        response.setCharacterEncoding("utf-8");
//        ��ȡ��������
        String current_username = request.getParameter("current_user_name");
        String destination_username=request.getParameter("destination_username");
        msg=request.getParameter("message");

         PrintWriter writer=  response.getWriter();
         //�õ��ļ�·��

        String self_file;
         String destination_file;
         //�жϻ���
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

        //׷������,utf8

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
        //�Է���Ϣ
       File_tool.append_content(d_file,"%%##**"+dateFormat.format(new Date())+(current_username.trim().length()+50)+current_username.trim()+msg);
        //�Լ���Ϣ
        File_tool.append_content(m_file,"%%##**"+dateFormat.format(new Date())+(destination_username.trim().length()+10)+destination_username.trim()+msg);

        //���Ŀ��Ϊ�����˿ͷ�
        if(destination_username.trim().equals("�����˿ͷ�")){
            new Thread(()->{
                //    http://api.qingyunke.com/
                //    ���ܻ���������
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
                            //����״̬
                            String temp=tokenizer.nextToken();
                            content=temp.substring(11,temp.length()-1);
                            break;
                        }else{
                            content="�����˹��ˣ����Ժ������԰ɣ�";break;
                        }
                    }

                    SimpleDateFormat Format=new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");

                    try {
                        //�Լ���Ϣ
                        File_tool.append_content(m_file,"%%##**"+Format.format(new Date())+(destination_username.trim().length()+50)+destination_username.trim()+content);
                        //�Է���Ϣ
                        File_tool.append_content(d_file,"%%##**"+Format.format(new Date())+(current_username.trim().length()+10)+current_username.trim()+content);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }}).start();
        }
    }


    @RequestMapping(value="/friends_list.do")
    public void friends_list(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //����
        response.setCharacterEncoding("utf-8");
//        ��ȡ��������
        String current_username = request.getParameter("current_user_name");
        User current_user=iuserService.getUserByName(current_username);
        //��õ�ǰ�û��ĺ����б�
        String friends=current_user.getFriends();
        StringTokenizer tokenizer=new StringTokenizer(friends,"%%##");
        PrintWriter writer=response.getWriter();

        //������
        StringBuilder result=new StringBuilder();
        //��ӻ����˿ͷ�
        result.append("<div onclick=\"click_start_chat(this)\" data-target=\"#demo\" data-slide-to=\"0\" style=\"margin-bottom: 2px;\">\n" +
                "              <div class=\" alert alert-warning\" style=\"word-break:break-all; margin: 0px;\">\n" +
                "                  <h5 style=\"text-align: center\">\n" +"�����˿ͷ�"+
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
       //����
        response.setCharacterEncoding("utf-8");
//        ��ȡ��������
        String current_username=request.getParameter("current_user_name");
        String name=request.getParameter("search_string");

//        �������ݿ�,dao
        List<User> users= iuserService.getUsersByName(name);
        User current_user=iuserService.getUserByName(current_username);


        PrintWriter writer=response.getWriter();
        //��õ�ǰ�û��ĺ����б�
        String friends=current_user.getFriends();
        StringTokenizer tokenizer=new StringTokenizer(friends,"%%##");
        int i=tokenizer.countTokens();
        String[] friend_list=new String[i];

       while (--i!=-1)
        {
            friend_list[i]=tokenizer.nextToken();
        }


        //����Ϊ��
        if(name.equals(""))
        {
            writer.print("<div id=\"chat_state1\" style=\"visibility: visible; margin-top: 190px;width: 350px;height: 55px;position: absolute;z-index: 1;\">\n" +
                    "                  <h3 style=\"text-align: center\"><b>���������/ɾ������Ŷ��</b></h3>\n" +
                    "                  </div>");
            return;
        }
        //û��ƥ�䵽
        if(users.isEmpty()) {
            writer.print("<div id=\"chat_state1\" style=\"visibility: visible; margin-top: 190px;width: 350px;height: 55px;position: absolute;z-index: 1;\">\n" +
                    "                  <h3 style=\"text-align: center\"><b>û��ƥ�䵽������</b></h3>\n" +
                    "                  </div>");
            return;
        }


        //�������Լ����û���
        if(name.equals(current_username)){

            writer.print("<div id=\"chat_state1\" style=\"visibility: visible; margin-top: 190px;width: 350px;height: 55px;position: absolute;z-index: 1;\">\n" +
                    "                  <h3 style=\"text-align: center\"><b>�������Լ�����</b></h3>\n" +
                    "                  </div>");
            return;
        }

        //html���������h5style
        StringBuilder result=new StringBuilder();
            for(User user:users) {
                //������Լ�
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
                                    "                              <h5 style=\"height: 40px;\">�Ƴ�</h5>\n" +
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
                                    "                         <h5 style=\"height: 40px;\">���</h5>\n" +
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
            writer.print("<h5>�û�����</h5>");
            return;
        }
        if(users==null)
            writer.print("<h5 style=\"color: green;\">�û���������ʹ��</h5>");
        else
            writer.print("<h5 style=\"color: red;\">�û������ѱ�ռ��</h5>");
    }


}
