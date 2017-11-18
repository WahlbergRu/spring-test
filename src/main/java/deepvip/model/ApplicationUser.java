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
    private long userId;

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

    @Column
    @OneToMany(mappedBy = "userPredictionId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserPrediction> userPredictions;

    public ApplicationUser(){
        userId=0;
    }

    public ApplicationUser(String login, String password){

    }

    public ApplicationUser(long id, String login, String name, String lastName, String email, String affiliation){
        this.userId = id;
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.affiliation = affiliation;
    }

    public long getId() {
        return userId;
    }

    public void setId(long id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAffiliation() {
        return this.affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
