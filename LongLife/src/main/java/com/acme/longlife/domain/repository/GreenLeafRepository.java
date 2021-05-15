package com.acme.longlife.domain.repository;

import com.acme.longlife.domain.model.GreenLeaf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GreenLeafRepository extends JpaRepository<GreenLeaf,Long> {
    Page<GreenLeaf> findAllByBigTreeId(Long bigTreeId, Pageable pageable);
    Boolean existsByBigTreeIdAndTitle(Long bigTreeId, String title);
    Optional<GreenLeaf> findByIdAndAndBigTreeId(Long greenLeafId, Long bigTreeId);
}
