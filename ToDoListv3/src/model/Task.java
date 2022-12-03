package model;

import java.time.LocalDate;

public class Task {
    private LocalDate datecreated;
    private String description;
    private String task;


    public Task() {
    }

    public Task(LocalDate datecreated, String description, String task) {
        this.datecreated = datecreated;
        this.description = description;
        this.task = task;
    }

    /**
     * @return the datecreated
     */
    public LocalDate getDatecreated() {
        return datecreated;
    }

    /**
     * @param datecreated the datecreated to set
     */
    public void setDatecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }
    
}
