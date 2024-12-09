package com.dev.snsboard.service;

import com.dev.snsboard.model.Post;
import com.dev.snsboard.model.PostPatchRequestBody;
import com.dev.snsboard.model.PostPostRequestBody;
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
        var newPostId = posts.stream().mapToLong(Post::getPostId).max().orElse(0L) + 1;

        var newPost = new Post(newPostId, postPostRequestBody.body(), ZonedDateTime.now());
        posts.add(newPost);

        return newPost;
    }

    public Post updatePost(Long postId, PostPatchRequestBody postPatchRequestBody) {
        Optional<Post> postOptional=
                posts.stream().filter(post -> postId.equals(post.getPostId())).findFirst();

        if (postOptional.isPresent()) {
            Post postToUpdate = postOptional.get();
            postToUpdate.setBody(postPatchRequestBody.body());
            return postToUpdate;
        } else {
            // 404 error
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.");
        }
    }

    public void deletePost(Long postId) {
        Optional<Post> postOptional=
                posts.stream().filter(post -> postId.equals(post.getPostId())).findFirst();

        if (postOptional.isPresent()) {
            posts.remove(postOptional.get());
        } else {
            // 404 error
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.");
        }
    }
}
