package deepvip;

import deepvip.model.UserPrediction;
import deepvip.model.UserPredictionHistory;

public class UserPredictionPostModel {
    UserPrediction userPrediction;
    UserPredictionHistory userPredictionHistory;

    public UserPrediction getUserPrediction() {
        return userPrediction;
    }

    public UserPredictionPostModel setUserPrediction(UserPrediction userPrediction) {
        this.userPrediction = userPrediction;
        return this;
    }

    public UserPredictionHistory getUserPredictionHistory() {
        return userPredictionHistory;
    }

    public UserPredictionPostModel setUserPredictionHistory(UserPredictionHistory userPredictionHistory) {
        this.userPredictionHistory = userPredictionHistory;
        return this;
    }

}
