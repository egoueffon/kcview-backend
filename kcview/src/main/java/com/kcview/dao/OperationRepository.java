package com.kcview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcview.entity.Club;
import com.kcview.entity.ObjectifMensuel;
import com.kcview.entity.Operation;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

	
}
