package com.example.demo.service;

import com.example.demo.model.BlogPost;
import com.example.demo.model.PostStatus;
import com.example.demo.model.User;
import com.example.demo.repository.BlogPostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogPostServiceTest {

    @Mock
    private BlogPostRepository blogPostRepository;

    @InjectMocks
    private BlogPostService blogPostService;

    private User user;
    private BlogPost blogPost;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setTitle("Test Post");
        blogPost.setContent("Test Content");
        blogPost.setStatus(PostStatus.DRAFT);
        blogPost.setUser(user);
    }

    @Test
    void getUserPosts_ShouldReturnUserPosts() {
        when(blogPostRepository.findByUserId(user.getId()))
                .thenReturn(Arrays.asList(blogPost));

        List<BlogPost> result = blogPostService.getUserPosts(user);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(blogPost.getTitle(), result.get(0).getTitle());
        verify(blogPostRepository).findByUserId(user.getId());
    }

    @Test
    void getPost_WhenPostExists_AndUserIsOwner_ShouldReturnPost() {
        when(blogPostRepository.findById(1L))
                .thenReturn(Optional.of(blogPost));

        BlogPost result = blogPostService.getPost(1L, user);

        assertNotNull(result);
        assertEquals(blogPost.getTitle(), result.getTitle());
        verify(blogPostRepository).findById(1L);
    }

    @Test
    void getPost_WhenPostDoesNotExist_ShouldThrowException() {
        when(blogPostRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> blogPostService.getPost(1L, user));
    }

    @Test
    void getPost_WhenUserIsNotOwner_ShouldThrowException() {
        User otherUser = new User();
        otherUser.setId(2L);
        otherUser.setUsername("otheruser");

        when(blogPostRepository.findById(1L))
                .thenReturn(Optional.of(blogPost));

        assertThrows(AccessDeniedException.class,
                () -> blogPostService.getPost(1L, otherUser));
    }

    @Test
    void createPost_ShouldSaveAndReturnPost() {
        when(blogPostRepository.save(any(BlogPost.class)))
                .thenReturn(blogPost);

        BlogPost result = blogPostService.createPost(blogPost, user);

        assertNotNull(result);
        assertEquals(blogPost.getTitle(), result.getTitle());
        assertEquals(user, result.getUser());
        verify(blogPostRepository).save(any(BlogPost.class));
    }

    @Test
    void updatePost_WhenPostExistsAndUserIsOwner_ShouldUpdatePost() {
        BlogPost updatedPost = new BlogPost();
        updatedPost.setTitle("Updated Title");
        updatedPost.setContent("Updated Content");
        updatedPost.setStatus(PostStatus.PUBLISHED);

        when(blogPostRepository.findById(1L))
                .thenReturn(Optional.of(blogPost));
        when(blogPostRepository.save(any(BlogPost.class)))
                .thenReturn(updatedPost);

        BlogPost result = blogPostService.updatePost(1L, updatedPost, user);

        assertNotNull(result);
        assertEquals(updatedPost.getTitle(), result.getTitle());
        assertEquals(updatedPost.getContent(), result.getContent());
        assertEquals(updatedPost.getStatus(), result.getStatus());
        verify(blogPostRepository).save(any(BlogPost.class));
    }

    @Test
    void deletePost_WhenPostExistsAndUserIsOwner_ShouldDeletePost() {
        when(blogPostRepository.findById(1L))
                .thenReturn(Optional.of(blogPost));
        doNothing().when(blogPostRepository).delete(any(BlogPost.class));

        assertDoesNotThrow(() -> blogPostService.deletePost(1L, user));
        verify(blogPostRepository).delete(any(BlogPost.class));
    }
}
