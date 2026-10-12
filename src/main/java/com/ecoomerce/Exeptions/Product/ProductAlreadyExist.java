package com.ecoomerce.Exeptions.Product;

public class ProductAlreadyExist extends RuntimeException {
    public ProductAlreadyExist(String message) {
        super(message);
    }
}
