package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class Employee {

    private String id;
    private String name;
    private int age;
    private String etc;

    public static Employee ofTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
        System.out.println("test");
        return new Employee("1", "test", 19, null);
    }

}
