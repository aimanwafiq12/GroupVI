
public class Main {

    public static void main(String[] args) {
        //Initialize
        Student[] students = new Student[10];
        for (int i = 0; i < 10; i++) {
            students[i] = new Student(i+1);
        }
        
        //Students 1 and 7
        students[0].addRep(3, students[6]);
        students[6].addRep(4, students[0]);
        
        //Students 1 and 2
        students[0].addRep(8, students[1]);
        students[1].addRep(5, students[0]);
        
        //Students 2 and 5
        students[1].addRep(2, students[4]);
        students[4].addRep(6, students[1]);
        
        //Students 2 and 6
        students[1].addRep(7, students[5]);
        students[5].addRep(9, students[1]);
        
        //Students 2 and 3
        students[1].addRep(4, students[2]);
        students[2].addRep(5, students[1]);
        
        //Students 4 and 8
        students[3].addRep(10, students[7]);
        students[7].addRep(7, students[3]);
        
        //Students 4 and 10
        students[3].addRep(7, students[9]);
        students[9].addRep(7, students[3]);
        
        //Students 9 and 10
        students[9].addRep(5, students[8]);
        students[8].addRep(6, students[9]);
        
    }
}
