package firsttime.service;

import firsttime.entries.User;

import java.util.List;

public interface IUserService {
    public User getUserByName(String userName);
    public int insert(User record);
    public List<User> getUsersByName(String userName);
    public int updateByPrimaryKeySelective(User record);

}
