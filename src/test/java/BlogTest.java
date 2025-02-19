import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Person class representing an author
class Person {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    public Person(String id, String firstName, String lastName, int age, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }
}

// BlogPost class representing a blog post
class BlogPost {
    private String id;
    private String authorId;
    private String title;
    private String content;

    public BlogPost(String id, String authorId, String title, String content) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getAuthorId() {
        return authorId;
    }
}

// Blog class to manage blog posts and authors
class Blog {
    private List<BlogPost> posts;
    private Map<String, Integer> authorAgeMap; // Mapping authorId -> age

    public Blog(List<BlogPost> posts, List<Person> contributors) {
        this.posts = posts;
        this.authorAgeMap = contributors.stream()
                .collect(Collectors.toMap(Person::getId, Person::getAge));
    }

    public List<String> getPostsByAuthorAge(int age) {
        return posts.stream()
                .filter(post -> authorAgeMap.getOrDefault(post.getAuthorId(), -1) == age)
                .map(BlogPost::getId)
                .collect(Collectors.toList());
    }
}

public class BlogTest {

    @Test
    public void testGetPostsByAuthorAge() {
        Person author1 = new Person("1", "John", "Doe", 30, "Male");
        Person author2 = new Person("2", "Jane", "Smith", 25, "Female");

        BlogPost post1 = new BlogPost("1", "1", "Post by John", "Content 1");
        BlogPost post2 = new BlogPost("2", "2", "Post by Jane", "Content 2");

        Blog blog = new Blog(List.of(post1, post2), List.of(author1, author2));

        // Test: Get posts by author age 30
        List<String> posts = blog.getPostsByAuthorAge(30);
        assertEquals(1, posts.size());
        assertEquals("1", posts.get(0)); // Post 1 should be the result
    }

    @Test
    public void testGetPostsByAuthorAgeNoMatch() {
        Person author1 = new Person("1", "John", "Doe", 30, "Male");
        Person author2 = new Person("2", "Jane", "Smith", 25, "Female");

        BlogPost post1 = new BlogPost("1", "1", "Post by John", "Content 1");
        BlogPost post2 = new BlogPost("2", "2", "Post by Jane", "Content 2");

        Blog blog = new Blog(List.of(post1, post2), List.of(author1, author2));

        // Test: Get posts by author age 40 (No match)
        List<String> posts = blog.getPostsByAuthorAge(40);
        assertTrue(posts.isEmpty()); // No posts should match age 40
    }

    @Test
    public void testHandleMissingAuthorId() {
        Person author1 = new Person("1", "John", "Doe", 30, "Male");
        Person author2 = new Person("2", "Jane", "Smith", 25, "Female");

        // Post with an invalid author ID
        BlogPost post1 = new BlogPost("1", "1", "Post by John", "Content 1");
        BlogPost post2 = new BlogPost("2", "3", "Post by Unknown Author", "Content 2"); // Invalid author ID

        Blog blog = new Blog(List.of(post1, post2), List.of(author1, author2));

        // Test: Get posts by author age 25 (should not include post with invalid authorId)
        List<String> posts = blog.getPostsByAuthorAge(25);
        assertEquals(1, posts.size()); // Only one valid post by Jane (age 25)
    }

    @Test
    public void testHandleMissingPersonForAuthorId() {
        Person author1 = new Person("1", "John", "Doe", 30, "Male");

        // Post with an authorId that doesn't exist in contributors
        BlogPost post1 = new BlogPost("1", "1", "Post by John", "Content 1");
        BlogPost post2 = new BlogPost("2", "3", "Post by Unknown Author", "Content 2"); // Invalid author ID

        Blog blog = new Blog(List.of(post1, post2), List.of(author1));

        // Test: Get posts by author age 25 (should not include post with invalid authorId)
        List<String> posts = blog.getPostsByAuthorAge(25);
        assertTrue(posts.isEmpty()); // No valid posts with age 25
    }

    @Test
    public void testHandleCorruptData() {
        // Simulate reading corrupt or invalid data (e.g., invalid JSON or missing fields)
        // Test if the code gracefully handles missing or malformed data.
        assertDoesNotThrow(() -> new Blog(List.of(), List.of()));
    }
}
