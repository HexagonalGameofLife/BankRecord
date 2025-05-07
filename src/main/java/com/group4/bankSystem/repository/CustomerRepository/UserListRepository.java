package com.group4.bankSystem.repository.CustomerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group4.bankSystem.entities.AccountEntities.Account;
import com.group4.bankSystem.entities.CustomerEntities.UserList;
import com.group4.bankSystem.entities.CustomerEntities.UserListId;

public interface UserListRepository extends JpaRepository<UserList, UserListId>{

    List<UserList> findByAccount_AccountId(Integer accountId);

    void deleteByCustomer_CustomerId(Integer customerId);

    @Query("SELECT a FROM UserList u JOIN u.account a WHERE u.customer.customerId = :customerId")
    List<Account> findAccountsByCustomerId(@Param("customerId") Integer customerId);

    // 1) Tek üyeli hesaplardaki bu kullanıcının kaydını sil
    @Modifying
    @Query(value = """
        DELETE FROM user_list
         WHERE customer_id = :customerId
           AND account_id IN (
             SELECT account_id
               FROM user_list
              GROUP BY account_id
             HAVING COUNT(*) = 1
           )
        """, nativeQuery = true)
    int deleteSingleMemberEntries(@Param("customerId") Integer customerId);

    // 2) Primary’i silinen hesaplar için başka bir üyeyi primary yap
    @Modifying
    @Query(value = """
        UPDATE user_list ul1
           JOIN (
             SELECT account_id
               FROM user_list
              WHERE customer_id = :customerId
                AND is_primary_user = TRUE
           ) AS target
             ON ul1.account_id = target.account_id
            AND ul1.customer_id != :customerId
          SET ul1.is_primary_user = TRUE
         WHERE NOT EXISTS (
             SELECT 1 FROM user_list ul2
              WHERE ul2.account_id = ul1.account_id
                AND ul2.is_primary_user = TRUE
                AND ul2.customer_id != :customerId
           )
        """, nativeQuery = true)
    int promoteAnotherToPrimary(@Param("customerId") Integer customerId);

    // 3) Paylaşımlı (çok üyeli) hesaplardaki bu kullanıcının diğer kayıtlarını sil
    @Modifying
    @Query(value = """
        DELETE FROM user_list
         WHERE customer_id = :customerId
           AND account_id NOT IN (
             SELECT account_id
               FROM user_list
              GROUP BY account_id
             HAVING COUNT(*) = 1
           )
        """, nativeQuery = true)
    int deleteMultiMemberEntries(@Param("customerId") Integer customerId);

    // Mevcutsa: bu kullanıcıya ait hesapları çekmek için
    @Query("""
        SELECT ul.account.accountId
          FROM UserList ul
         WHERE ul.customer.customerId = :customerId
    """)
    List<Integer> findAccountIdsByCustomerId(@Param("customerId") Integer customerId);

    /**
     * Sadece belirli bir account–customer ilişkisini siler.
     */
    void deleteByAccount_AccountIdAndCustomer_CustomerId(Integer accountId, Integer customerId);


}
