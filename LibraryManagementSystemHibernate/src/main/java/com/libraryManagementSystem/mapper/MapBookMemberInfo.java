package com.libraryManagementSystem.mapper;

import com.libraryManagementSystem.dto.BooKMemberInfo;
import com.libraryManagementSystem.model.Book;

import java.time.LocalDate;
import java.time.ZoneOffset;

public class MapBookMemberInfo {
    public static BooKMemberInfo mapRow(Book book){
        return new BooKMemberInfo(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getStatus(),
                book.getAuthor()==null?null:book.getAuthor().getName(),
                book.getMember()==null?null:book.getMember().getName(),
                book.getMember()==null?null:book.getMember().getEmail(),
                book.getMember()==null?null:book.getMember().getMembershipType(),
                book.getMember()==null?null:LocalDate.ofInstant(book.getMember().getJoinedAt(), ZoneOffset.UTC),
                book.getPublishedYear()
        );
    }
}
