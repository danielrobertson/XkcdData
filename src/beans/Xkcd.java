package beans;

/**
 * Represents an Xkcd comic
 */
public class Xkcd {

    // the comic number
    private int number;

    // title
    private String title;

    // transcript text
    private String transcript;

    // url to
    private String imageUrl;

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    // text on the comic
    private String altText;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Xkcd {" +
                "number=" + number +
                ", title='" + title + '\'' +
                ", transcript='" + transcript + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", altText='" + altText + '\'' +
                '}';
    }
}
