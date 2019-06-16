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

    public static void append_content(java.io.File file, String append) throws IOException {
        BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(file));
        StringBuilder des_builder=new StringBuilder();
        int len=0;
        byte[] buffer=new byte[1024];
        while ((len=bufferedInputStream.read(buffer))!=-1){
            des_builder.append(new String(buffer,0,len,encoding));
        }
        des_builder.append(append);
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(file));
        bufferedOutputStream.write(des_builder.toString().getBytes(),0,des_builder.toString().getBytes().length);
        bufferedOutputStream.flush();
        //释放流
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}
