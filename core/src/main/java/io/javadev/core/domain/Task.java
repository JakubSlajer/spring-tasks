package io.javadev.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @JoinColumn(unique = true)
    private String id;
    private String taskData;
    @ManyToOne
    @JoinColumn(name = "acquired_by")
    private User acquiredBy;

    public Task() {
    }

    public Task(String taskData, User acquiredBy) {
        this.taskData = taskData;
        this.acquiredBy = acquiredBy;
    }

    public Task(String id, String taskData, User acquiredBy) {
        this.id = id;
        this.taskData = taskData;
        this.acquiredBy = acquiredBy;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskData() {
        return taskData;
    }

    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }

    public User getAcquiredBy() {
        return acquiredBy;
    }

    public void setAcquiredBy(User acquiredBy) {
        this.acquiredBy = acquiredBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(getId(), task.getId()) && Objects.equals(getTaskData(), task.getTaskData()) && Objects.equals(getAcquiredBy(), task.getAcquiredBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTaskData(), getAcquiredBy());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", taskData='" + taskData + '\'' +
                '}';
    }
}
