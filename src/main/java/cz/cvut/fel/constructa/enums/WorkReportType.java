package cz.cvut.fel.constructa.enums;

public enum WorkReportType {
    WORK_REPORT("Pracovní záznam"),
    HOLIDAY("Dovolená"),
    SICK_NOTE("Nemocenská"),
    MATERNITY_LEAVE("Mateřská"),
    UNPAID_VACATION("Neplacená dovolená"),
    SICK_DAY("Sick day");

    private String workReportType;
    WorkReportType(String workReportType) {
        this.workReportType = workReportType;
    }
    public String getWorkReportType() {
        return workReportType;
    }
}
