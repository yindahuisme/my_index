package firsttime.util;

import java.io.*;
import java.util.Properties;

public class File_tool {
    public static String encoding;
    static {
        //判断环境
        Properties sysProperty=System.getProperties();
        if(sysProperty.getProperty("os.name").toLowerCase().startsWith("win")){
            encoding="gbk";
        }else {
            encoding="utf-8";
        }
    }

    public static synchronized void append_content(java.io.File file, String append) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(file));
        StringBuilder des_builder=new StringBuilder();
        int len=0;
        char[] buffer=new char[1024];
        while ((len=br.read(buffer))!=-1){
            des_builder.append(new String(buffer,0,len));
        }
        des_builder.append(append);
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(file));
        bufferedOutputStream.write(des_builder.toString().getBytes(),0,des_builder.toString().getBytes().length);
        bufferedOutputStream.flush();
        //释放流
        br.close();
        bufferedOutputStream.close();
    }
}
