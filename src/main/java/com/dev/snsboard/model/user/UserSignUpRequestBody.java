package com.dev.snsboard.model.user;

// record를 사용하여 사용하고자 하는 필드만 나열
public record UserSignUpRequestBody(String username, String password) {

}
