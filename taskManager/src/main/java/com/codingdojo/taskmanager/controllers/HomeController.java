package com.codingdojo.taskmanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.taskmanager.models.TaskModel;
import com.codingdojo.taskmanager.models.UserModel;
import com.codingdojo.taskmanager.services.TaskService;
import com.codingdojo.taskmanager.services.UserService;
import com.codingdojo.taskmanager.validator.UserValidator;



@Controller
public class HomeController {
	private final UserService userService;
	private final UserValidator userValidator;
	private final TaskService taskService;


	
	public HomeController(UserService userService, UserValidator userValidator, TaskService taskService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.taskService = taskService;
	

	}
	
	@RequestMapping("/")
	public String loginReg(@ModelAttribute("user") UserModel user, TaskModel task) {
		return "loginReg.jsp";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") UserModel user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "loginReg.jsp";
		}else {
			userService.registerUser(user);
			session.setAttribute("name", user.getName());
			session.setAttribute("user", user.getId());
			return "redirect:/welcome";
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttribute, HttpSession session) {
		boolean test = userService.authenticateUser(email,  password);
		if(test) {
			UserModel user = userService.findByEmail(email);
			session.setAttribute("user",  user.getId());
			session.setAttribute("name", user.getName());
			return "redirect:/welcome";
		}else {
			redirectAttribute.addFlashAttribute("error", "Invalid Credentials");
			return "redirect:/";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/welcome")
	public String welcome(HttpSession session, Model model,@ModelAttribute("tasks")TaskModel task, UserModel user) {
		if(session.getAttribute("user") == null) {
			return "redirect:/loginReg";
		}else {
			
			Long id = (Long) session.getAttribute("user");
			UserModel users = userService.findUserById(id);
			model.addAttribute("users", users);
			List<TaskModel> tasks = taskService.getAll();
			model.addAttribute("tasks", tasks);
			System.out.println("*****************");
			List<TaskModel> taskPriority = taskService.getByPriority(users.getPriority());
			model.addAttribute("taskPriority", taskPriority);
			return "welcome.jsp";
		}
	}
	
	@RequestMapping("/create")
	public String create(@ModelAttribute("model") TaskModel task, UserModel users, Model model) {
		List<UserModel> user = userService.getAll();
		model.addAttribute("user", user);
		return "create.jsp";
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String newIdea(@Valid @ModelAttribute("tasks")TaskModel task, BindingResult result, HttpSession session, RedirectAttributes redirectAttribute) {
		if(result.hasErrors()) {
			redirectAttribute.addFlashAttribute("error", "Cannont be Blank");
			return "redirect:/create";
		}else {
			TaskModel taskModel = taskService.newTask(task);
			Long id = (Long) session.getAttribute("user");
			UserModel userModel = userService.findUserById(id);
//			List<UserModel> users = userService.getAll();
			taskModel.setUser(userModel);
			taskService.update(taskModel);
			return "redirect:/welcome";
		}
	}
	
	@RequestMapping("/view/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		TaskModel task = taskService.getTaskById(id);
		model.addAttribute("task", task);
		return "view.jsp";	
	}
	
	@RequestMapping("/edit/{id}")
	public String editIdea(@PathVariable("id")Long id, Model model, HttpSession session, UserModel users, TaskModel tasks) {
//		if(session.getAttribute("id") != tasks.getAttribute("user_Id") {
//			return "redirect:/welcome";
//		}else {
			TaskModel task = taskService.getTaskById(id);
			model.addAttribute("task", task);
			List<UserModel> user = userService.getAll();
			model.addAttribute("user", user);
			return "edit.jsp";
//		}
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String updateIdea(@PathVariable("id") Long id, @RequestParam(value="task") String str) {	

			taskService.update(id, str);
			return "redirect:/welcome";
	
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteTask(@PathVariable("id")Long id) {
		taskService.delete(id);
		return "redirect:/welcome";
	}
	
	@RequestMapping("/completed/{id}")
	public String completedTask(@PathVariable("id")Long id) {
		taskService.delete(id);
		return "redirect:/welcome";
	}
	

}
