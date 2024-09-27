package projectngo.ngopro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectngo.ngopro.Entity.Comment;
import projectngo.ngopro.Service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.findCommentsByPostId(postId);
    }

    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable Long postId, @RequestBody Comment newComment) {
        System.out.println(newComment);
        return commentService.addComment(postId, newComment);
    }
}
