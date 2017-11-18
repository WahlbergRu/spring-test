package deepvip.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@Entity
@Table(name = "USER_PREDICTION_HISTORY")
public class UserPredictionHistory {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userPredictionHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_predictions_id", nullable=false)
    private UserPrediction userPrediction;

    @Column
    @NotEmpty
    private Number PredictionSpecificity;

    @Column
    @NotEmpty
    private Number PredictionSensitivity;

    @Column
    @NotEmpty
    private Number PredictionConfidence;

    @Column
    @NotEmpty
    private Number FilterHumanSpecificity;

    @Column
    @NotEmpty
    private Number FilterHumanSensitivity;

    public long getUserPredictionHistoryId() {
        return userPredictionHistoryId;
    }

    public void setUserPredictionHistoryId(long userPredictionHistoryId) {
        this.userPredictionHistoryId = userPredictionHistoryId;
    }

    public UserPrediction getUserPrediction() {
        return userPrediction;
    }

    public void setUserPrediction(UserPrediction userPrediction) {
        this.userPrediction = userPrediction;
    }

    public Number getPredictionSpecificity() {
        return PredictionSpecificity;
    }

    public void setPredictionSpecificity(Number predictionSpecificity) {
        PredictionSpecificity = predictionSpecificity;
    }

    public Number getPredictionSensitivity() {
        return PredictionSensitivity;
    }

    public void setPredictionSensitivity(Number predictionSensitivity) {
        PredictionSensitivity = predictionSensitivity;
    }

    public Number getPredictionConfidence() {
        return PredictionConfidence;
    }

    public void setPredictionConfidence(Number predictionConfidence) {
        PredictionConfidence = predictionConfidence;
    }

    public Number getFilterHumanSpecificity() {
        return FilterHumanSpecificity;
    }

    public void setFilterHumanSpecificity(Number filterHumanSpecificity) {
        FilterHumanSpecificity = filterHumanSpecificity;
    }

    public Number getFilterHumanSensitivity() {
        return FilterHumanSensitivity;
    }

    public void setFilterHumanSensitivity(Number filterHumanSensitivity) {
        FilterHumanSensitivity = filterHumanSensitivity;
    }
}
