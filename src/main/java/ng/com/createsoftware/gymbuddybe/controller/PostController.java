package ng.com.createsoftware.gymbuddybe.controller;

import lombok.AllArgsConstructor;
import ng.com.createsoftware.gymbuddybe.model.Post;
import ng.com.createsoftware.gymbuddybe.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/post")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    //add a post
    @PostMapping("/addPost/{userId}")
    public ResponseEntity<String> addPost(@RequestBody Post post, @PathVariable("userId")final Long userId){
        postService.addPost(post, userId);
        return new ResponseEntity<>("Post Added Successfully!", HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/get/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") final Long postId){
        Post post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //edit post
    @PostMapping("/edit/{postId}/{userId}")
    public ResponseEntity<Post> editPost(@PathVariable("postId")final Long postId,
                                         @RequestBody Post post,
                                         @PathVariable("userId") final Long userId){
        Post postToEdit = postService.editPost(post, postId, userId);
        return new ResponseEntity<>(postToEdit, HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") final Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
