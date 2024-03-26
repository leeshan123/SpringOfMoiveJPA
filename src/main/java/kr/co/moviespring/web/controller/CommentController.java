// package kr.co.moviespring.web.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import kr.co.moviespring.web.entity.OnelineReview;
// import kr.co.moviespring.web.service.CommentService;

// @Controller
// @RequestMapping("movie/detail")
// public class CommentController {
    
//     @Autowired
//     CommentService commentService;

    // @PostMapping("comment")
    // public String comment(String comments,int rate,Long movieId){
    //     System.out.println("post 요청");

    //     OnelineReview review= commentService.SaveComment(comments,rate,movieId);
        
    //     System.out.println("댓글작성");
    //     return "redirect:/movie/detail?movieid="+movieId;
    // }
// }
