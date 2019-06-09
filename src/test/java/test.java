import javax.annotation.Resource;

import firsttime.util.MD5Util;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSON;
import firsttime.entries.User;
import firsttime.service.IUserService;


public class test {
    @Resource
    private IUserService userService = null;

    @Test
    public void test1() {
    System.out.println(MD5Util.MD5Encode("112233","utf-8"));
    }


}