package com.acme.longlife.domain.repository;

import com.acme.longlife.domain.model.BigTree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BigTreeRepository extends JpaRepository<BigTree,Long> {

}
