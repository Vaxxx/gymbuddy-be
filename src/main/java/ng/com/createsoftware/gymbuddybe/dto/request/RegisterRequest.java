package ng.com.createsoftware.gymbuddybe.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRequest {

    @NotBlank
    @Size(min = 2, max = 30)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 30)
    private String lastname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 9, max = 15)
    private String phone;


    private int age;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private List<String> role;

    @NotBlank
    private String password;

}
