package com.dev.snsboard.repository;

import com.dev.snsboard.model.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostEntityRepository extends JpaRepository<PostEntity, Long> {
}
