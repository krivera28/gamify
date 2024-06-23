package RequestHandler;
import Task.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Controller
public class RequestHandler {
    @GetMapping("/api")
    public ResponseEntity<String> myApiHandler() {
        return new ResponseEntity<String>("Successfully called API", HttpStatus.OK);
    }
    @GetMapping("/index")
    public String Home(){
        logger.info("Home page request");
        return "index";
    }

    @GetMapping("/")
    public String HomeAlt(){
        return "index";
    }

    @GetMapping("/taskHome")
    public String TaskHome(){
        logger.info("Task Page request");
        return "tasks";
    }

    @GetMapping("/login")
    public String Login(){
        logger.info("Login Page request");
        return "login";
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
                .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
                .getHandlerMethods();
        map.forEach((key, value) -> logger.info("{} {}", key, value));
    }

    private ArrayList<Task> tasks = new ArrayList<Task>();
    TaskDatabase db = new TaskDatabase("./database.db");

    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);


    @PostMapping("/loginform")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
        // Logic to authenticate the user
        // For simplicity, assume credentials are valid
        // If authentication is successful

        boolean valid = db.isValidUser(credentials.getUsername(),credentials.getPassword());
        if (valid) {
            logger.info("Received successful login request for username: {}", credentials.getUsername());
            logger.info("Received successful login request for password: {}", credentials.getPassword());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("{\"message\": \"Login successful\"}");
        } else {
            logger.info("Received failed login request for username: {}", credentials.getUsername());
            logger.info("Received failed login request for password: {}", credentials.getPassword());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Login failed. Please create an account.\"}");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCredentials credentials) {
        logger.info("Received registration request for username: {}", credentials.getUsername());
        logger.info("Received registration request for password: {}", credentials.getPassword());

        // Logic to register the user
        // For simplicity, assume registration is successful
        db.addUser(credentials.username, credentials.password);
        // Create a JSON response with a message indicating success
        String jsonResponse = "{\"message\": \"Account created successfully\"}";

        // Return the JSON response with HTTP status ACCEPTED
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(jsonResponse);
    }


    @PostMapping("/addtasks")
    public ResponseEntity<String> addTaskFromHtml(@RequestBody TaskInput taskInput) {
        if (taskInput == null) {
            logger.info("Received faulty save request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        boolean valid = db.isValidUser(taskInput.username,taskInput.password);
        if (valid){
            Task task = new Task(taskInput.name, taskInput.description, taskInput.goalDate,Double.parseDouble(taskInput.progress),taskInput.category);
            tasks.add(task);
            boolean good = db.addTasksForUser(taskInput.username, taskInput.password,tasks);
            tasks.clear();
            logger.info("Received good save request:"+String.valueOf(good));
            return ResponseEntity.ok(null);
        } else {
            logger.info("Received failed save request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please sign in or register.");
        }
    }


    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasksForUser(@RequestParam String username, @RequestParam String password) {
        boolean valid = db.isValidUser(username, password);
        if (!valid) {
            logger.info("Invalid Authorization for tasks.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        }
        logger.info("Valid Authorization for tasks.");

        // Return a success response
        List<Task> output = db.getUserTasks(username);
        for (Task a:output){
            logger.info(a.getCompleteGoal());
        }
        return ResponseEntity.ok(output);
    }


    @DeleteMapping("/removetask/{taskId}")
    public ResponseEntity<String> removeTask(@PathVariable int taskId, @RequestBody UserCredentials credentials) {
        boolean valid = db.isValidUser(credentials.getUsername(), credentials.getPassword());
        if (!valid) {
            logger.info("Invalid Authorization for task removal.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        // Remove the task with the specified taskId from the database
        boolean removed = db.removeTask(credentials.getUsername(), taskId);
        if (removed) {
            logger.info("Task removed successfully.");
            return ResponseEntity.ok("Task removed successfully.");
        } else {
            logger.info("Failed to remove task.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
    }


    // Inner classes
    static class UserCredentials {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    static class TaskInput {
        @Setter
        @Getter
        private String username;
        @Setter
        @Getter
        private String password;
        @Setter
        @Getter
        private String name;
        @Setter
        @Getter
        private String description;
        @Setter
        @Getter
        private String goalDate;
        @Setter
        @Getter
        private String progress;
        @Setter
        @Getter
        private String category;
    }
}



