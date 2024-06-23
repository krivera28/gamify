//package ScheduleGroup;
//import java.time.*;
//import java.util.Vector;
//import Task.Task;
//public class ScheduleGroup{
//    private Vector<Task>schedule;
//    public String Schedulename;
//    /*Task list is much more sensitive, we don't want any
//     * accidental deletion/modification of the list to ruin
//     * the user-experience, will primarily modify through
//     * helper methods and database calls.*/
//
//    public String Group = "My Schedule";
//    public LocalDate creationdate = LocalDate.now();
//
//    /*schedulename and creation date are not sensitive data,
//     * keep public
//     */
//    /*blank constructor, may modify to take in vector/array of tasks*/
//    public ScheduleGroup() {}
//
//    public String setName(String name) {
//        Schedulename = name;
//        return "Schedule is now named: "+name;
//        /*Methods to add a task for various passed arguments*/
//    }
//    public String getName(){
//        return Schedulename;
//    }
//    public Vector<Task> getSchedule(){
//        return schedule;
//    }
//
//    public void addTask(Task input) {
//        schedule.addElement(input);
//    }
//
//    /*Methods to remove tasks with various inputs*/
//    public String RemoveTask(Task toRemove){
//            if(schedule.contains(toRemove)) {
//                schedule.remove(toRemove);
//                return "Task removed.";
//            }
//        return "Error: That task does not exist, is this a duplicate request?";
//    }
//    public void setTaskName(String name, int index){
//        schedule.get(index).setName(name);
//    }
//    public void setTaskDes(String des, int index){
//        schedule.get(index).setDes(des);
//    }
//    public String RemoveTask(String name) {
//            for (Task temp : schedule) {
//                if (temp.getName().equals(name)) {
//                    schedule.remove(temp);
//                    return "Task removed.";
//                }
//            }
//        return "Error: No task with that name.";
//    }
//
//
//    /*method to clear schedule*/
//    public String ClearSchedule() {
//        if(!schedule.isEmpty()) {
//            schedule.clear();
//            return "Schedule cleared.";
//        } else {return "Schedule already empty.";}
//    }
//

//}
