package projectngo.ngopro.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectngo.ngopro.Entity.Reply;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
    List<Reply> findByCommentId(Long commentId);
}
