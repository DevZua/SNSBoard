package com.dev.snsboard.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
@SQLDelete(sql = "UPDATE \"user\" SET deleteddatetime =  CURRENT_TIMESTAMP WHERE userid = ?")
@SQLRestriction("deletedDatetime IS NULL")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String profile;

    @Column
    private String description;

    @Column
    private ZonedDateTime createdDateTime;

    @Column
    private ZonedDateTime updatedDateTime;

    @Column
    private ZonedDateTime deletedDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getProfile(), that.getProfile()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCreatedDateTime(), that.getCreatedDateTime()) && Objects.equals(getUpdatedDateTime(), that.getUpdatedDateTime()) && Objects.equals(getDeletedDateTime(), that.getDeletedDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getPassword(), getProfile(), getDescription(), getCreatedDateTime(), getUpdatedDateTime(), getDeletedDateTime());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

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

    public static UserEntity of(String username, String password) {
        var userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);

        userEntity.setProfile("https://avatar.iran.liara.run/public/" + new Random().nextInt(100));

        return userEntity;
    }

    @PrePersist
    private void prePersist() {
        this.createdDateTime = ZonedDateTime.now();
        this.updatedDateTime =  this.createdDateTime;
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedDateTime = ZonedDateTime.now();
    }
}
