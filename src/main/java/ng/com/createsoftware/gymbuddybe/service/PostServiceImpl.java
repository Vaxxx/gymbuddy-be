package ng.com.createsoftware.gymbuddybe.service;

import ng.com.createsoftware.gymbuddybe.model.Post;
import ng.com.createsoftware.gymbuddybe.model.User;
import ng.com.createsoftware.gymbuddybe.repository.PostRepository;
import ng.com.createsoftware.gymbuddybe.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Post addPost(Post post, Long userId) {
        User user = userRepository.findById(userId).get();

        Post newPost = new Post();

        return extractedPost(post, user, newPost);
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("Could not find Post with ID: " + postId));
    }

    @Override
    public Post getPostById(Long postId) {
        return getPost(postId);
    }

    @Override
    public Post editPost(Post post, Long postId, Long userId) {

        User user = userRepository.findById(userId).get();

        Post postToEdit = getPost(postId);
       return extractedPost(post, user, postToEdit);
    }

    private Post extractedPost(Post post, User user, Post postToEdit) {
        postToEdit.setTitle(post.getTitle());
        postToEdit.setBody(post.getBody());
        postToEdit.setUser(user);

        postRepository.save(postToEdit);
        return postToEdit;
    }

    @Override
    public Post deletePost(Long postId) {
        Post post = getPost(postId);
        postRepository.delete(post);
        return post;
    }
}
