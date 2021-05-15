package com.acme.longlife.service;

import com.acme.longlife.domain.model.GreenLeaf;
import com.acme.longlife.domain.repository.BigTreeRepository;
import com.acme.longlife.domain.repository.GreenLeafRepository;
import com.acme.longlife.domain.service.GreenLeafService;
import com.acme.longlife.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GreenLeafServiceImpl implements GreenLeafService {
    @Autowired
    private GreenLeafRepository greenLeafRepository;
    @Autowired
    private BigTreeRepository bigTreeRepository;

    @Override
    public Page<GreenLeaf> getAllGreenLeavesByBigTreeId(Long bigTreeId, Pageable pageable) {
        return greenLeafRepository.findAllByBigTreeId(bigTreeId, pageable);
    }

    @Override
    public GreenLeaf getGreenLeafByIdAndBigTreeId(Long bigTreeId, Long greenLeafId) {
        return greenLeafRepository.findByIdAndAndBigTreeId(greenLeafId, bigTreeId).
                orElseThrow(()->new ResourceNotFoundException("GreenLeaf not found with Id "
                        + greenLeafId + " and BigTreeId " + bigTreeId));
    }

    @Override
    public GreenLeaf createGreenLeaf(Long bigTreeId, GreenLeaf greenLeafRequest) {
        if (greenLeafRepository.existsByBigTreeIdAndTitle(bigTreeId, greenLeafRequest.getTitle()))
            throw new RuntimeException("Cannot exist two Green Leaves with same title");

        return bigTreeRepository.findById(bigTreeId).map(bigTree -> {
            greenLeafRequest.setBigTree(bigTree);
            return greenLeafRepository.save(greenLeafRequest);
        }).orElseThrow(()->new ResourceNotFoundException("BigTree", "Id", bigTreeId));
    }

    @Override
    public GreenLeaf updateGreenLeaf(Long bigTreeId, Long greenLeafId, GreenLeaf greenLeafRequest) {
        if (greenLeafRepository.existsByBigTreeIdAndTitle(bigTreeId, greenLeafRequest.getTitle()))
            throw new RuntimeException("Cannot exist two Green Leaves with same title");
        if (!bigTreeRepository.existsById(bigTreeId))
            throw new ResourceNotFoundException("BigTree", "Id", bigTreeId);
        return greenLeafRepository.findById(greenLeafId).map(greenLeaf -> {
            greenLeaf.setTitle(greenLeafRequest.getTitle())
                    .setScenario(greenLeafRequest.getScenario())
                    .setTip(greenLeafRequest.getTip());
            return greenLeafRepository.save(greenLeaf);
        }).orElseThrow(()->new ResourceNotFoundException("GreenLeaf", "Id", greenLeafId));
    }

    @Override
    public ResponseEntity<?> deleteGreenLeaf(Long bigTreeId, Long greenLeafId) {
        if (!bigTreeRepository.existsById(bigTreeId))
            throw new ResourceNotFoundException("BigTree", "Id", bigTreeId);
        return greenLeafRepository.findById(greenLeafId).map(greenLeaf -> {
            greenLeafRepository.delete(greenLeaf);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("GreenLeaf", "Id", greenLeafId));
    }
}
