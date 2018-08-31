package com.codingdojo.taskmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.taskmanager.models.TaskModel;
import com.codingdojo.taskmanager.repositories.TaskRepository;



@Service
public class TaskService {
	private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<TaskModel> getAll() {
		return taskRepository.findAll();
	}
	
	public Optional<TaskModel> getById(Long id) {
		return taskRepository.findById(id);
	}
	
	public TaskModel getTaskById(Long id) {
		return taskRepository.findTaskById(id);
	}
	
	public TaskModel newTask(TaskModel task) {
		return taskRepository.save(task);
	}
	
	public void update(TaskModel task) {
		taskRepository.save(task);
	}
	
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}

	public TaskModel update(Long id, String str) {
		TaskModel t = getTaskById(id);
		t.setTask(str);	
		return taskRepository.save(t);		
	}
	
	public List<TaskModel> getByPriority(String priority){
		return taskRepository.findAllByPriority(priority);
	}
	
	

}
