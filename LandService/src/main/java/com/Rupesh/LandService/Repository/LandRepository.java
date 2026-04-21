package com.Rupesh.LandService.Repository;

import com.Rupesh.LandService.Entity.Land;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface LandRepository extends JpaRepository<Land , Long> {

    Optional<List<Land>> findByOwnerId(Long ownerId);
    List<Land> findByActiveTrue();
    List<Land> findByOwnerIdAndActiveTrue(Long ownerId);
}
