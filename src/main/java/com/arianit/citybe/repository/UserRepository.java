package com.arianit.citybe.repository;

import com.arianit.citybe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
