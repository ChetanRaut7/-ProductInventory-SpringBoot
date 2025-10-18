package com.Sathya.mvc.controller;

import com.Sathya.mvc.model.Product;
import com.Sathya.mvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-insertion";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              RedirectAttributes redirectAttributes) {
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            if (!imageFile.isEmpty()) {
                String fileName = imageFile.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.write(filePath, imageFile.getBytes());
                product.setImage(fileName);
            }

            service.saveProduct(product);
            redirectAttributes.addFlashAttribute("msg", "✅ Product added successfully!");
            return "redirect:/products";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "❌ Failed to add product.");
            return "redirect:/add";
        }
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        model.addAttribute("list", service.getAllProducts());
        return "product-list";
    }

    
    @GetMapping("/search")
    public String showSearchForm() {
        return "search-product";
    }
    
    @GetMapping("/search-result")
    public String searchProduct(@RequestParam("id") Long id, Model model) {
        Product product = service.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
        } else {
            model.addAttribute("msg", "Product not found");
        }
        return "search-product";
    }
    
//    @GetMapping("/search")
//    public String searchProduct(@RequestParam("id") Long id, Model model) {
//        Product product = service.getProductById(id);
//        if (product != null) {
//            model.addAttribute("product", product);
//        } else {
//            model.addAttribute("msg", "Product not found");
//        }
//        return "search-product";
//    }
    
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = service.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "edit-product"; // matches edit-product.html
        } else {
            model.addAttribute("msg", "Product not found");
            return "redirect:/products";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.deleteProduct(id);
        redirectAttributes.addFlashAttribute("msg", "🗑️ Product deleted");
        return "redirect:/products";
    }
    
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }
    
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product,
                                @RequestParam("imageFile") MultipartFile imageFile) {
        service.updateProduct(product, imageFile); // You handle saving logic here
        return "redirect:/products"; // Go back to product list after update
    }
    
    
}