package com.hgstars.service.aaa.service;

import com.hgstars.api.aaa.IAccountService;
import com.hgstars.models.mysql.User;
import com.hgstars.service.aaa.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Encodes;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yujindong on 16/12/21.
 */
@Service
@Transactional
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;
    private Clock clock = Clock.DEFAULT;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Autowired
    private UserDao userDao;

    @Cacheable(value = "account", key = "'account_list_all'")
    public List<User> getAllUsers() {
        List<User> userList = (List<User>) userDao.findAll();
        logger.info("userList{}", userList);
        return userList;
    }
    @Cacheable(value = "system.config", key = "'system.config_test'+#id")
    public long test(int id) {
        logger.info("无缓存");
        long result = System.currentTimeMillis();
        return result;
    }

    public void register(User user) {
        entryptPassword(user);
        user.setRegisterDate(clock.getCurrentDate());
        logger.info("用户: {} 在 {} 注册", user.getLoginName(), simpleDateFormat.format(user.getRegisterDate()));
        userDao.save(user);
    }

    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
}
