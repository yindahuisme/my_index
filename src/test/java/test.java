import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    static Logger logger = Logger.getLogger ( StringTokenizer.class);
    @Test
public void test1() throws IOException, ParseException {

//    String str1=new String(",,p张三,,p张思,,pmybatis");
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
//        //暂存返回结果，覆盖文件内容
//        StringBuilder result=new StringBuilder();
//        StringBuilder over_file=new StringBuilder();
//        boolean flag=false;
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
//        while (tokenizer.hasMoreTokens()){
//
//            String msg_item=tokenizer.nextToken();
//            //取出元素
//            String time=msg_item.substring(0,19);
//            //判断时间是否已经久远
//            if(flag||new Date().getTime()-sdf.parse(time).getTime()<864000000) {
//                int name_length = Integer.valueOf(msg_item.substring(19, 21)) > 50 ? Integer.valueOf(msg_item.substring(19, 21)) - 50 : Integer.valueOf(msg_item.substring(19, 21)) - 10;
//                String name = msg_item.substring(21, 21 + name_length);
//                String message = msg_item.substring(21 + name_length);
//
//                over_file.append("%%##**"+msg_item);
//
//                //判断我方输入，敌方输入
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
public void test2(){
    Properties sysProperty=System.getProperties();
    if(sysProperty.getProperty("os.name").toLowerCase().startsWith("win"))
    {
        System.out.println("win");
    }

}
}