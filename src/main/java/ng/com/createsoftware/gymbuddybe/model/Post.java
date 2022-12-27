package ng.com.createsoftware.gymbuddybe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name="posts")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private User user;

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Post(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(String title) {
        this.title = title;
    }
}
