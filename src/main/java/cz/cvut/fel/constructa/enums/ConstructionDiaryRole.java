package cz.cvut.fel.constructa.enums;

public enum ConstructionDiaryRole {
    CONSTRUCTION_PART_CONTRACTOR("Construction_part_contractor"),
    CONSTRUCTION_MANAGER("Construction_manager"),
    COPYRIGHT_SUPERVISOR("Copyright_supervisor"),
    GENERAL_CONTRACTOR("General_contractor"),
    TECHNICAL_PERMANENT_SUPERVISOR("Technical_permanent_supervisor"),
    TECHNICAL_TEMPORARY_SUPERVISIOR("Technical_temporary_supervisor"),
    TECHNOLOGY_PART_CONTRACTOR("Technology_part_contractor");

    private String constructionDiaryRole;
    ConstructionDiaryRole(String constructionDiaryRole) {
        this.constructionDiaryRole = constructionDiaryRole;
    }
    public String getConstructionDiaryRole() {
        return constructionDiaryRole;
    }
}
