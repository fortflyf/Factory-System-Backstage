package com.factory.mapping;

import com.factory.pojo.Finance;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FinanceRepostory extends JpaRepository<Finance,Long> {
}
