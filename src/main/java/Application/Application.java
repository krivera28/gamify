package Application;
import RequestHandler.RequestHandler;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackageClasses=RequestHandler.class)
public class Application{
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}