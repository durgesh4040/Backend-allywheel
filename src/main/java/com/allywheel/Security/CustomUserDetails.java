package com.allywheel.Security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.allywheel.Security.Oauth2.*;

import lombok.Builder;
import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails,OAuth2User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String avatarUrl;
    private Oauth2Provider provider;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
