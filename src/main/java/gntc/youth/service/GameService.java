package gntc.youth.service;

import gntc.youth.domain.Stage;
import gntc.youth.domain.TeamProgress;
import gntc.youth.domain.TeamRouteStep;
import gntc.youth.model.ScanRequest;
import gntc.youth.model.ScanResponse;
import gntc.youth.repository.TeamProgressRepository;
import gntc.youth.repository.TeamRouteStepRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final TeamProgressRepository progressRepository;
    private final TeamRouteStepRepository routeStepRepository;

    @Transactional
    public ScanResponse scan(ScanRequest request) {
        int team = validateTeam(request.teamNumber());

        List<TeamRouteStep> route = routeStepRepository.findByTeamNumberOrderByStepIndexAsc(team);

        if (route.size() != 4) {
            throw new IllegalStateException("Team route must contain exactly 4 steps.");
        }

        TeamProgress progress = progressRepository.findById(team)
                .orElseGet(() -> progressRepository.save(new TeamProgress(team)));

        if (progress.isFinished()) {
            return new ScanResponse(true, null, null, null, null, 4, true);
        }

        TeamRouteStep expected = route.get(progress.getCurrentIndex());
        boolean correct = (expected.getStage() == request.stage());

        if (!correct) {
            return new ScanResponse(false, expected.getStage().name(), null, null, null, progress.getCurrentIndex(), false);
        }

        progress.nextStep();

        if (progress.getCurrentIndex() >= route.size()) {
            progress.finish();
            return new ScanResponse(true, null, null, null, null, 4, true);
        }

        TeamRouteStep nextStep = route.get(progress.getCurrentIndex());
        return new ScanResponse(true, null, null, nextStep.getStage().name(), nextStep.getSite().name(), progress.getCurrentIndex(), false);
    }

    private int validateTeam(Integer team) {
        if (team == null || team < 1 || team > 59) {
            throw new IllegalArgumentException("teamNumber must be between 1 and 59.");
        }
        return team;
    }
}
