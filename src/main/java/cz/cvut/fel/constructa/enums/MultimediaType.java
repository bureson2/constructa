package cz.cvut.fel.constructa.enums;

/**
 * The enum Multimedia type.
 */
public enum MultimediaType {
    /**
     * Photo multimedia type.
     */
    IMAGE("Obrázek"),
    /**
     * Document multimedia type.
     */
    DOCUMENT("Dokument"),
    /**
     * Video multimedia type.
     */
    VIDEO("Video");

    /**
     * The Multimedia type.
     */
    private final String multimediaType;

    /**
     * Instantiates a new Multimedia type.
     *
     * @param multimediaType the multimedia type
     */
    MultimediaType(String multimediaType) {
        this.multimediaType = multimediaType;
    }

    /**
     * Gets multimedia type.
     *
     * @return the multimedia type
     */
    public String getMultimediaType() {
        return multimediaType;
    }

}
