package com.dev.snsboard.model;

import java.util.Objects;

// Post 데이터를 POST 방식으로 사용하는 RequestBody 라는 뜻으로 클래스명 지음
public class PostPostRequestBody {
    private String body;

    public PostPostRequestBody(String body) {
        this.body = body;
    }

    // @RequestBody 가 정상적으로 돌아가기 위해서 파라미터가 없는 생성자도 필요함
    public PostPostRequestBody() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostPostRequestBody that)) return false;
        return Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getBody());
    }
}
