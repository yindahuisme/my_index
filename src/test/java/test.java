import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    static Logger logger = Logger.getLogger ( StringTokenizer.class);
    @Test
public void test1() throws IOException, ParseException {

//    String str1=new String(",,p����,,p��˼,,pmybatis");
//        StringTokenizer tokenizer=new StringTokenizer(str1,",,p");
//        System.out.println("tokens:"+tokenizer.countTokens());
//        while (tokenizer.hasMoreTokens())
//        {
//            System.out.println(tokenizer.nextToken());
//        }
//logger.info("hello");
////
//        String resource =  (this.getClass().getClassLoader().getResource("./").getPath()+"test.txt").substring(1);
//        System.out.println(resource);
//        File msg=new File(resource);
//
//
//

//

//
//        BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(msg));
//        StringBuilder builder=new StringBuilder();
//        int len=0;
//        byte[] buffer=new byte[1024];
//        while ((len=bufferedInputStream.read(buffer))!=-1){
//            builder.append(new String(buffer,"utf-8"));
//        }
//        StringTokenizer tokenizer=new StringTokenizer("%%##**");
//
//        //�ݴ淵�ؽ���������ļ�����
//        StringBuilder result=new StringBuilder();
//        StringBuilder over_file=new StringBuilder();
//        boolean flag=false;
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
//        while (tokenizer.hasMoreTokens()){
//
//            String msg_item=tokenizer.nextToken();
//            //ȡ��Ԫ��
//            String time=msg_item.substring(0,19);
//            //�ж�ʱ���Ƿ��Ѿ���Զ
//            if(flag||new Date().getTime()-sdf.parse(time).getTime()<864000000) {
//                int name_length = Integer.valueOf(msg_item.substring(19, 21)) > 50 ? Integer.valueOf(msg_item.substring(19, 21)) - 50 : Integer.valueOf(msg_item.substring(19, 21)) - 10;
//                String name = msg_item.substring(21, 21 + name_length);
//                String message = msg_item.substring(21 + name_length);
//
//                over_file.append("%%##**"+msg_item);
//
//                //�ж��ҷ����룬�з�����
//                if(Integer.valueOf(msg_item.substring(19, 21))>50)
//                {
//                    result.append("   <div style=\"height: 25px;text-align: center\"><h5>2019.06.12 06.36.51</h5></div>\n" +
//                            "                <div class=\"row\" style=\"margin-bottom: 10px;width: 350px;\">\n" +
//                            "                <div class=\"col-sm-2\" style=\"height: 40px;\">\n" +
//                            "                  <img src=\"/rec/image/rect.png\" class=\"rounded-circle\" style=\" width: 40px;height: 40px;\">\n" +
//                            "                </div>\n" +
//                            "                <div class=\"col-sm-10 alert alert-success\" style=\"word-break:break-all; margin: 0px;\">\n" +
//                            "                  <h5>\n" +
//                            "                    hello\n" +
//                            "                  </h5>\n" +
//                            "                </div>\n" +
//                            "              </div>");
//                }else{
//                    result.append("   <div style=\"height: 25px;text-align: center\"><h5>2019.06.12 06.36.51</h5></div>\n" +
//                            "                <div class=\"row\" style=\"margin: 0px 0px 10px 35px;width: 350px;\">\n" +
//                            "                  <div class=\"col-10 alert alert-success\" style=\"word-break:break-all; margin: 0px;\">\n" +
//                            "                    <h5>\n" +
//                            "                        hello\n" +
//                            "                    </h5>\n" +
//                            "                </div>\n" +
//                            "                <div class=\"col-2\" style=\"height: 40px;padding:0px 0px 0px 18px;\">\n" +
//                            "                  <img src=\"/rec/image/bg.jpg\" class=\"rounded-circle\" style=\" width: 40px;height: 40px;\">\n" +
//                            "                </div>\n" +
//                            "              </div>");
//                }
//
//                flag=true;
//            }
//        }

      //  msg.createNewFile();
//
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
//        System.out.println(dateFormat.format(new Date()));


}
@Test
public void test2() throws IOException {
//    Properties sysProperty=System.getProperties();
//    if(sysProperty.getProperty("os.name").toLowerCase().startsWith("win"))
//    {
//        System.out.println("win");
//    }


//    http://api.qingyunke.com/
//    ���ܻ���������
//    String requesturl = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=����˭��";
//HttpUriRequest request = new HttpGet(requesturl);
//
//CloseableHttpResponse response = HttpClients.createDefault().execute(request);
//if(response.getStatusLine().getStatusCode()==200){
//
//HttpEntity entity = response.getEntity();
//StringBuilder result=new StringBuilder();
//String content="";
//int len=0;
//byte[] buffer=new byte[1024];
//while ((len=entity.getContent().read(buffer))!=-1){
//    result.append(new String(buffer,0,len,"utf-8"));
//}
//StringTokenizer tokenizer=new StringTokenizer(result.toString().substring(1,result.toString().length()-1),",");
//
//while (tokenizer.hasMoreTokens()){
//    Integer status=Integer.valueOf(tokenizer.nextToken().substring(9));
//    if(status==0){
//        //����״̬
//        String temp=tokenizer.nextToken();
//        content=temp.substring(11,temp.length()-1);
//                break;
//    }else{
//        content="�����˹��ˣ����Ժ������԰ɣ�";break;
//    }
//}
//System.out.println("���ؽ����"+content);
//
//}
//    BufferedReader br;
//    InputStream is;
//
//        String request= "����˭��";
//        String info = URLEncoder.encode(request, "utf-8");
//        String APIkey = "60fe6afc27cf41f2af702d9086b795f7";
//        String getUrl = "http://www.tuling123.com/openapi/api?key="
//                +APIkey+"&info="+info;
//        URL url = new URL(getUrl);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.connect();
//        is = conn.getInputStream();
//        InputStreamReader isr = new InputStreamReader(is, "utf-8");
//
//        br = new BufferedReader(isr);
//        String line = "";
//        StringBuffer sb = new StringBuffer();
//        while((line = br.readLine()) != null){
//            sb.append(line);
//        }
//        String sb2 = sb.substring(sb.lastIndexOf(":"));
//        System.out.println("Сͼͼ:"+sb2.toString());
//        br.close();
//	    is.close();
//
//    String content="{br}";
//    String tmp=content.replaceAll("br}","<br>");
//    System.out.println(tmp);

 }




}