package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Optional;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getAllCommentsByPost(@PathVariable(name = "postId") long postId) {
        return commentRepository.findCommentsByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getCommentByPost(@PathVariable(name = "postId") long postId,
                                                  @PathVariable(name = "commentId") long commentId) {
        Comment comment = commentRepository.findCommentByIdAndPostId(commentId, postId);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found!");
        }
        return comment;
    }

    @PostMapping(path = "/{postId}/comments")
    public Iterable<Comment> createCommentForPost(@PathVariable(name = "postId") long postId,
                                                  @RequestBody Comment newComment) {
        Optional<Post> existedPost = postRepository.findById(postId);
        if (existedPost.isEmpty()) {
            throw new ResourceNotFoundException("Post not found!");
        }
        newComment.setPost(existedPost.get());
        commentRepository.save(newComment);
        return commentRepository.findCommentsByPostId(postId);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateCommentByPost(@PathVariable(name = "postId") long postId,
                                       @PathVariable(name = "commentId") long commentId,
                                       @RequestBody Comment updatedComment) {
        Comment comment = commentRepository.findCommentByIdAndPostId(commentId, postId);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found!");
        }
        updatedComment.setId(commentId);
        updatedComment.setPost(comment.getPost());
        return commentRepository.save(updatedComment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteCommentByPost(@PathVariable(name = "postId") long postId,
                                       @PathVariable(name = "commentId") long commentId) {
        Comment comment = commentRepository.findCommentByIdAndPostId(commentId, postId);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found!");
        }
        commentRepository.delete(comment);
    }
    // END
}
