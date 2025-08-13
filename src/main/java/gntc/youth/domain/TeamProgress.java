package gntc.youth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team_progress")
@Getter
@NoArgsConstructor
public class TeamProgress {
    @Id
    private Integer teamNumber;

    private int currentIndex = 0;

    private boolean finished = false;

    @Version
    private int version;

    public TeamProgress(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    public void nextStep() {
        if (finished) {
            throw new IllegalStateException("Cannot advance, team has already finished.");
        }
        currentIndex++;
    }

    public void finish() {
        if (finished) {
            throw new IllegalStateException("Team has already finished.");
        }
        finished = true;
    }
}
