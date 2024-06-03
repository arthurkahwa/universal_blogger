package de.chapter.mill.controller;

import de.chapter.mill.entity.Post;
import de.chapter.mill.entity.User;
import de.chapter.mill.entity.UserPost;
import de.chapter.mill.repository.PostRepository;
import de.chapter.mill.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger LOGGER = LogManager.getLogger(ApiController.class.getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("posts")
    public ResponseEntity<List<Post>> createMultiplePosts(@RequestBody List<Post> inputPostList) {
        LOGGER.trace("Create multiple users");

        try {
            List<Post> postList = postRepository.saveAll(inputPostList);

            return new ResponseEntity<>(postList, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("post")
    public ResponseEntity<Post> createSinglePost(@RequestBody Post inputPost) {
        LOGGER.trace("Create a single Post");

        try {
            Post createdPost = postRepository.save(inputPost);

            return new ResponseEntity<>(createdPost, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @GetMapping("post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        LOGGER.trace("Get a single post using its id");

        try {
            Optional<Post> post = postRepository.findById(id);

            return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @GetMapping("posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        LOGGER.trace("Get all posts");

        try {
            List<Post> postList = postRepository.findAll();

            if (postList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(postList, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @GetMapping("posts/{userId}")
    public ResponseEntity<List<Post>> getAllPostsForUser(@PathVariable Long userId) {
        LOGGER.trace("Get Posts for User with id");
        try {
            List<Post> postList = new ArrayList<>(postRepository.findAllByUserId(userId));

            return new ResponseEntity<>(postList, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("post/{id}")
    public ResponseEntity<HttpStatus> deletePostById(@PathVariable Long id) {
        LOGGER.trace("Delete a single Post");

        try {
            postRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        LOGGER.trace("Get User by Id");

        try {
            Optional<User> user = userRepository.findById(id);

            return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("user/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id) {
        LOGGER.trace("Dele a single user");

        try {
            userRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("post/{id}")
    public ResponseEntity<Post> updateSinglePost(@PathVariable("id") Long id,
                                                 @RequestBody Post inputPost) {
        LOGGER.trace("Update a single post");

        try {
            Optional<Post> outputPost = postRepository.findById(id);

            if (outputPost.isPresent()) {
                if (inputPost.equals(outputPost.get())) {
                    return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
                }
                else {
                    Post post = outputPost.get();
                    post.setBody(inputPost.getBody());
                    post.setTitle(inputPost.getTitle());

                    Optional<User> user = userRepository.findById(inputPost.getUserId());
                    if (user.isPresent()) {
                        if (!(user.get().getId() == inputPost.getUserId())) {
                            post.setUserId(inputPost.getUserId());
                        }
                    }

                    return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);

                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        LOGGER.trace("Get all users");
         try {
             List<User> userList = new ArrayList<>(userRepository.findAll());

             if (userList.isEmpty()) {
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
             }

             ResponseEntity<List<User>> foundUsers =  new ResponseEntity<>(userList, HttpStatus.OK);

             return foundUsers;
         }
         catch (Exception exception) {
             return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("user")
    public ResponseEntity<User> createSingleUser(@RequestBody User user) {
        try {
            User _user = userRepository
                    .save(new User(user.getUsername(), user.getEmail()));
            return new ResponseEntity<>(_user, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("users")
    public ResponseEntity<List<User>> createMultipleUsers(@RequestBody List<User> inputUsers) {
        LOGGER.trace("Create a group of users");
        try {
            List<User> createdUserList = userRepository.saveAll(inputUsers);

            return new ResponseEntity<>(createdUserList, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @GetMapping("user/posts/{id}")
    public ResponseEntity<UserPost> getSingleUserWithPosts(@PathVariable("id") Long id) {
        try {
            LOGGER.trace("Get a user with all their posts");
            Optional<User> optionalUserObject = userRepository.findById(id);

            if (optionalUserObject.isPresent()) {
                List<Post> userPosts = postRepository.findAllByUserId(id);

                User user = optionalUserObject.get();
                UserPost userWithPosts = new UserPost(user.getId(), user.getUsername(), user.getEmail(), userPosts);

                return new ResponseEntity<>(userWithPosts, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a list of all the users with corresponding posts.
     *
     * @return userList
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @GetMapping("users/posts")
    public ResponseEntity<List<UserPost>> getAllUsersAndPosts() {
        LOGGER.trace("List all users and corresponding posts");

        try {
            List<User> userList = new ArrayList<>(userRepository.findAll());

            if (userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<UserPost> userPostList = new ArrayList<>();

            userList.forEach(user -> {
                List<Post> postList = postRepository.findAllByUserId(user.getId());

                UserPost userWithPosts = new UserPost(user.getId(),
                                                      user.getUsername(),
                                                      user.getEmail(),
                                                      postList);

                userPostList.add(userWithPosts);
            });

            return new ResponseEntity<>(userPostList, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
