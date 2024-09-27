package projectngo.ngopro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectngo.ngopro.Entity.Comment;
import projectngo.ngopro.Entity.NeighborPosts;
import projectngo.ngopro.Entity.Post;
import projectngo.ngopro.Entity.Reply;
import projectngo.ngopro.Repository.CommentRepository;
import projectngo.ngopro.Repository.PostRepository;
import projectngo.ngopro.Repository.ReplyRepository;
import projectngo.ngopro.Service.CommentService;
import projectngo.ngopro.Service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;


    // Get all posts
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get a post by id along with comments
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }


    @GetMapping("/{id}/neighbors")
    public NeighborPosts getNeighborPosts(@PathVariable Long id) {
        Long prevPostId = postService.findPreviousPost(id);
        Long nextPostId = postService.findNextPost(id);

        return new NeighborPosts(prevPostId, nextPostId);
    }

}