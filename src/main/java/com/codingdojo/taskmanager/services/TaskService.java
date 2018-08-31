package com.codingdojo.taskmanager.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.taskmanager.models.TaskModel;
import com.codingdojo.taskmanager.repositories.TaskRepository;



@Service
@Transactional
public class TaskService {
	@Autowired
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

	public TaskModel update(Long id, String taskEdit, String assigneeEdit, String priorityEdit) {
		TaskModel t = getTaskById(id);
		t.setTask(taskEdit);
		t.setAssignee(assigneeEdit);
		t.setPriority(priorityEdit);
		
		
		return taskRepository.save(t);		
	}
	
//	public List<TaskModel> getByPriority(){
//		return taskRepository.findAllByOrderByPriority();
//	}
	
	
	
	
	

}
