package deepvip.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER_PREDICTION_TABLE")
public class UserPredictionTable {

    private long Id;

    private UserPrediction userPrediction;

    @Column
    @NotEmpty
    private Number GeneName;

    @Column
    @NotEmpty
    private String UniProtID;

    @Column
    @NotEmpty
    private Number Confidence;

    @Column
    @NotEmpty
    private Number Sensitivity;

    @Column
    @NotEmpty
    private Number Specificity;

    @Id
    @Column
    @GeneratedValue
    public long getId() {
        return Id;
    }

    public UserPredictionTable setId(long id) {
        Id = id;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_prediction_id")
    public UserPrediction getUserPrediction() {
        return userPrediction;
    }

    public void setUserPrediction(UserPrediction userPrediction) {
        this.userPrediction = userPrediction;
    }

    public Number getGeneName() {
        return GeneName;
    }

    public void setGeneName(Number geneName) {
        GeneName = geneName;
    }

    public String getUniProtID() {
        return UniProtID;
    }

    public void setUniProtID(String uniProtID) {
        UniProtID = uniProtID;
    }

    public Number getConfidence() {
        return Confidence;
    }

    public void setConfidence(Number confidence) {
        Confidence = confidence;
    }

    public Number getSensitivity() {
        return Sensitivity;
    }

    public void setSensitivity(Number sensitivity) {
        Sensitivity = sensitivity;
    }

    public Number getSpecificity() {
        return Specificity;
    }

    public void setSpecificity(Number specificity) {
        Specificity = specificity;
    }
}
