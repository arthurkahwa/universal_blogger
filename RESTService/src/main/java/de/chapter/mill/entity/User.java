package de.chapter.mill.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * User Entity
 * Represents the <strong>tblUser</strong> database table
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tbluser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
