package com.archit.assignment2;

import java.util.List;
import java.util.stream.Collectors;

public class Blog {

    private List<BlogPost> posts;
    private List<Person> contributors;

    // Constructor
    public Blog(List<BlogPost> posts, List<Person> contributors) {
        this.posts = posts;
        this.contributors = contributors;
    }

    // Getters for posts and contributors
    public List<BlogPost> getPosts() {
        return posts;
    }

    public List<Person> getContributors() {
        return contributors;
    }

    // Method to get posts by author's age
    public List<String> getPostsByAuthorAge(int age) {
        return posts.stream()
                .filter(post -> {
                    // Find the person who authored the post by matching authorId
                    return contributors.stream()
                            .filter(person -> person.getId().equals(post.getAuthorId()))
                            .anyMatch(person -> person.getAge() == age);
                })
                .map(BlogPost::getId)  // Return the post IDs
                .collect(Collectors.toList());
    }
}
