/*package com.group4.bankSystem.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group4.bankSystem.entities.AccountEntities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

    @Query("SELECT a.accountId FROM Account a JOIN a.userList ul WHERE ul.customer.customerId = :customerId")
    List<Integer> findAccountIdsByCustomerId(@Param("customerId") Integer customerId);

    @Query("SELECT DISTINCT a FROM Account a JOIN a.userList ul WHERE ul.customer.customerId = :customerId")
    List<Account> findAllByCustomerId(@Param("customerId") Long customerId);

    
    

    Optional<Account> findByIban(String iban);

    //zayimmmmmmmmmmmmmmmmmmmpublic List<Account> findByCustomerId(Long id);
    @Query("SELECT a FROM Account a WHERE a.customer.customerId = :customerId")
    List<Account> findByCustomerId(@Param("customerId") Long customerId);

}*/

package com.group4.bankSystem.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group4.bankSystem.entities.AccountEntities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a.accountId FROM Account a JOIN a.userList ul WHERE ul.customer.customerId = :customerId")
    List<Integer> findAccountIdsByCustomerId(@Param("customerId") Integer customerId);

    @Query("SELECT DISTINCT a FROM Account a JOIN a.userList ul WHERE ul.customer.customerId = :customerId")
    List<Account> findAllByCustomerId(@Param("customerId") Long customerId);

    Optional<Account> findByIban(String iban);
}

