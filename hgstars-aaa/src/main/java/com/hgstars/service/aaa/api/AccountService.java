package com.hgstars.service.aaa.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.hgstars.api.aaa.IAccountService;
import com.hgstars.models.mysql.User;
import com.hgstars.service.aaa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yujindong on 17/1/4.
 */
@Service
public class AccountService implements IAccountService{

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private UserService userService;

    @Override
    public long test(int id) {
        logger.info("test account service");
        return userService.test(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
