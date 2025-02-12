package com.url.shortener.security.serviceSec;

import com.url.shortener.models.User;
import com.url.shortener.repositories.Six_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/* Este clase hara el trabajo de que interactuen entre si la base de datos
    y el contexto de la autenticacion */
@Service
public class Five_UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    Six_UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
        return One_UserDetailsImpl.build(user);

    }
}
