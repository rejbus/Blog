package com.Rejbo.Dawid.blog.controller;

import com.Rejbo.Dawid.blog.model.Post;
import com.Rejbo.Dawid.blog.model.PostComment;
import com.Rejbo.Dawid.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class MainController {

    @Autowired
    PostRepository postRepository;

    public MainController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String getIndexPage(Model model) {
        model.addAttribute("name", "Dawid");
        return "index";
    }

    @GetMapping("/addPost")
    public String addPostPage() {
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam(value = "title") String title,
                          @RequestParam(value = "content") String content) {
        Post post = new Post(title, content);
        PostComment postComment = new PostComment();
        post.addComment(postComment);
        postRepository.save(post);
        return "index";
    }

    @GetMapping("/posts")
    public String postsPage(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts";
    }

    @GetMapping("/posts/{title}")
    public String postsByTitle(@PathVariable String title, Model model) {
        model.addAttribute("posts", postRepository.findAllByTitleContains(title));
        return "posts";
    }

    @GetMapping("/posts/{title}/{sortfield}/{sortDirection}")
    public String postsByTitle(@PathVariable String title,
                               @PathVariable String sortfield,
                               @PathVariable String sortDirection, Model model) {
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equals(sortDirection)){
            direction= Sort.Direction.DESC;
        }

        List<Post> postList = postRepository.findAllByTitleContains(title,
                Sort.by(Sort.Direction.fromString(sortDirection),sortfield));
        model.addAttribute("posts", postList);
        return "posts";
    }

}

