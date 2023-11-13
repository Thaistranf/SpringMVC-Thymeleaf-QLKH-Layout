package com.example.thymeleafqlkh.service;

import com.example.thymeleafqlkh.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    void save (Customer customer);
    Customer findById(int id);
    void update(int id, Customer customer);
    void remove(int id);
}
