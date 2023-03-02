package com.galinazabelina.core.core.repository;

import com.galinazabelina.core.model.entity.Account;
import com.galinazabelina.core.model.entity.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationsHistoryRepository extends CrudRepository<Operation, Long> {

    List<Operation> findAllByAccountId(Long accountId);
}
