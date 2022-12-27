package ng.com.createsoftware.gymbuddybe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="locations")
@Entity
@Data
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    private Double latitude;

    private Double longitude;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private User user;

    public Location(Long id, String city, String country, Double latitude, Double longitude, User user) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }

    public Location(String city, String country, Double latitude, Double longitude, User user) {
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }

    public Location(String city, String country, Double latitude, Double longitude) {
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", user=" + user +
                '}';
    }
}
