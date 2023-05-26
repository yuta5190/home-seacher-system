package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/map")
@Controller
public class ShowMapController {

@GetMapping("/")	
public String getIndex(Model model,Integer id) {
model.addAttribute("id",1);
return "map";}

@PostMapping("/")
public String postIndex(Model model,Integer id) {
model.addAttribute("id",id);
return "map";
}
}

