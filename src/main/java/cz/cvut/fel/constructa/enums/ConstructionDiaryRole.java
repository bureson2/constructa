package cz.cvut.fel.constructa.enums;

/**
 * The enum Construction diary role.
 */
public enum ConstructionDiaryRole {
    /**
     * Construction part contractor construction diary role.
     */
    CONSTRUCTION_PART_CONTRACTOR("Construction_part_contractor"),
    /**
     * Construction manager construction diary role.
     */
    CONSTRUCTION_MANAGER("Construction_manager"),
    /**
     * Copyright supervisor construction diary role.
     */
    COPYRIGHT_SUPERVISOR("Copyright_supervisor"),
    /**
     * General contractor construction diary role.
     */
    GENERAL_CONTRACTOR("General_contractor"),
    /**
     * Technical permanent supervisor construction diary role.
     */
    TECHNICAL_PERMANENT_SUPERVISOR("Technical_permanent_supervisor"),
    /**
     * Technical temporary supervisior construction diary role.
     */
    TECHNICAL_TEMPORARY_SUPERVISIOR("Technical_temporary_supervisor"),
    /**
     * Technology part contractor construction diary role.
     */
    TECHNOLOGY_PART_CONTRACTOR("Technology_part_contractor");

    /**
     * The Construction diary role.
     */
    private final String constructionDiaryRole;

    /**
     * Instantiates a new Construction diary role.
     *
     * @param constructionDiaryRole the construction diary role
     */
    ConstructionDiaryRole(String constructionDiaryRole) {
        this.constructionDiaryRole = constructionDiaryRole;
    }

    /**
     * Gets construction diary role.
     *
     * @return the construction diary role
     */
    public String getConstructionDiaryRole() {
        return constructionDiaryRole;
    }
}
