//package kr.co.moviespring.web.controller;
//
//import kr.co.moviespring.web.service.ImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartRequest;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//public class ImageController {
//
//    private ImageService imageService;
//
//    @Autowired
//    public ImageController(ImageService imageService) {
//
//        this.imageService = imageService;
//    }
//
//
//    @PostMapping("/image/upload")
//    @ResponseBody
//    public Map<String, Object> imageUpload(@RequestParam("upload") MultipartFile file) {
//        Map<String, Object> responseData = new HashMap<>();
//        try {
//            String imageUrl = imageService.saveImage(file);
//            responseData.put("uploaded", true);
//            responseData.put("url", imageUrl);
//        } catch (IOException e) {
//            responseData.put("uploaded", false);
//            responseData.put("error", "Failed to upload image");
//        }
//        return responseData;
//    }
//
//}
