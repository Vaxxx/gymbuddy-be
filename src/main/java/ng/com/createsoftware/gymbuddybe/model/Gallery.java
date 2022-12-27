package ng.com.createsoftware.gymbuddybe.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="galleries")
@Entity
@Data
@NoArgsConstructor
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String image;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private User user;

    public Gallery(String image, User user) {
        this.image = image;
        this.user = user;
    }
}
