package com.yener.microlearning.repository;

import com.yener.microlearning.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByCompanyId(Long companyId);

    Card findByCardId(Long cardId);
}
