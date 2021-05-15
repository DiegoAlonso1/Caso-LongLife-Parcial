package com.acme.longlife.domain.repository;

import com.acme.longlife.domain.model.SapDrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SapDropRepository extends JpaRepository<SapDrop,Long> {

}
