package ng.com.createsoftware.gymbuddybe.repository;

import ng.com.createsoftware.gymbuddybe.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
