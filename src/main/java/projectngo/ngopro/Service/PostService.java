package projectngo.ngopro.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projectngo.ngopro.Entity.Post;
import projectngo.ngopro.Entity.Section;
import projectngo.ngopro.Repository.PostRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private S3Service s3Service;

    public List<Section> parseContent(String content) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<List<Section>>() {});
    }

    public Post findPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Long findPreviousPost(Long id) {
        List<Post> posts = postRepository.findAll();
        for (int i = 1; i < posts.size(); i++) {
            if (posts.get(i).getId().equals(id)) {
                return posts.get(i - 1).getId();
            }
        }
        return null;
    }

    public Long findNextPost(Long id) {
        List<Post> posts = postRepository.findAll();
        for (int i = 0; i < posts.size() - 1; i++) {
            if (posts.get(i).getId().equals(id)) {
                return posts.get(i + 1).getId();
            }
        }
        return null;
    }

    public void savePost(String title,String authorName,List<Section> sections, MultipartFile image) throws IOException {
        // Handle image upload
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            String fileName = title.replaceAll("\\s+", "_") + "_image.png";
            imageUrl = s3Service.uploadImageToS3(image, fileName);
        }

        // Save the post
        Post post = new Post();
        post.setTitle(title);
        post.setAuthorName(authorName);
        post.setCreatedAt(LocalDate.now());
        post.setContent(new ObjectMapper().writeValueAsString(sections)); // Convert list of sections back to JSON string
        post.setImageUrl(imageUrl); // Save image URL if uploaded
        postRepository.save(post);
    }
}