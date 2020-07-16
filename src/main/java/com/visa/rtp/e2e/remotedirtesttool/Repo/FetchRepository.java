package com.visa.rtp.e2e.remotedirtesttool.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.visa.rtp.e2e.remotedirtesttool.Model.FetchResponseEntity;

public interface FetchRepository extends JpaRepository<FetchResponseEntity, Long>{

}
