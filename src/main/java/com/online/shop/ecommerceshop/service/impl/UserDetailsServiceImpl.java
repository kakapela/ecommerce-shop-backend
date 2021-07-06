package com.online.shop.ecommerceshop.service.impl;

import com.online.shop.ecommerceshop.domain.User;
import com.online.shop.ecommerceshop.domain.UserPrincipal;
import com.online.shop.ecommerceshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.online.shop.ecommerceshop.constant.UserImplConstant.FOUND_USER_BY_USERNAME;
import static com.online.shop.ecommerceshop.constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username);

        if(user == null){
            log.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        }
        else {
            UserPrincipal userPrincipal = new UserPrincipal(user);
            log.info(FOUND_USER_BY_USERNAME + username);
            return userPrincipal;
        }
    }
}
