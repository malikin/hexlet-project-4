package io.hexlet.hexletproject4.repository;

import io.hexlet.hexletproject4.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
