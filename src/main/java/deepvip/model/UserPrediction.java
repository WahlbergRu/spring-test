package deepvip.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER_PREDICTION")
public class UserPrediction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userPredictionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private ApplicationUser applicationUser;

    @Column
    @OneToMany(mappedBy = "userPrediction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserPrediction> userPrediction;

    @Column
    @NotEmpty
    private String PublicPrediction;

    @Column(columnDefinition="TEXT")
    @NotEmpty
    private String ViralProtein;

    @Column(columnDefinition="TEXT")
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

    public long getUserPredictionId() {
        return userPredictionId;
    }

    public void setUserPredictionId(long userPredictionId) {
        this.userPredictionId = userPredictionId;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Set<UserPrediction> getUserPrediction() {
        return userPrediction;
    }

    public void setUserPrediction(Set<UserPrediction> userPrediction) {
        this.userPrediction = userPrediction;
    }

    public String getPublicPrediction() {
        return PublicPrediction;
    }

    public void setPublicPrediction(String publicPrediction) {
        PublicPrediction = publicPrediction;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getViralProtein() {
        return ViralProtein;
    }

    public void setViralProtein(String viralProtein) {
        ViralProtein = viralProtein;
    }

    public String getHumanInteraction() {
        return HumanInteraction;
    }

    public void setHumanInteraction(String humanInteraction) {
        HumanInteraction = humanInteraction;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSendResult() {
        return SendResult;
    }

    public void setSendResult(String sendResult) {
        SendResult = sendResult;
    }
}
