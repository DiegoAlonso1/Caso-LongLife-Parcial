package com.acme.longlife.service;

import com.acme.longlife.domain.model.BigTree;
import com.acme.longlife.domain.repository.BigTreeRepository;
import com.acme.longlife.domain.service.BigTreeService;
import com.acme.longlife.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

@Service
public class BigTreeServiceImpl implements BigTreeService {
    @Autowired
    private BigTreeRepository bigTreeRepository;

    @Override
    public Page<BigTree> getAllBigTrees(Pageable pageable) {
        return bigTreeRepository.findAll(pageable);
    }

    @Override
    public BigTree createBigTree(BigTree bigTree) {
        Calendar born = bigTree.getBornAt();
        LocalDate bornDate = LocalDate.of(born.get(Calendar.YEAR),born.get(Calendar.MONTH),born.get(Calendar.DAY_OF_MONTH));

        if(Period.between(bornDate, LocalDate.now()).getYears() < 50)
            throw new RuntimeException("The expected age must be over 50");
        return bigTreeRepository.save(bigTree);
    }

    @Override
    public BigTree getBigTreeById(Long bigTreeId) {
        return bigTreeRepository.findById(bigTreeId).
                orElseThrow(() -> new ResourceNotFoundException("BigTree", "Id", bigTreeId));
    }

    @Override
    public BigTree updateBigTree(Long bigTreeId, BigTree bigTreeRequest) {
        Calendar bornDate = bigTreeRequest.getBornAt();
        LocalDate date = LocalDate.of(bornDate.get(Calendar.YEAR),bornDate.get(Calendar.MONTH),bornDate.get(Calendar.DATE));

        if(Period.between(date, LocalDate.now()).getYears()<50)
            throw new RuntimeException("The expected age must be over 50");

        BigTree bigTree = bigTreeRepository.findById(bigTreeId).
                orElseThrow(() -> new ResourceNotFoundException("BigTree", "Id", bigTreeId));
        bigTree.setUsername(bigTreeRequest.getUsername())
                .setEmail(bigTreeRequest.getEmail())
                .setFirstName(bigTreeRequest.getFirstName())
                .setLastName(bigTreeRequest.getLastName())
                .setGender(bigTreeRequest.getGender())
                .setBornAt(bigTreeRequest.getBornAt());
        return bigTreeRepository.save(bigTree);
    }

    @Override
    public ResponseEntity<?> deleteBigTree(Long bigTreeId) {
        BigTree bigTree = bigTreeRepository.findById(bigTreeId).
                orElseThrow(() -> new ResourceNotFoundException("BigTree", "Id", bigTreeId));
        bigTreeRepository.delete(bigTree);
        return ResponseEntity.ok().build();
    }
}
