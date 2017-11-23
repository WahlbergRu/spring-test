package deepvip.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_prediction")
public class UserPrediction {

    private long Id;

    private Set<UserPredictionHistory> userPredictionHistory;

    @Column
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Id", updatable = false, insertable = false)
    private ApplicationUser applicationUser;

    @Column
    @NotEmpty
    private String PublicPrediction;

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
    @NotEmpty
    private String SendResult;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return Id;
    }

    public UserPrediction setId(long id) {
        Id = id;
        return this;
    }

    @Column
    @OneToMany(mappedBy = "Id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<UserPredictionHistory> getUserPredictionHistory() {
        return userPredictionHistory;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public UserPrediction setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
        return this;
    }

    public UserPrediction setUserPredictionHistory(Set<UserPredictionHistory> userPredictionHistory) {
        this.userPredictionHistory = userPredictionHistory;
        return this;
    }

    public String getPublicPrediction() {
        return PublicPrediction;
    }

    public UserPrediction setPublicPrediction(String publicPrediction) {
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

    public String getSendResult() {
        return SendResult;
    }

    public UserPrediction setSendResult(String sendResult) {
        SendResult = sendResult;
        return this;
    }
}
