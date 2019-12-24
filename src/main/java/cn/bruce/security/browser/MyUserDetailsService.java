package cn.bruce.security.browser;

import cn.bruce.dao.UserDao;
import cn.bruce.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据用户名在数据库中查找用户信息，并封装为UserDetails以供security进行权限认证等操作
     *
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查找用户信息
        User user = userDao.getUserByName(username);

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getStore()));
    }
}
