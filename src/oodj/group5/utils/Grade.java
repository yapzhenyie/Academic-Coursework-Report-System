package oodj.group5.utils;

public enum Grade {

    A_PLUS("A+", 80, 100, Classification.DISTINCTION),
    A("A", 75, 79, Classification.DISTINCTION),
    B_PLUS("B+", 70, 74, Classification.CREDIT),
    B("B", 65, 69, Classification.CREDIT),
    C_PLUS("C+", 60, 64, Classification.PASS),
    C("C", 55, 59, Classification.PASS),
    C_MINUS("C-", 50, 54, Classification.PASS),
    D("D", 40, 49, Classification.FAIL),
    F_PLUS("F+", 30, 39, Classification.FAIL),
    F("F", 20, 29, Classification.FAIL),
    F_MINUS("F-", 0, 19, Classification.FAIL);

    private String name;
    private int from;
    private int until;
    private Classification classification;

    private Grade(String name, int from, int until, Classification classification) {
        this.name = name;
        this.from = from;
        this.until = until;
        this.classification = classification;
    }

    public String getName() {
        return name;
    }

    public int getMarksFrom() {
        return from;
    }

    public int getMarksUntil() {
        return until;
    }

    public Classification getClassification() {
        return this.classification;
    }

    public static Grade getGrade(int marks) {
        for (Grade grade : Grade.values()) {
            if (marks >= grade.getMarksFrom() && marks <= grade.getMarksUntil()) {
                return grade;
            }
        }
        return null;
    }

    public enum Classification {

        DISTINCTION("Distinction"),
        CREDIT("Credit"),
        PASS("Pass"),
        FAIL("Fail");

        private String name;

        private Classification(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
