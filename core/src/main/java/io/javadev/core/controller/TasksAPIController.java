package io.javadev.core.controller;

import com.github.dozermapper.core.Mapper;
import io.javadev.core.domain.Task;
import io.javadev.core.domain.VOs.TaskVO;
import io.javadev.core.domain.criteria.TaskSearchCriteria;
import io.javadev.core.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class TasksAPIController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private Mapper mapper;

    @RequestMapping(value = "/tasks", produces = {"application/json"})
    public ResponseEntity<List<TaskVO>> retrieveTasks(
            @Valid @RequestParam(value = "taskData", required = false) String taskData,
            @Valid @RequestParam(value = "userId", required = false) String userId
    ) {

        TaskSearchCriteria.Builder taskSearchCriteriaBuilder = new TaskSearchCriteria.Builder()
                .taskData(taskData).userId(userId);

        List<Task> taskList = taskService.findBySearchCriteria(taskSearchCriteriaBuilder.build());
        List<TaskVO> result = taskList.stream().map(o -> mapper.map(o, TaskVO.class)).toList();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{taskId}", produces = {"application/json"})
    public ResponseEntity<TaskVO> retrieveTaskByID(
            @PathVariable("taskId") String taskId
    ) {

        TaskSearchCriteria.Builder taskSearchCriteriaBuilder = new TaskSearchCriteria.Builder()
                .taskId(taskId);

        List<Task> taskList = taskService.findBySearchCriteria(taskSearchCriteriaBuilder.build());
        Optional<TaskVO> result = taskList.stream().map(o -> mapper.map(o, TaskVO.class)).findFirst();

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
    }
}
