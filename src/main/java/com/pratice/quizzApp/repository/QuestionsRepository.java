package com.pratice.quizzApp.repository;

import com.pratice.quizzApp.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionsRepository extends JpaRepository<Question,Long> {

    @Override
    Optional<Question> findById(Long aLong);
}
