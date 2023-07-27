package works.itireland.post.post;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import works.itireland.clients.post.PostRequest;
import works.itireland.clients.post.PostResponse;
import works.itireland.clients.user.UserClient;
import works.itireland.clients.user.UserResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@EnableFeignClients
@Slf4j
public class PostController {

    private final PostService postService;
    private final UserClient userClient;

    @PostMapping
    public PostResponse save(@RequestBody PostRequest postRequest){

        log.info("save post:" + postRequest);
        PostResponse postResponse =  postService.insert(postRequest);
        UserResponse userResponse = userClient.find(postRequest.getUserId());
        postResponse.setUser(userResponse);
        return postResponse;
    }

    @GetMapping
    public List<PostResponse> findAll(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "10") int pageSize){
        log.info("find posts by page:" + page +", pageSize:" +pageSize);
        Pageable pageable = PageRequest.of(page, pageSize);
        return postService.findAll(pageable).toList();
    }


    @GetMapping("/user/{userId}")
    public List<PostResponse> findAllByUserId(@PathVariable Long userId, @RequestParam(required = false, defaultValue = "0") int page,
                                            @RequestParam(required = false, defaultValue = "10") int pageSize){
        log.info("find posts by userId and page:" + page +", pageSize:" +pageSize);
        Pageable pageable = PageRequest.of(page, pageSize);
        return postService.findAllByUserId(userId, pageable);
    }

    @GetMapping("/followings/user/{userId}")
    public List<PostResponse> findFollowingsByUserId(@PathVariable Long userId, @RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize){
        log.info("find followings by userId and page:" + page +", pageSize:" +pageSize);
        Pageable pageable = PageRequest.of(page, pageSize);
        return postService.findFollowingsByUserId(userId, pageable);
    }




}