package com.libraryManagementSystem.repository;

import com.libraryManagementSystem.model.Author;
import com.libraryManagementSystem.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Member> getMemberById(long memberId) {
        return Optional.ofNullable(entityManager.find(Member.class,memberId));
    }
}
