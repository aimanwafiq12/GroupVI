
public class Reputation {
    int point;
    Student relativeTo;
    Reputation nextRep;

    public Reputation(int point, Student relativeTo, Reputation nextRep) {
        this.point = point;
        this.relativeTo = relativeTo;
        this.nextRep = nextRep; 
    }
}
