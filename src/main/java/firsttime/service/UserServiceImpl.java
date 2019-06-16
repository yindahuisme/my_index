package firsttime.service;

import javax.annotation.Resource;

import firsttime.entries.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import firsttime.dao.UserMapper;

import java.util.List;


@Service("userService")
@Repository
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userDao;

    public int insert(User record){
        return this.userDao.insert(record);
    }
    public User getUserByName(String userName) {
        return this.userDao.selectByPrimaryKey(userName);
    }
    public List<User> getUsersByName(String userName) {
      return   userDao.selectByUserName(userName);
    }
    public int updateByPrimaryKeySelective(User record){ return userDao.updateByPrimaryKeySelective(record);}

}