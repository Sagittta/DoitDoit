package kr.hs.emirim.sagittta.DoitDoit;

public class Subjects {

    private String subject;
    private String category;
    private int startPage;
    private int endPage;

    public Subjects(String subject) {
        this.subject = subject;
    }

    public Subjects(String subject, String category) {
        this.subject = subject;
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}
