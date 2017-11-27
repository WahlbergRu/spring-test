package deepvip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@Entity
@Table(name = "user_prediction_history")
public class UserPredictionHistory {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_prediction_id")
    private UserPrediction userPrediction;

    @Column
    private Number PredictionSpecificity;

    @Column
    private Number PredictionSensitivity;

    @Column
    private Number PredictionConfidence;

    @Column
    private Number FilterHumanSpecificity;

    @Column
    private Number FilterHumanSensitivity;

    public long getId() {
        return Id;
    }

    public UserPredictionHistory setId(long id) {
        Id = id;
        return this;
    }

    public UserPrediction getUserPrediction() {
        return userPrediction;
    }

    public UserPredictionHistory setUserPrediction(UserPrediction userPrediction) {
        this.userPrediction = userPrediction;
        return this;
    }

    public Number getPredictionSpecificity() {
        return PredictionSpecificity;
    }

    public UserPredictionHistory setPredictionSpecificity(Number predictionSpecificity) {
        PredictionSpecificity = predictionSpecificity;
        return this;
    }

    public Number getPredictionSensitivity() {
        return PredictionSensitivity;
    }

    public UserPredictionHistory setPredictionSensitivity(Number predictionSensitivity) {
        PredictionSensitivity = predictionSensitivity;
        return this;
    }

    public Number getPredictionConfidence() {
        return PredictionConfidence;
    }

    public UserPredictionHistory setPredictionConfidence(Number predictionConfidence) {
        PredictionConfidence = predictionConfidence;
        return this;
    }

    public Number getFilterHumanSpecificity() {
        return FilterHumanSpecificity;
    }

    public UserPredictionHistory setFilterHumanSpecificity(Number filterHumanSpecificity) {
        FilterHumanSpecificity = filterHumanSpecificity;
        return this;
    }

    public Number getFilterHumanSensitivity() {
        return FilterHumanSensitivity;
    }

    public UserPredictionHistory setFilterHumanSensitivity(Number filterHumanSensitivity) {
        FilterHumanSensitivity = filterHumanSensitivity;
        return this;
    }
}
