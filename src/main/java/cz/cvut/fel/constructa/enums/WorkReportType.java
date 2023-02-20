package cz.cvut.fel.constructa.enums;

public enum WorkReportType {
    WORK_REPORT("Work_report"),
    HOLIDAY("Holiday"),
    SICK_NOTE("Sick_note"),
    MATERNITY_LEAVE("Maternity_leave"),
    UNPAID_VACATION("Unpaid_vacation"),
    SICK_DAY("Sick_daz");

    private String workReportType;
    WorkReportType(String workReportType) {
        this.workReportType = workReportType;
    }
    public String getWorkReportType() {
        return workReportType;
    }
}
