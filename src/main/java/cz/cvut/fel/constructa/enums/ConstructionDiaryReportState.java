package cz.cvut.fel.constructa.enums;

/**
 * The enum Construction diary report state.
 */
public enum ConstructionDiaryReportState {
    /**
     * Finished construction diary report state.
     */
    FINISHED("Dokončeno"),
    /**
     * In progress construction diary report state.
     */
    IN_PROGRESS("Rozpracováno"),
    /**
     * Blocked construction diary report state.
     */
    BLOCKED("Blokováno");

    /**
     * The Construction diary report state.
     */
    private final String constructionDiaryReportState;

    /**
     * Instantiates a new Construction diary report state.
     *
     * @param constructionDiaryReportState the construction diary report state
     */
    ConstructionDiaryReportState(String constructionDiaryReportState) {
        this.constructionDiaryReportState = constructionDiaryReportState;
    }

    /**
     * Gets construction diary report state.
     *
     * @return the construction diary report state
     */
    public String getConstructionDiaryReportState() {
        return constructionDiaryReportState;
    }
}
