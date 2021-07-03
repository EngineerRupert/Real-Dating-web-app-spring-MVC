package ru.realdating.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;

@Component
public class DetailsService implements UserDetailsService {

    @Autowired
    public UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User found = userDao.findUserByLogin(s);
        if (found == null) {
            throw new UsernameNotFoundException("User " + s + " not found");
        }

        return org.springframework.security.core.userdetails.User.withUsername(s)
                .password(found.getPassword())
                .roles()
                .build();
    }
}
