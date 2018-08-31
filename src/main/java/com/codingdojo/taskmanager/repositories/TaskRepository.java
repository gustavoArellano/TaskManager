package com.codingdojo.taskmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.taskmanager.models.TaskModel;



@Repository
public interface TaskRepository extends PagingAndSortingRepository<TaskModel, Long>{
	List<TaskModel> findAll();
	Optional<TaskModel> findById(Long id);
	TaskModel findTaskById(Long id);
	List<TaskModel> findAllByOrderByPriority();
}