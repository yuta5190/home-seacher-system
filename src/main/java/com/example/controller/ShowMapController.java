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
 * map表示のメソッド
 * @param model　モデル
 * @param id　それぞれのid
 * @param infomationType　そのidが何のidなのか
 * @return
 */
@PostMapping("/")
public String postIndex(Model model,Integer id,String infomationType) {
model.addAttribute("id",id);
model.addAttribute("infomationType",infomationType);
return "map";
}
}

