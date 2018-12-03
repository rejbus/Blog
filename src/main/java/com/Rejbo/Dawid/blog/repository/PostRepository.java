package com.Rejbo.Dawid.blog.repository;

import com.Rejbo.Dawid.blog.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByTitleContains(String title);
    List<Post> findAllByTitleContains(String title, Sort sort);
    List<Post> findAllByTitleContainsOrContentContains(String title, String content);
}
