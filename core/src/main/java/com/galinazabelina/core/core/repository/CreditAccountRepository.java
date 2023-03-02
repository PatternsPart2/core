package com.galinazabelina.core.core.repository;

import com.galinazabelina.core.model.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditAccountRepository extends CrudRepository<Account, Long> {

    @Query("update Account set isClosed = false where id = :accountId")
    void closeAccountById(@Param("accountId") Long accountId);

    @Query("select balance from Account where id = :accountId")
    Optional<Long> getBalance(@Param("accountId") Long accountId);

    @Query("update Account set balance = balance + :balanceChange where id = :accountId")
    void changeBalance(@Param("accountId") Long accountId, @Param("balanceChange") Long balanceChange);
}
