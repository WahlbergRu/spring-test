package deepvip.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    private String affilation;

    public User(){
        id=0;
    }

    public User(long id, String login, String name, String lastName, String email, String affilation){
        this.id = id;
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.affilation = affilation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAffilation() {
        return this.affilation;
    }

    public void setAffilation(String affilation) {
        this.affilation = affilation;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }


}
