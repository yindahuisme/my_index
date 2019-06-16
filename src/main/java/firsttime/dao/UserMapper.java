package firsttime.dao;

import firsttime.entries.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userName);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    //×Ô¶¨Òå
    List<User> selectByUserName(String name);
}