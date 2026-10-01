package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Electronic;
import za.ac.cput.domain.Product;
import za.ac.cput.service.ElectronicService;
import za.ac.cput.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    //  Service Variables
    private final ProductService productService;
    private final ElectronicService electronicService;

    //  Constructor
    public ProductController(ProductService productService, ElectronicService electronicService) {
        this.productService = productService;
        this.electronicService = electronicService;
    }

    //  Create
    @PostMapping("/electronics/create")
    public Electronic createElectronic(@RequestBody Electronic electronic){
        return electronicService.create(electronic);
    }

    //  Read
    @GetMapping("/read/{id}")
    public Product read(@PathVariable int id){
        return productService.read(id);
    }

    //  Update
    @PutMapping("/electronics/update")
    public Electronic updateElectronic(@RequestBody Electronic electronic){
        return electronicService.update(electronic);
    }

    //  Delete
    @DeleteMapping("/update/{id}")
    public void update(@PathVariable int id){
        productService.delete(id);
    }

    //  GetAll
    @GetMapping("/getAll")
    public List<Product> getAll(){
        return productService.getAll();
    }

    // FindByAgentId
    @GetMapping("/customer/{customerId}")
    public List<Product> getByCustomerId(@PathVariable int customerId){
        return productService.findByCustomerId(customerId);
    }
}
