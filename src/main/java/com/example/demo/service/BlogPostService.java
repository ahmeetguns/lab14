package com.example.demo.service;

import com.example.demo.model.BlogPost;
import com.example.demo.model.User;
import com.example.demo.repository.BlogPostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;

    @Transactional(readOnly = true)
    public List<BlogPost> getUserPosts(User user) {
        log.info("Fetching posts for user: {}", user.getUsername());
        return blogPostRepository.findByUserId(user.getId());
    }

    @Transactional(readOnly = true)
    public BlogPost getPost(Long postId, User user) {
        log.info("Fetching post {} for user: {}", postId, user.getUsername());
        BlogPost post = blogPostRepository.findById(postId)
                .orElseThrow(() -> {
                    log.warn("Post {} not found", postId);
                    return new EntityNotFoundException("Post not found");
                });
        
        if (!post.getUser().getId().equals(user.getId())) {
            log.warn("Access denied: User {} attempted to access post {} owned by {}", 
                    user.getUsername(), postId, post.getUser().getUsername());
            throw new AccessDeniedException("You don't have permission to access this post");
        }
        
        return post;
    }

    @Transactional
    public BlogPost createPost(BlogPost post, User user) {
        log.info("Creating new post for user: {}", user.getUsername());
        post.setUser(user);
        BlogPost savedPost = blogPostRepository.save(post);
        log.info("Created post {} for user {}", savedPost.getId(), user.getUsername());
        return savedPost;
    }

    @Transactional
    public BlogPost updatePost(Long postId, BlogPost updatedPost, User user) {
        log.info("Updating post {} for user: {}", postId, user.getUsername());
        BlogPost existingPost = getPost(postId, user);
        
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        existingPost.setStatus(updatedPost.getStatus());
        
        BlogPost savedPost = blogPostRepository.save(existingPost);
        log.info("Updated post {} for user {}", postId, user.getUsername());
        return savedPost;
    }

    @Transactional
    public void deletePost(Long postId, User user) {
        log.info("Deleting post {} for user: {}", postId, user.getUsername());
        BlogPost post = getPost(postId, user);
        blogPostRepository.delete(post);
        log.info("Deleted post {} for user {}", postId, user.getUsername());
    }
}
