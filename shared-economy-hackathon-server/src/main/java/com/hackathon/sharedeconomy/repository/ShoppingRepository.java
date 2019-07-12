package com.hackathon.sharedeconomy.repository;

import com.hackathon.sharedeconomy.model.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YoungMan on 2019-02-14.
 */

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
}
