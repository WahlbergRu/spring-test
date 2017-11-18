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
    private String Public;

    @Column
    @NotEmpty
    private String Status;

    @Column
    @NotEmpty
    private String ViralProtein;

    @Column
    @NotEmpty
    private String HumanInteractionPartners;

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

    public String getPublic() {
        return Public;
    }

    public void setPublic(String aPublic) {
        Public = aPublic;
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

    public String getHumanInteractionPartners() {
        return HumanInteractionPartners;
    }

    public void setHumanInteractionPartners(String humanInteractionPartners) {
        HumanInteractionPartners = humanInteractionPartners;
    }
}
