package cn.fuzej.blog.service.impl;


import cn.fuzej.blog.dao.UserRepository;
import cn.fuzej.blog.po.User;
import cn.fuzej.blog.service.UserService;
import cn.fuzej.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by fuzej on  2020-02-09 17:24
 *
 * @Describtion :
 */
/*
* @Service
*   1.和@Controller 、@Mapper 的区别
*   2.要不要启动类扫描包
* */
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password)
    {
        return userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
    }

}
