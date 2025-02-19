package com.archit.assignment2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class BlogPost {
    private String id;
    private String authorId; // Equals personId
    private String postContent;

    private BlogPost(String id, String authorId, String postContent) {
        if (id == null || authorId == null) {
            throw new IllegalArgumentException("Invalid input values");
        }
        this.id = id;
        this.authorId = authorId;
        this.postContent = postContent;
    }

    public static BlogPost fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, BlogPost.class);
    }
}
