package io.javadev.core.repository;

import io.javadev.core.domain.Task;
import io.javadev.core.domain.criteria.TaskSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskJpaRepository extends JpaRepository<Task, String> {

    @Query("SELECT t FROM Task t JOIN FETCH t.acquiredBy WHERE t.acquiredBy is not null AND " +
            "(:#{#param.taskId} is null or t.id = :#{#param.taskId}) AND " +
            "(:#{#param.taskData} is null or t.taskData like %:#{#param.taskData}%) AND " +
            "(:#{#param.userId} is null or t.acquiredBy.id = :#{#param.userId})")
    List<Task> findBySearchCriteria(@Param("param") TaskSearchCriteria searchCriteria);

}
