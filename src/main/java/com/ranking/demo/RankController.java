package com.ranking.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/rank")
@RestController
public class RankController {
    private final RankRepository rankRepository;

    @Autowired
    public RankController(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RankEntity> saveGame(@RequestBody RankEntity rankEntity) {
        Optional<RankEntity> rankOptional = rankRepository.findByLogin(rankEntity.getLogin());
        rankOptional.ifPresent(rank -> {
            if (rank.getPoints() <= rankEntity.getPoints()) {
                rank.setPoints(rankEntity.getPoints());
                rank.setDate(rankEntity.getDate());
                rankRepository.save(rank);
            }
        });

        if (!rankOptional.isPresent()) {
            return ResponseEntity.ok(rankRepository.save(rankEntity));
        }

        return ResponseEntity.ok(rankOptional.get());
    }

    @GetMapping
    public ResponseEntity<List<RankEntity>> getList() {
        return ResponseEntity.ok(rankRepository.findAll());
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<RankEntity>> getListSortedByPoints() {
        return ResponseEntity.ok(rankRepository.findAll(Sort.by(Sort.Direction.DESC, "points")));
    }
}

