package deepvip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_prediction")
public class UserPrediction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    private ApplicationUser applicationUser;

    @Column
    private Boolean PublicPrediction;

    @Column(columnDefinition="VARCHAR")
    @NotEmpty
    private String ViralProtein;

    @Column(columnDefinition="VARCHAR")
    @NotEmpty
    private String HumanInteraction;

    @Column
    @NotEmpty
    private String Title;

    @Column
    @NotEmpty
    private String Description;

    @Column
    @NotEmpty
    private String Status;

    @Column
    private Boolean SendResult;

    public long getId() {
        return Id;
    }

    public UserPrediction setId(long id) {
        Id = id;
        return this;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public UserPrediction setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
        return this;
    }

    public Boolean getPublicPrediction() {
        return PublicPrediction;
    }

    public UserPrediction setPublicPrediction(Boolean publicPrediction) {
        PublicPrediction = publicPrediction;
        return this;
    }

    public String getViralProtein() {
        return ViralProtein;
    }

    public UserPrediction setViralProtein(String viralProtein) {
        ViralProtein = viralProtein;
        return this;
    }

    public String getHumanInteraction() {
        return HumanInteraction;
    }

    public UserPrediction setHumanInteraction(String humanInteraction) {
        HumanInteraction = humanInteraction;
        return this;
    }

    public String getTitle() {
        return Title;
    }

    public UserPrediction setTitle(String title) {
        Title = title;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public UserPrediction setDescription(String description) {
        Description = description;
        return this;
    }

    public String getStatus() {
        return Status;
    }

    public UserPrediction setStatus(String status) {
        Status = status;
        return this;
    }

    public Boolean getSendResult() {
        return SendResult;
    }

    public UserPrediction setSendResult(Boolean sendResult) {
        SendResult = sendResult;
        return this;
    }
}
