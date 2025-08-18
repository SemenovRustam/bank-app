package com.semenov.account.repository;

import com.semenov.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByLogin(String login);

    void deleteAllByLogin(String login);
}
