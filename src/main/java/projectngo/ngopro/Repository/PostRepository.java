package projectngo.ngopro.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectngo.ngopro.Entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
