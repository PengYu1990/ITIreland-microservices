package works.itireland.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import works.itireland.post.domain.Post;


@Repository
public interface PostRepository extends MongoRepository<Post, Long> {
}