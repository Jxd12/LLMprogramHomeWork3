package org.example.repository;

import org.example.entity.PO.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {
    Optional<UserPO> findByUsername(String username);

    @Query("SELECT u FROM UserPO u WHERE u.username = :username AND u.password = :password")
    Optional<UserPO> findByUsernameAndPassword(String username, String password);

    Boolean existsByUsername(String username);
}
