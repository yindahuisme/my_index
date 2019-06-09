package firsttime.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import firsttime.dao.UserMapper;
import firsttime.entries.User;


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
}