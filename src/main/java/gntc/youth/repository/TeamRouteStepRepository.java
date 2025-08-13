package gntc.youth.repository;

import gntc.youth.domain.TeamRouteStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRouteStepRepository extends JpaRepository<TeamRouteStep, Long> {

    List<TeamRouteStep> findByTeamNumberOrderByStepIndexAsc(Integer teamNumber);
}
