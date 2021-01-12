package com.zb.repository;

import com.zb.entity.Wants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Wants DAO层
 *
 * @author lijiacheng
 * @version 1.0
 */
public interface WantsRepository extends JpaRepository<Wants, Long>, JpaSpecificationExecutor<Wants> {

}
