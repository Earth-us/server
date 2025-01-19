package com.greenity.server.user.repository;

import com.greenity.server.user.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull
    Optional<User> findById(@NonNull Long id);

    Optional<User> findByEmail(String email);

    void deleteById(@NonNull Long id);
}