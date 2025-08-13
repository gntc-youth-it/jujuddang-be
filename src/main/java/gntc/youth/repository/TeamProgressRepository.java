package gntc.youth.repository;

import gntc.youth.domain.TeamProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamProgressRepository extends JpaRepository<TeamProgress, Integer> {
}
