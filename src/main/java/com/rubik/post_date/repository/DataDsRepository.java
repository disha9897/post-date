package com.rubik.post_date.repository;

import com.rubik.post_date.domain.entity.DataDsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataDsRepository extends JpaRepository<DataDsEntity, Long> {
}
