package projectngo.ngopro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projectngo.ngopro.Entity.Reply;
import projectngo.ngopro.Service.ReplyService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping("/{commentId}/replies")
    public List<Reply> getRepliesByCommentId(@PathVariable Long commentId) {
        return replyService.findRepliesByCommentId(commentId);
    }

    @PostMapping("/{commentId}/replies")
    public Reply addReply(@PathVariable Long commentId, @RequestBody Reply newReply) {
        return replyService.addReply(commentId, newReply);
    }
}



