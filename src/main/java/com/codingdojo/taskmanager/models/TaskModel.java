package com.codingdojo.taskmanager.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Entity
@Table(name="tasks")
public class TaskModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Size(min=3, message="Task's name should be longer than 3 characters!")
	private String task;
	@Size(min=3, message="Please select assignee!")
	private String assignee;
	@Size(min=3, message="Please select priority!")
	private String priority;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
    		name="taskJoined",
    		joinColumns = @JoinColumn(name="task_id"),
    		inverseJoinColumns = @JoinColumn(name="user_id")
    		)
	private List<UserModel> users;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserModel user;
	
	
	public TaskModel() {
		
	}
	public TaskModel(String task, String assignee, String priority, List<UserModel> users) {
		super();
		this.task = task;
		this.users = users;
		this.users = users;
		this.assignee = assignee;
		this.priority = priority;

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String taskEdit) {
		this.task = taskEdit;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assigneeEdit) {
		this.assignee = assigneeEdit;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priorityEdit) {
		this.priority = priorityEdit;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public UserModel getUser() {
		return user;
	}
	
	public void setUser(UserModel user) {
		this.user = user;
	}
	
	
	public List<UserModel> getUsers() {
		return users;
	}
	
	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
	
	
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}	

}
