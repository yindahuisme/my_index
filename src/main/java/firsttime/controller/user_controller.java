package firsttime.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import firsttime.entries.User;
import firsttime.service.IUserService;
import firsttime.util.Log;
import firsttime.util.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping("/user")
public class user_controller {
    @Resource
    private IUserService iuserService;
    @RequestMapping(value="/log_in.do",method=RequestMethod.GET)
    public String log_in(HttpServletRequest request,Model model){

        return "/log_in.jsp";
    }
    @RequestMapping(value="/sign_in.do",method=RequestMethod.GET)
    public String sign_in(HttpServletRequest request,Model model){
        return "/page/sign_in.jsp";
    }

    @RequestMapping(value="/signin.do")
    public String signin(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Log.log_warn(this,"sign_username:"+username);
        User user=iuserService.getUserByName(username);
        if(user==null)
        {
            User temp=new User();
            temp.setUserName(username);
            String passwd=MD5Util.MD5Encode(password,"utf-8");
            temp.setPassword(passwd);
            temp.setFriends("");
        iuserService.insert(temp);
        request.setAttribute("regist_status","successfull");
        return "/page/sign_in.jsp";
        }
        else {
            request.setAttribute("regist_status","existed");
            return "/page/sign_in.jsp";
        }
    }

    @RequestMapping(value="/login.do")
    public String login(HttpServletRequest request,Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=iuserService.getUserByName(username);
        if(user==null)
        {
            request.setAttribute("log_status","notexisted");
            return "/log_in.jsp";
        }
        else if(!user.getPassword().equals(MD5Util.MD5Encode(password,"utf-8"))){
            request.setAttribute("log_status","no");
            return "/log_in.jsp";
        }
        else{
            request.setAttribute("username",username);
            return "/page/index.jsp";
        }
    }

}
