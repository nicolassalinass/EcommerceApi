package com.ecoomerce.Exeptions.Product;

public class ProductNotFoundExeptions extends RuntimeException {
    public ProductNotFoundExeptions(String message) {
        super(message);
    }
}
