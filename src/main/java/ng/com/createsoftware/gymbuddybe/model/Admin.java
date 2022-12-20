package ng.com.createsoftware.gymbuddybe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String email;
    
    private String picture;

    public Admin(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
