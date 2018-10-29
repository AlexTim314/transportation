package org.ivc.transportation.services;

import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sokolov Slava
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.userRepository.findByUserName(userName);

        if (appUser == null) {
//            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

//        System.out.println("Found User: " + appUser);

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (appUser.getRoles() != null) {
            appUser.getRoles().forEach(r -> {
                GrantedAuthority authority = new SimpleGrantedAuthority(r.getRoleName());
                grantList.add(authority);
            });
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getPassword(), grantList);

        return userDetails;
    }

}
