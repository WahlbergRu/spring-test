package deepvip.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "USER_PREDICTIONS")
public class UserPredictions {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty
    private String ViralProtein;

    @Column
    @NotEmpty
    private String HumanInteractionPartners;

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

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private ApplicationUser applicationUser;


}
