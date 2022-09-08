package io.javadev.core.domain.criteria;


public class TaskSearchCriteria {
    public final String taskId;
    public final String taskData;
    public final String userId;

    private TaskSearchCriteria(Builder builder) {
        taskId = builder.taskId;
        taskData = builder.taskData;
        userId = builder.userId;
    }

    public static final class Builder {
        private String taskId;
        private String taskData;
        private String userId;

        public Builder() {
        }

        public Builder taskId(String value) {
            taskId = value;
            return this;
        }

        public Builder taskData(String value) {
            taskData = value;
            return this;
        }

        public Builder userId(String value) {
            userId = value;
            return this;
        }

        public TaskSearchCriteria build() {
            return new TaskSearchCriteria(this);
        }
    }
}

