package com.rupesh.User_Service.Repository;

import com.rupesh.User_Service.Entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP , Long> {

    Optional<OTP> findByPhone(String phone);

    void deleteByPhone(String phone);
}
