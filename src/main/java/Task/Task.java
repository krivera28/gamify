package Task;

import lombok.Getter;
import lombok.Setter;


public class Task {
    @Setter
    @Getter
    public int ID = 0;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String category;
    @Setter
    @Getter
    private String description;
    /*progress will be a double between 0 and 1*/
    @Setter
    @Getter
    private double progress;
    @Setter
    @Getter
    private String completeGoal;
    /*String to Date Formatter*/


    /*task constructors*/

    public Task(String n, String des, String goal,
                double prog, String cat) {
        category = cat;
        name = n;
        description = des;
        completeGoal = goal;
        progress = prog;
    }

    public Task(int id, String n, String des, String goal,
                double prog, String cat) {
        ID = id;
        category = cat;
        name = n;
        description = des;
        completeGoal = goal;
        progress = prog;
    }
}