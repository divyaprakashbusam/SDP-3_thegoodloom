package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.global.GlobalData;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/shop")
    public String Shop(Model model)
    {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "shop";
    }
	
	@PostMapping("/shop")
    public String AfterLogin()
    {
		return "shop";
    }
	
	@GetMapping("/shop/category/{id}")
    public String ShopByCat(Model model, @PathVariable int id)
    {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		return "shop";
    }
	
	@GetMapping("/shop/viewproduct/{id}")
    public String ViewByCat(Model model, @PathVariable int id)
    {
		model.addAttribute("product", productService.getProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "viewProduct";
    }
	
	

}
