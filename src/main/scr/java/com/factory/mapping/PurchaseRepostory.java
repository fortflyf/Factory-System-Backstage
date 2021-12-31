package com.factory.mapping;


import com.factory.pojo.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PurchaseRepostory extends JpaRepository<Purchase,Long> {
}
