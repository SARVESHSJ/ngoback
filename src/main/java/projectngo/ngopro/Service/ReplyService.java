package projectngo.ngopro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectngo.ngopro.Entity.Comment;
import projectngo.ngopro.Entity.Reply;
import projectngo.ngopro.Repository.CommentRepository;
import projectngo.ngopro.Repository.ReplyRepository;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Reply> findRepliesByCommentId(Long commentId) {
        return replyRepository.findByCommentId(commentId);
    }

    public Reply addReply(Long commentId, Reply newReply) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            newReply.setComment(comment);
            return replyRepository.save(newReply);
        }
        return null;
    }
}
