package firsttime.service;

import firsttime.entries.User;

public interface IUserService {
    public User getUserByName(String userName);
    public int insert(User record);
}
