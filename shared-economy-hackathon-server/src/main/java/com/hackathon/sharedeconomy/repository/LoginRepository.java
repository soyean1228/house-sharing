package com.hackathon.sharedeconomy.repository;

import com.hackathon.sharedeconomy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LoginRepository extends JpaRepository<User, String> {
}