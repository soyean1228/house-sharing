package com.hackathon.sharedeconomy.repository;

import com.hackathon.sharedeconomy.model.entity.ForSale;
import com.hackathon.sharedeconomy.repository.custom.ForSaleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-14.
 */

public interface ForSaleRepository extends JpaRepository<ForSale, Long>, ForSaleRepositoryCustom {

    @Query("select f from ForSale f join f.user u where f.name = :name and u.id = :forSaleUserId")
    ForSale findByNameAndUserId(@Param("name") String name, @Param("forSaleUserId") String forSaleUserId);

    @Query("select f from ForSale f join f.user u where u.id = :forSaleUserId and f.saleType = 'SALE'")
    ForSale findByUserId(@Param("forSaleUserId") String forSaleUserId);

    @Query("select f from ForSale f join f.user u where u.address like concat('%',:address,'%')")
    List<ForSale> getForSaleByUserId(@Param("address") String address);

    @Query("select f from ForSale f join f.user u where u.address like concat('%',:address,'%')")
    List<ForSale> getSearchForSaleByAddress(@Param("address") String address);

}
