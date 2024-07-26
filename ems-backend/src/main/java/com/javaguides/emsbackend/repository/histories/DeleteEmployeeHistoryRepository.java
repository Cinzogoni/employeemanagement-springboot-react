package com.javaguides.emsbackend.repository.histories;

import com.javaguides.emsbackend.entity.DeleteEmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteEmployeeHistoryRepository extends JpaRepository<DeleteEmployeeHistory, Long> {
}
