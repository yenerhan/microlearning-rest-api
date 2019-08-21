package com.yener.microlearning.repository;

import com.yener.microlearning.entity.CompanyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyCardRepository extends JpaRepository<CompanyCard, Long> {

    List<CompanyCard> findByCompanyIdOrDepartmentId(Long companyId, Long DepartmendId);
}
