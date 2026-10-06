package com.libraryManagementSystem.service;

import com.libraryManagementSystem.dto.BooKMemberInfo;
import com.libraryManagementSystem.exception.ResourceNotFoundException;
import com.libraryManagementSystem.mapper.MapBookMemberInfo;
import com.libraryManagementSystem.model.Author;
import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.model.Member;
import com.libraryManagementSystem.repository.AuthorRepository;
import com.libraryManagementSystem.repository.BookRepository;
import com.libraryManagementSystem.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookSerivce {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;

    public BookSerivce(BookRepository bookRepository, AuthorRepository authorRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.memberRepository = memberRepository;
    }

    public void save(Book book, long authorId, long memberId) {
        //step 1: Fetch Author object from DB by using ID(authorId)
        Optional<Author> optional=authorRepository.getAuthorById(authorId);
        if(optional.isEmpty()||optional==null){
            throw new ResourceNotFoundException("The Author ID is invalid");
        }
        Author author=optional.get();

        //step 2: Fetch member object from DB by using ID(memberId) if the book has member
        if(memberId!=0){
            Optional<Member> optional1 =memberRepository.getMemberById(memberId);
            if(optional1.isEmpty()||optional1==null){
                throw new ResourceNotFoundException("The Member ID is invalid");
            }
            Member member =optional1.get();
            //step 3: set member to book
            book.setMember(member);
        }
        //step 3.5: set author to book
        book.setAuthor(author);

        //step 4: pass the book to BookRepository
        bookRepository.save(book);
    }

    public Book findById(long id) {
        Book book= bookRepository.findById(id);
        if(book==null){
            throw new ResourceNotFoundException("Invalid book ID");
        }
        return book;
    }

    public List<BooKMemberInfo> findAll() {
        List<Book> list=bookRepository.findAll();
        return list
                .stream()
                .map(MapBookMemberInfo::mapRow)
                .toList();
    }
}
