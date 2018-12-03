package com.Rejbo.Dawid.blog.controller;

import com.Rejbo.Dawid.blog.model.Post;
import com.Rejbo.Dawid.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class SearchController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/search")
    public String search(@RequestParam String searchPhrase, Model model) {
        List<Post> postList = postRepository.findAllByTitleContainsOrContentContains(searchPhrase, searchPhrase);
        model.addAttribute("posts", postList);
        model.addAttribute("searchPhrase", searchPhrase);
        return "posts";
    }
}

