package works.itireland.post.comment;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import works.itireland.auth.AuthUtils;
import works.itireland.auth.AuthorizedRoles;
import works.itireland.clients.R;
import works.itireland.clients.post.CommentRequest;
import works.itireland.clients.post.CommentResponse;
import works.itireland.clients.user.UserClient;
import works.itireland.clients.user.UserResponse;
import works.itireland.exception.ValidationException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final UserClient userClient;
    private final CommentService commentService;
    @PostMapping
    @AuthorizedRoles(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public R<CommentResponse> save(HttpServletRequest request, @Validated @RequestBody CommentRequest commentRequest, BindingResult errors){

        //Throw Validation Exception
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        String username = AuthUtils.getUserName(request);
        UserResponse user = userClient.findByUsername(username).getData();
        commentRequest.setUserId(user.getId());

        return R.success(commentService.save(commentRequest));
    }

    @GetMapping
    public R<List<CommentResponse>> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                     @RequestParam(defaultValue = "100", required = false) Integer size,
                     @RequestParam(defaultValue = "ctime", required = false) String sort,
                     @RequestParam String postId
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        List<CommentResponse> comments = commentService.findAllByPostId(postId, pageable);
        return R.success(comments);

    }

    @GetMapping("/all")
    public R<List<CommentResponse>> findAllComments(@RequestParam(defaultValue = "0", required = false) Integer page,
                                            @RequestParam(defaultValue = "100", required = false) Integer size,
                                            @RequestParam(defaultValue = "ctime", required = false) String sort
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        List<CommentResponse> comments = commentService.findAll(pageable);
        return R.success(comments);

    }

    @DeleteMapping("/secure/{commentId}")
    @AuthorizedRoles(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public R delete(HttpServletRequest request, @PathVariable String commentId){
        String username = AuthUtils.getUserName(request);
        UserResponse user = userClient.findByUsername(username).getData();
        commentService.delete(user.getId(), commentId);
        return R.success(null);
    }

    @GetMapping("/count")
    public long count() {
        return commentService.count();
    }


    @GetMapping("/{id}")
    public R<CommentResponse> getById(@PathVariable String id) {
        return R.success(commentService.findById(id));
    }
}
