package com.libraryManagementSystem.dto;

import com.libraryManagementSystem.enums.Genre;
import com.libraryManagementSystem.enums.MembershipType;
import com.libraryManagementSystem.enums.Status;

import java.time.LocalDate;
import java.util.Date;

public record BooKMemberInfo(
        long id,
        String title,
        Genre genre,
        Status status,
        String author,
        String name,
        String email,
        MembershipType membershipType,
        LocalDate joinedAt,
        int publishedYear
) {
}
