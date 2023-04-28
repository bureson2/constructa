package cz.cvut.fel.constructa.enums;

/**
 * The enum Work report type.
 */
public enum WorkReportType {
    /**
     * Work report work report type.
     */
    WORK_REPORT("Pracovní záznam"),
    /**
     * Holiday work report type.
     */
    HOLIDAY("Dovolená"),
    /**
     * Sick note work report type.
     */
    SICK_NOTE("Nemocenská"),
    /**
     * Maternity leave work report type.
     */
    MATERNITY_LEAVE("Mateřská"),
    /**
     * Unpaid vacation work report type.
     */
    UNPAID_VACATION("Neplacená dovolená"),
    /**
     * The Sick day.
     */
    SICK_DAY("Sick day");

    /**
     * The Work report type.
     */
    private String workReportType;

    /**
     * Instantiates a new Work report type.
     *
     * @param workReportType the work report type
     */
    WorkReportType(String workReportType) {
        this.workReportType = workReportType;
    }

    /**
     * Gets work report type.
     *
     * @return the work report type
     */
    public String getWorkReportType() {
        return workReportType;
    }
}
