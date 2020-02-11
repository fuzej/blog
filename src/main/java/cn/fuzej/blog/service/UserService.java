package cn.fuzej.blog.service;

import cn.fuzej.blog.po.User;

/**
 * created by fuzej on  2020-02-09 17:23
 *
 * @Describtion :
 */
public interface UserService
{
    User checkUser(String username, String password);
}
