package com.dev.snsboard.service;

import com.dev.snsboard.model.Post;
import com.dev.snsboard.model.PostPatchRequestBody;
import com.dev.snsboard.model.PostPostRequestBody;
import com.dev.snsboard.model.entity.PostEntity;
import com.dev.snsboard.repository.PostEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostEntityRepository postEntityRepository;

    // 모든 게시물 조회
    public List<Post> getPosts() {
        // 내부적으로 적절한 SQL로 변환되어 실행됨.
        var postEntities = postEntityRepository.findAll();

        // RepositoryfindAll을 통해 가져온 Entity List를 map을 통해서 post Record로 변환한 다음에 List로 만들어 리턴
        return postEntities.stream().map(Post::from).toList();
    }

    public Post getPostByPostId(Long postId) {
        var postEntity =
            postEntityRepository
                    .findById(postId)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.")
                    );

        return Post.from(postEntity);
    }

    public Post createPost(PostPostRequestBody postPostRequestBody) {
        // 새로운 post data 를 만들기 위해서 생성
        var postEntity = new PostEntity();
        postEntity.setBody(postPostRequestBody.body());
        // 실제 저장 수행 하여 변수로 받기
        var savedPostEntity = postEntityRepository.save(postEntity);
        return Post.from(savedPostEntity);

    }

    public Post updatePost(Long postId, PostPatchRequestBody postPatchRequestBody) {
        var postEntity =
            postEntityRepository
                .findById(postId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found."));
        postEntity.setBody(postPatchRequestBody.body());
        var updatedPostEntity = postEntityRepository.save(postEntity);
        return Post.from(updatedPostEntity);
    }

    public void deletePost(Long postId) {
        var postEntity =
            postEntityRepository
                .findById(postId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found."));
        postEntityRepository.delete(postEntity);
    }
}
