package com.kry.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kry.assignment.entity.ServiceInfo;

@Repository
public interface ServiceInfoRepository extends JpaRepository<ServiceInfo, String> {

}
