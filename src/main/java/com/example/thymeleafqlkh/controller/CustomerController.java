package com.example.thymeleafqlkh.controller;

import com.example.thymeleafqlkh.model.Customer;
import com.example.thymeleafqlkh.service.CustomerService;
import com.example.thymeleafqlkh.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
// @GetMapping => khi nhap duong dan va enter se di den giao dien web
// @PostMapping => sau khi an nut submit trong form html -> thong tin nhap vao form duoc dua den phuong thuc @PostMapping tuong ung (dung trong them va sua thong tin)
@Controller //Khai bao class CustomerController la 1 controller
@RequestMapping("/customers")   //Duong dan den controller sau http://localhost:8081
public class CustomerController {
    private final ICustomerService customerService = new CustomerService();

    @GetMapping("")  //=> hien thi ds: http://localhost:8081/customers do gia tri @GetMapping = null
    public String index(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        //Tra ve trang web index.html
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping("/save")
    public String save(Customer customer) {
        customer.setId((int) (Math.random() * 10000));
        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/view";
    }
}
