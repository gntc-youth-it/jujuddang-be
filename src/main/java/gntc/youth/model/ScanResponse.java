package gntc.youth.model;

public record ScanResponse(
        boolean correct,
        String expectedStage,
        String expectedSite,
        String nextStage,
        String nextSite,
        int progress,
        boolean finished
) {
}
