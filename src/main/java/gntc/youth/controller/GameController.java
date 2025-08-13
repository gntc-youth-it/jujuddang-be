package gntc.youth.controller;

import gntc.youth.model.ScanRequest;
import gntc.youth.model.ScanResponse;
import gntc.youth.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkpoint")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/scan")
    public ResponseEntity<ScanResponse> scan(@RequestBody ScanRequest request) {
        return ResponseEntity.ok(gameService.scan(request));
    }
}
