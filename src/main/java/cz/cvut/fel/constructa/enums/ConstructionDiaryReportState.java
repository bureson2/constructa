package cz.cvut.fel.constructa.enums;

public enum ConstructionDiaryReportState {
    FINISHED("Functional"),
    IN_PROGRESS("Unfunctional"),
    BLOCKED("Under_repair");

    private String constructionDiaryReportState;
    ConstructionDiaryReportState(String constructionDiaryReportState) {
        this.constructionDiaryReportState = constructionDiaryReportState;
    }
    public String getConstructionDiaryReportState() {
        return constructionDiaryReportState;
    }
}
