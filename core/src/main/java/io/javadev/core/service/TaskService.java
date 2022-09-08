package io.javadev.core.service;

import io.javadev.core.domain.Task;
import io.javadev.core.domain.criteria.TaskSearchCriteria;
import io.javadev.core.repository.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    @Autowired
    TaskJpaRepository taskRepository;

    public List<Task> findBySearchCriteria(TaskSearchCriteria criteria) {
        return taskRepository.findBySearchCriteria(criteria);
    }
}
