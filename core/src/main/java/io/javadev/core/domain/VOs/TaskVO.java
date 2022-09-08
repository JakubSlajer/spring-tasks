package io.javadev.core.domain.VOs;

import java.io.Serializable;
import java.util.Objects;

public class TaskVO implements Serializable {

    private String id;
    private String taskData;
    private UserVO acquiredBy;

    public TaskVO() {
    }

    public TaskVO(String taskData, UserVO acquiredBy) {
        this.taskData = taskData;
        this.acquiredBy = acquiredBy;
    }

    public TaskVO(String id, String taskData, UserVO acquiredBy) {
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

    public UserVO getAcquiredBy() {
        return acquiredBy;
    }

    public void setAcquiredBy(UserVO acquiredBy) {
        this.acquiredBy = acquiredBy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskVO taskVO)) return false;
        return Objects.equals(getId(), taskVO.getId()) && Objects.equals(getTaskData(), taskVO.getTaskData()) && Objects.equals(getAcquiredBy(), taskVO.getAcquiredBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTaskData(), getAcquiredBy());
    }

    @Override
    public String toString() {
        return "TaskVO{" +
                "id='" + id + '\'' +
                ", taskData='" + taskData + '\'' +
                ", acquiredBy=" + acquiredBy +
                '}';
    }
}
