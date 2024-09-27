package projectngo.ngopro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import projectngo.ngopro.Entity.Section;
import projectngo.ngopro.Service.PostService;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/admin/addPost")
    public ResponseEntity<String> addPost(
            @RequestParam("title") String title,
            @RequestParam("authorName") String authorName,
            @RequestParam("content") String content,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        try {
            // Convert content (JSON string) to a list of sections
            List<Section> sections = postService.parseContent(content);

            // Save post (including image if uploaded)
            postService.savePost(title,authorName,sections, image);

            return ResponseEntity.ok("Post added successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to save the post");
        }
    }
}
