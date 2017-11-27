package deepvip.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "USER_PREDICTION_TABLE")
public class UserPredictionTable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userPredictionTableId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_prediction_id")
    private UserPrediction userPrediction;

    @Column
    @NotEmpty
    private ArrayList<String> GeneName;

    @Column
    @NotEmpty
    private ArrayList<String> UniProtID;

    @Column
    @NotEmpty
    private ArrayList<Number> Confidence;

    @Column
    @NotEmpty
    private ArrayList<Number> Sensitivity;

    @Column
    @NotEmpty
    private ArrayList<Number> Specificity;

    public long getUserPredictionTableId() {
        return userPredictionTableId;
    }

    public UserPredictionTable setUserPredictionTableId(long userPredictionTableId) {
        this.userPredictionTableId = userPredictionTableId;
        return this;
    }

    public UserPrediction getUserPrediction() {
        return userPrediction;
    }

    public UserPredictionTable setUserPrediction(UserPrediction userPrediction) {
        this.userPrediction = userPrediction;
        return this;
    }

    public ArrayList<String> getGeneName() {
        return GeneName;
    }

    public UserPredictionTable setGeneName(ArrayList<String> geneName) {
        GeneName = geneName;
        return this;
    }

    public ArrayList<String> getUniProtID() {
        return UniProtID;
    }

    public UserPredictionTable setUniProtID(ArrayList<String> uniProtID) {
        UniProtID = uniProtID;
        return this;
    }

    public ArrayList<Number> getConfidence() {
        return Confidence;
    }

    public UserPredictionTable setConfidence(ArrayList<Number> confidence) {
        Confidence = confidence;
        return this;
    }

    public ArrayList<Number> getSensitivity() {
        return Sensitivity;
    }

    public UserPredictionTable setSensitivity(ArrayList<Number> sensitivity) {
        Sensitivity = sensitivity;
        return this;
    }

    public ArrayList<Number> getSpecificity() {
        return Specificity;
    }

    public UserPredictionTable setSpecificity(ArrayList<Number> specificity) {
        Specificity = specificity;
        return this;
    }
}
