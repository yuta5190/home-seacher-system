package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * map表示がめん.
 * 
 * @author yoshida_yuuta
 *
 */
@RequestMapping("/map")
@Controller
public class ShowMapController {

/**
 * 仮画面表示(get時)
 * @param model
 * @return
 */
@GetMapping("/")	
public String getIndex(Model model) {
model.addAttribute("id",1);
return "map";}

/**
 * idを受け取る画面表示
 * @param model
 * @param id　駅id or 地区id
 * @return map表示画面
 */
@PostMapping("/")
public String postIndex(Model model,Integer id) {
model.addAttribute("id",id);
return "map";
}
}

