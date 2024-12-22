package com.dev.snsboard.model.user;

import com.dev.snsboard.model.entity.UserEntity;

import java.time.ZonedDateTime;

public record User(
        Long userId,
        String username,
        String profile,
        String description,
        ZonedDateTime createdDateTime,
        ZonedDateTime updatedDateTime) {

    // user Entity 를 user record 로 쉽게 변환할 수 있도록 함
    public static User from(UserEntity userEntity) {
        return new User(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getProfile(),
                userEntity.getDescription(),
                userEntity.getCreatedDateTime(),
                userEntity.getUpdatedDateTime()
        );
    }
}