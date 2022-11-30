package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.RequestLog;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {

}
