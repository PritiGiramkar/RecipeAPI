package com.demo.springdemoproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.springdemoproject.model.Recipe;
import com.demo.springdemoproject.repository.RecipeJPARepository;

@RestController
@RequestMapping("/recipe")
public class UserController {
	@Autowired
	private RecipeJPARepository recipeJPArepository;
	
	@PostMapping(value="/load")
	public void load(@RequestBody final Recipe recipe)
	{
		recipeJPArepository.save(recipe);
		// to check whether the particular data got inserted or not
//		return recipeJPArepository.findByTitle(recipe.getTitle());
		//it is function under optional object which states if the value is present or not -> Nosuchelementexception
		//userJPArepository.findByID(user.getID()).get();
	}
	
	
	//@RequestMapping(value="/all", method = RequestMethod.GET)
	@GetMapping(value= "/all")	// this can be used instead of line given above 
	public List<Recipe> findAll()
	{
		return recipeJPArepository.findAll();
	}
	
	@GetMapping(value= "name")
	@ResponseBody
	public List<Recipe> findByName(@RequestParam String name)
	{
		System.out.println(name);
		return recipeJPArepository.findByTitle(name);
	}
	
	@GetMapping(value= "id/{id}")
	public Optional<Recipe> findById(@PathVariable final Long id)//Pathvariable is used to handle template variable in request URI mapping
	{
		return recipeJPArepository.findById(id);
	}
	
	@GetMapping(value= "message")
	public String getApplicationMessage()
	{
		return "This application returns user details";
	}
	
	@GetMapping(value="/add/{n1}/{n2}")
	public Integer addition(@PathVariable String n1,@PathVariable String n2)
	{
		return Integer.parseInt(n1)+Integer.parseInt(n2);
	}
	
}
