package com.acme.longlife.domain.service;

import com.acme.longlife.domain.model.BigTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BigTreeService {
    Page<BigTree> getAllBigTrees(Pageable pageable);
    BigTree createBigTree(BigTree bigTree);
    BigTree getBigTreeById(Long bigTreeId);
    BigTree updateBigTree(Long bigTreeId, BigTree bigTreeRequest);
    ResponseEntity<?> deleteBigTree(Long bigTreeId);
}
