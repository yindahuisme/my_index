package firsttime.util;
import org.apache.log4j.Logger;

import java.util.Date;

public class Log {

    public static void log_info(Object obj, String info){
       Logger logger=Logger.getLogger (obj.getClass());
        logger.info(new Date()+"---"+info);
    }
    public static void log_warn(Object obj, String warn){
        Logger logger=Logger.getLogger (obj.getClass());
        logger.warn(new Date()+"---"+warn);
    }
}
