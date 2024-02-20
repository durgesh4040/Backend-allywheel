package com.allywheel.Model;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.allywheel.Security.UserRole;
import com.allywheel.Security.Oauth2.Oauth2Provider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity

@Table(name="user")
@Data
@Setter
@Getter
@NoArgsConstructor
public class UserModel {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

    private String username;
    private String password;
    private String name;
    @Column(unique=true)
    private String email;
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Oauth2Provider provider;

    private String providerId;

    public UserModel(String username, String password, String name, String email, UserRole role, String imageUrl,  Oauth2Provider provider, String providerId) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.providerId = providerId;
    }
	
}
