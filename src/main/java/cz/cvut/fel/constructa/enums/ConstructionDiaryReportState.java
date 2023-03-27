package cz.cvut.fel.constructa.enums;

public enum ConstructionDiaryReportState {
    FINISHED("Dokončeno"),
    IN_PROGRESS("Rozpracováno"),
    BLOCKED("Blokováno");

    private String constructionDiaryReportState;
    ConstructionDiaryReportState(String constructionDiaryReportState) {
        this.constructionDiaryReportState = constructionDiaryReportState;
    }
    public String getConstructionDiaryReportState() {
        return constructionDiaryReportState;
    }
}
