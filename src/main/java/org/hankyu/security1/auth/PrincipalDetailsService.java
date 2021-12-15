package org.hankyu.security1.auth;

import org.hankyu.security1.model.User;
import org.hankyu.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// security config의 loginProcessingUrl("/login") 요청이 오면 자동으로 UserDetailsService 타입 빈의 loadUserByUsername을 호출
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null)
            return new PrincipalDetails(user);

        return null;
    }
}
