package com.ecommerce.main;

import com.ecommerce.config.AppConfig;
import com.ecommerce.dto.VendorCount;
import com.ecommerce.enums.Name;
import com.ecommerce.exception.InvalidProductID;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.Vendor;
import com.ecommerce.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService=context.getBean(ProductService.class);
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("-----------------Welcome to Ecommerce application-----------------");
            System.out.println("Choices are:");
            System.out.println("1. Insert a product");
            System.out.println("2. Finding a product using it's ID");
            System.out.println("3. To update the stock quantity");
            System.out.println("4. To get the all product count with it's vendor");
            System.out.println("5. Exiting....");
            System.out.println("Enter your choice:");
            int choice=sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    Product product=new Product();

                    System.out.println("Enter the Product name:");
                    product.setName(sc.nextLine());

                    System.out.println("Enter the Product price:");
                    product.setPrice(sc.nextDouble());

                    System.out.println("Enter the Product's stock quantity:");
                    product.setStockQuantity(sc.nextInt());
                    sc.nextLine();

                    System.out.println("Now enter the product's category details");

                    Category category=new Category();

                    System.out.println("select the category name:");
                    System.out.println("ELECTRONICS,\n" + "FASHION,\n" + "HOME,\n" + "BEAUTY,\n" + "SPORTS,\n" + "BOOKS,\n" + "TOYS,\n" + "GROCERIES,\n" + "AUTOMOTIVE,\n" + "HEALTH");
                    try {
                        category.setName(Name.valueOf(sc.nextLine().trim().toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category name select from the above listed category");
                        break;
                    }

                    System.out.println("Enter the description:");
                    category.setDescription(sc.nextLine());

                    product.setCategory(category);

                    System.out.println("Now, let's enter the vendor details");
                    Vendor vendor=new Vendor();

                    System.out.println("Enter the vendor's name:");
                    vendor.setName(sc.nextLine());

                    System.out.println("Enter the vendor's email:");
                    vendor.setEmail(sc.nextLine().toLowerCase().trim());

                    product.setVendor(vendor);
                    productService.save(product);

                    System.out.println("Product save successfully!");
                    break;

                case 2:
                    System.out.println("Enter the product's ID: ");
                    try{
                        Product p=productService.findById(sc.nextLong());

                        System.out.println("The Product info you searched for:");

                        System.out.println("Product ID: "+p.getId());
                        System.out.println("Product name: "+p.getName());
                        System.out.println("Product price: "+p.getPrice());
                        System.out.println("Product stock quantity: "+p.getStockQuantity());
                        System.out.println("Product's category ID: "+p.getCategory().getId());
                        System.out.println("Product's vendor ID: "+p.getVendor().getId());
                        System.out.println("Product's category name: "+p.getCategory().getName());
                        System.out.println("Product's vendor name: "+p.getVendor().getName());
                    }catch (InvalidProductID e){
                        System.out.println("Error: "+e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Enter the Product ID which you want to update the stock:");
                    Long id=sc.nextLong();
                    try {
                        if (productService.findById(id) != null) {
                            System.out.println("Enter the new stock quantity:");
                            int newQuantity = sc.nextInt();
                            productService.updateStock(id, newQuantity);
                            System.out.println("The stock quantity has been updated successfully");
                        } else {
                            System.out.println("Invalid product ID please enter the correct product ID");
                        }
                    }catch (InvalidProductID e){
                            System.out.println("Error: "+e.getMessage());
                    }

                    break;
                case 4:
                    Map<String,Integer> vendorCount=productService.countProductsByVendor();
                    for(Map.Entry<String,Integer> entry: vendorCount.entrySet()){
                        System.out.println("Vendor's name: "+entry.getKey()+"       Product count: "+entry.getValue());
                    }
                    break;
                case 5:
                    System.out.println("Thank you! come again :)");
                    return;
                default:
                    System.out.println("Invalid choice :(");
            }
        }
    }
}
