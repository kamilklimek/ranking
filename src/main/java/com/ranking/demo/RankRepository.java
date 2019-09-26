package com.ranking.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankRepository extends JpaRepository<RankEntity, Long> {
    Optional<RankEntity> findByLogin(String login);

}
