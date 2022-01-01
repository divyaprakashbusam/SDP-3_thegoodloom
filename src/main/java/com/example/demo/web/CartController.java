package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.global.GlobalData;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id)
	{
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String getCart(Model model)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart";
	}
	
	   
	//Remove item
	
		@GetMapping("/cart/removeItem/{index}")
	    public String removeItem(@PathVariable int index)
	    {
			GlobalData.cart.remove(index);
			return "redirect:/cart";
	    }
		
		
		@GetMapping("/checkout")
	    public String checkOut(Model model)
	    {
			model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
			return "checkout";
	    }
		
		@PostMapping("/pay")
		public String PayAfter(Model model) {
			model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
			return "pay";
		}
		
		@GetMapping("/pay")
		public String PayGet() {
			return "pay";
		}
	
	
}
