package com.group4.bankSystem.repository.CustomerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group4.bankSystem.entities.CustomerEntities.UserList;
import com.group4.bankSystem.entities.CustomerEntities.UserListId;

public interface UserListRepository extends JpaRepository<UserList, UserListId>{

    List<UserList> findByAccount_AccountId(Integer accountId);

    @Query("SELECT ul.account.accountId FROM UserList ul WHERE ul.customer.customerId = :customerId")
    List<Integer> findAccountIdsByCustomerId(@Param("customerId") Integer customerId);

    void deleteByCustomer_CustomerId(Integer customerId);

}
