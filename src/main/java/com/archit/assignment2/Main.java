package com.archit.assignment2;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        // Initialize ObjectMapper for JSON deserialization
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON files into lists of Person and BlogPost
            List<Person> people = objectMapper.readValue(new File("person.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));
            List<BlogPost> blogPosts = objectMapper.readValue(new File("blogPosts.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, BlogPost.class));

            // Create a Blog instance with the data
            Blog blog = new Blog(blogPosts, people);

            // Call getPostsByAuthorAge and print the results for authors aged 25
            List<String> postsByAge = blog.getPostsByAuthorAge(25);
            System.out.println("Blog posts by authors with age 25: " + postsByAge);

            // Print the total number of blog posts and contributors
            System.out.println("Total blog posts: " + blog.getPosts().size());
            System.out.println("Total contributors: " + blog.getContributors().size());

        } catch (IOException e) {
            // Handle cases where files are missing or corrupt
            System.err.println("Error reading JSON files: " + e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
