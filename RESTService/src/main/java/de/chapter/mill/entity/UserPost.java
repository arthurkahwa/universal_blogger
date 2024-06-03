package de.chapter.mill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Derived class used to steer output
 * using data from a user and combining that with the corresponding posts
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPost {
    private Long id;
    private String username;
    private String email;
    private List<Post> posts;
}
