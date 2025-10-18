			package com.Sathya.mvc.service;
			

			import org.springframework.beans.factory.annotation.Autowired;
			import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Sathya.mvc.model.Product;
				import com.Sathya.mvc.repository.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

			@Service
			public class ProductService {
			    @Autowired
			    private ProductRepository repo;

			    public Product saveProduct(Product product) {
			        return repo.save(product);
			    }

			    public List<Product> getAllProducts() {
			        return repo.findAll();
			    }

			    public Product getProductById(Long id) {
			        return repo.findById(id).orElse(null);
			    }

			    public void deleteProduct(Long id) {
			        repo.deleteById(id);
			    }

			    public void updateProduct(Product product, MultipartFile imageFile) {
			        if (!imageFile.isEmpty()) {
			            String imageName = saveImage(imageFile);
			            product.setImage(imageName);
			        } else {
			            // Preserve existing image
			            Product existing = repo.findById(product.getId()).orElse(null);
			            if (existing != null) {
			                product.setImage(existing.getImage());
			            }
			        }

			        repo.save(product);
			    }

			    private String saveImage(MultipartFile imageFile) {
			        String fileName = imageFile.getOriginalFilename();
			        Path uploadPath = Paths.get("uploads");

			        try {
			            Files.createDirectories(uploadPath); // Create folder if not exists
			            Path filePath = uploadPath.resolve(fileName);
			            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			            return fileName;
			        } catch (IOException e) {
			            e.printStackTrace();
			            return null;
			        }
			    }

			}