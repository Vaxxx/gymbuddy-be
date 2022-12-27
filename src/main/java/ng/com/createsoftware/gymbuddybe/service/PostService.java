package ng.com.createsoftware.gymbuddybe.service;

import ng.com.createsoftware.gymbuddybe.model.Location;
import ng.com.createsoftware.gymbuddybe.model.Post;

public interface PostService {

    Post addPost(Post post, Long userId);

    Post getPost(Long postId);

    Post getPostById(Long postId);

    Post editPost(Post post, Long postId, Long userId);

    Post deletePost(Long postId);
}
