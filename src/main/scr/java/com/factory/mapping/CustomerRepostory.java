package com.factory.mapping;

import com.factory.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepostory extends JpaRepository<Customer,Long> {
}
