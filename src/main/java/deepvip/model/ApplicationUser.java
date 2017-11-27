package deepvip.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class ApplicationUser {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(unique = true)
    @Size(min=2, max=30)
    private String login;

    @Column
    @Size(min=2, max=30)
    private String name;

    @Column
    @Size(min=2, max=30)
    private String lastName;

    @Column
    @NotEmpty
    @Email
    private String email;

    @Column
    @Size(min=5)
    private String password;

    @Column
    @Size(min=5, max=255)
    private String affiliation;

    public long getId() {
        return Id;
    }

    public ApplicationUser setId(long id) {
        Id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public ApplicationUser setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getName() {
        return name;
    }

    public ApplicationUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ApplicationUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ApplicationUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ApplicationUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public ApplicationUser setAffiliation(String affiliation) {
        this.affiliation = affiliation;
        return this;
    }
}
