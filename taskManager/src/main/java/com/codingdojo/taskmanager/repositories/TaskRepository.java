package com.codingdojo.taskmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.taskmanager.models.TaskModel;



@Repository
public interface TaskRepository extends CrudRepository<TaskModel, Long>{
	List<TaskModel> findAll();
	Optional<TaskModel> findById(Long id);
	TaskModel findTaskById(Long id);
//	List<IdeaModel> findAllByLike(String like);
	List<TaskModel> findAllByPriority(String priority);
	
}