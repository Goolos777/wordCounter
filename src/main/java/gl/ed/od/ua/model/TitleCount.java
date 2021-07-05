package gl.ed.od.ua.model;

public class TitleCount {
    private final String title;
    private final long count;

    public TitleCount(String title, long count) {
        this.title = title;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "TitleCount{" +
                "title='" + title + '\'' +
                ", count=" + count +
                '}';
    }
}
