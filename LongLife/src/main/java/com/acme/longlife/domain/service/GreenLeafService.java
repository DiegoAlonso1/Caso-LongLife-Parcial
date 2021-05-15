package com.acme.longlife.domain.service;

import com.acme.longlife.domain.model.GreenLeaf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GreenLeafService {
    Page<GreenLeaf> getAllGreenLeavesByBigTreeId(Long bigTreeId, Pageable pageable);
    GreenLeaf getGreenLeafByIdAndBigTreeId(Long bigTreeId, Long greenLeafId);
    GreenLeaf createGreenLeaf(Long bigTreeId, GreenLeaf greenLeaf);
    GreenLeaf updateGreenLeaf(Long bigTreeId, Long greenLeafId, GreenLeaf greenLeafRequest);
    ResponseEntity<?> deleteGreenLeaf(Long bigTreeId, Long greenLeafId);
}
