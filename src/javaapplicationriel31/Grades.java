package javaapplicationriel31;

public class Grades {
    private int id;
    private String name;
    private double p, m, pf, f;
    double ave;

    public void addGrades(int sid, String sname, double sp, double sm, double spf, double sf) {
        this.id = sid;
        this.name = sname;
        this.p = sp;
        this.m = sm;
        this.pf = spf;
        this.f = sf;
    }

    public void viewGrades() {
        this.ave = (this.p + this.m + this.pf + this.f) / 4; 
        String remarks = (ave > 3.0) ? "Failed" : "Passed"; 

        System.out.printf("%-10d %-10s %-10.2f %-10.2f %-10.2f %-10.2f %-10.2f %-10s%n",
                this.id, this.name, this.p, this.m, this.pf, this.f, ave, remarks);
    }

    public static void main(String[] args) {
        Grades gr = new Grades();
        gr.addGrades(1011, "Riel", 1.3, 2.2, 2.0, 1.8);
        gr.viewGrades(); 
    }
}

