package de.chapter.mill.repository;

import de.chapter.mill.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /// @Query("SELECT p FROM Post WHERE p.userId = :userId")
    List<Post> findAllByUserId(Long userId);
}
