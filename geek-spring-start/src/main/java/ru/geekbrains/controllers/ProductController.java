package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.geekbrains.Product;
import ru.geekbrains.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void  setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAllProducts(Model uiModel) {
        List<Product> productList = productService.getAll();
        uiModel.addAttribute("products", productList);
        return "home";
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public String findById(Model uiModel, @PathVariable(value = "id") int id) {
        Product product = productService.getProductById(id);
        uiModel.addAttribute("product", product);
        return "home";
    }

    @RequestMapping("/newProduct")
    public String addNewProduct(Model uiModel) {
        uiModel.addAttribute("product", new Product());
        return "productForm";
    }
    @RequestMapping("/processProductForm")
    public String processProductForm(Model uiModel, @ModelAttribute("product") Product product) {
        productService.addProduct(product);
        uiModel.addAttribute("product", product);
        return "product";
    }

}
