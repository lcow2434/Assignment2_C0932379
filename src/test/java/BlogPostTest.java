import com.archit.assignment2.BlogPost;  // Adjust the package name if needed

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlogPostTest {

    @Test
    void testValidBlogPost() {
        BlogPost blogPost = BlogPost.builder()
                .id("101")
                .authorId("1")
                .postContent("This is a blog post.")
                .build();

        assertNotNull(blogPost);
    }

    @Test
    void testInvalidBlogPost() {
        assertThrows(IllegalArgumentException.class, () -> BlogPost.builder()
                .id(null)
                .authorId("1")
                .postContent("This is a blog post.")
                .build());
    }
}
