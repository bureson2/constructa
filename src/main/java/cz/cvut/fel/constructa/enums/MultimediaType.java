package cz.cvut.fel.constructa.enums;

public enum MultimediaType {
    PHOTO("Photo"),
    DOCUMENT("Document"),
    VIDEO("Video");

    private String multimediaType;
    MultimediaType(String multimediaType) {
        this.multimediaType = multimediaType;
    }
    public String getMultimediaType() {
        return multimediaType;
    }

}
