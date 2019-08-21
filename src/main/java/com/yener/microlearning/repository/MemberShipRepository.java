package com.yener.microlearning.repository;

import com.yener.microlearning.entity.MemberShip;
import com.yener.microlearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberShipRepository extends JpaRepository<MemberShip, Long> {

    MemberShip findByCompanyId(Long companyId);
    MemberShip findByCode(String code);
}
