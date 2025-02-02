package com.example.demo.controller;

import com.example.demo.model.BlogPost;
import com.example.demo.model.User;
import com.example.demo.service.BlogPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class BlogPostController {
    private final BlogPostService blogPostService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BlogPost>> getUserPosts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(blogPostService.getUserPosts(user));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlogPost> getPost(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(blogPostService.getPost(id, user));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlogPost> createPost(@Valid @RequestBody BlogPost post, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(blogPostService.createPost(post, user));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlogPost> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody BlogPost post,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(blogPostService.updatePost(id, post, user));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @AuthenticationPrincipal User user) {
        blogPostService.deletePost(id, user);
        return ResponseEntity.noContent().build();
    }
}
