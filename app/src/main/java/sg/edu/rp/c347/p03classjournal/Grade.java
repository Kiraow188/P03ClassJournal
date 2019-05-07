package sg.edu.rp.c347.p03classjournal;

public class Grade {

    private String week;
    private String grade;

    public Grade(String week, String grade) {
        this.week = week;
        this.grade = grade;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
