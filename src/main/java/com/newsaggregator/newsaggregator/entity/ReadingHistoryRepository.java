package com.newsaggregator.newsaggregator.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReadingHistoryRepository extends JpaRepository<ReadingHistory, Long> {
    List<ReadingHistory> findByUserId(String userId);
}