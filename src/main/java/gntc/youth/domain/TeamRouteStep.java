package gntc.youth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "team_route_step",
        uniqueConstraints = {
                @UniqueConstraint(name="uk_team_step", columnNames = {"teamNumber","stepIndex"})
        }
)
@Getter
@NoArgsConstructor
public class TeamRouteStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer teamNumber;

    private int stepIndex;

    private String placeName;
}
