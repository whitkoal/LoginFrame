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
public class SmsLoginUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {

        User user = userDao.getUserByMobile(mobile);

        return new org.springframework.security.core.userdetails.User(
                "bruce",
                "bruce",
                true,
                false,
                false,
                false,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));
    }
}
