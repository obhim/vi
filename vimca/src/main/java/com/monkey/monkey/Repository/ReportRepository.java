package com.monkey.monkey.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monkey.monkey.models.Report;

public interface ReportRepository  extends JpaRepository<Report, Long>{

}
