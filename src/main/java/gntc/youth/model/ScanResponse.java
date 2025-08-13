package gntc.youth.model;

public record ScanResponse(
        boolean correct,
        String expectedPlace,
        String nextPlace,
        int progress,
        boolean finished
) {
}
