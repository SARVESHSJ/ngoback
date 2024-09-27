package projectngo.ngopro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectngo.ngopro.Entity.Comment;
import projectngo.ngopro.Entity.Post;
import projectngo.ngopro.Repository.CommentRepository;
import projectngo.ngopro.Repository.PostRepository;

import java.util.List;

@Service
public class CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Comment> findCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment addComment(Long postId, Comment newComment) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            newComment.setPost(post);
            return commentRepository.save(newComment);
        }
        return null;
    }
}