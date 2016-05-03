package beans;

/**
 * Represents an Xkcd comic
 */
public class Xkcd {

    // the comic num
    private int num;

    // title
    private String title;

    // transcript text
    private String transcript;

    // image url
    private String img;

    // text on the comic
    private String alt;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        String line = "\n";
        StringBuffer sb = new StringBuffer();
        sb.append("Xkcd {").append(line);
        sb.append("num = ").append(num).append(line);
        sb.append("title = ").append(title).append(line);
        sb.append("transcript = ").append(transcript).append(line);
        sb.append("img = ").append(img).append(line);
        sb.append("alt = ").append(alt).append(line);
        sb.append("}").append(line);

        return sb.toString();
    }
}
