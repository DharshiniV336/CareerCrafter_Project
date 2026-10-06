package com.libraryManagementSystem.main;

import com.libraryManagementSystem.config.AppConfig;
import com.libraryManagementSystem.dto.BooKMemberInfo;
import com.libraryManagementSystem.enums.Genre;
import com.libraryManagementSystem.enums.Status;
import com.libraryManagementSystem.exception.ResourceNotFoundException;
import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.service.BookSerivce;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        BookSerivce bookSerivce=context.getBean(BookSerivce.class);
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("-----Library Management System------");
            System.out.println("1. Save or Add book");
            System.out.println("2. Find book by ID");
            System.out.println("3. Get all books with Author and member info");
            System.out.println("4. Exit");
            try{
                int choice=sc.nextInt();
                sc.nextLine();

                switch (choice){
                    case 1:
                        Book book=new Book();
                        System.out.println("Enter the book title:");
                        book.setTitle(sc.nextLine());

                        System.out.println("Select the genre of the book:");
                        System.out.println("FICTION,\n" + "NON_FICTION,\n" + "SCIENCE,\n" + "HISTORY,\n" + "BIOGRAPHY");
                        book.setGenre(Genre.valueOf(sc.next().toUpperCase()));

                        System.out.println("Select the status of the book:");
                        System.out.println("AVAILABLE,\n" + "BORROWED,\n" + "LOST");
                        Status status=Status.valueOf(sc.next().toUpperCase());
                        book.setStatus(status);

                        System.out.println("Enter the author ID:");
                        long authorId=sc.nextLong();
                        long memberId=0;
                        if(status==Status.BORROWED) {
                            System.out.println("Enter the member ID:");
                            memberId = sc.nextLong();
                        }
                        System.out.println("Enter the published year of this book:");
                        book.setPublishedYear(sc.nextInt());

                        bookSerivce.save(book,authorId,memberId);
                        System.out.println("Book saved successfully");
                        break;
                    case 2:
                        System.out.println("Enter the book ID:");
                        Book book1=bookSerivce.findById(sc.nextLong());
                        System.out.println("Book ID: "+book1.getId());
                        System.out.println("Book title: "+book1.getTitle());
                        System.out.println("Book genre: "+book1.getGenre());
                        System.out.println("Book status: "+book1.getStatus());

                        System.out.println("Book author ID: "+book1.getAuthor().getId());
                        if(book1.getMember()!=null) {
                            System.out.println("Book member ID: " + book1.getMember().getId());
                        }
                        System.out.println("Book published year: "+book1.getPublishedYear());
                        break;
                    case 3:
                        List<BooKMemberInfo> list=bookSerivce.findAll();
                        list.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }catch (ResourceNotFoundException e){
                System.out.println(e.getMessage());
            }
            catch (IllegalArgumentException e){
                System.out.println("Invalid enum select the correct enum");
            }catch (InputMismatchException e){
                System.out.println("Input mismatch");
                sc.nextLine();
            }
        }
    }
}
