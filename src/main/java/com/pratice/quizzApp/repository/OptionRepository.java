package com.pratice.quizzApp.repository;

import com.pratice.quizzApp.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

}
