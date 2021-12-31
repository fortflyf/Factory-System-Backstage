package com.factory.mapping;

import com.factory.pojo.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepostory extends JpaRepository<Worker,Long> {
}
