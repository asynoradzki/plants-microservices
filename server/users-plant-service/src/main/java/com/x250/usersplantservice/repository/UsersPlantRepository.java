package com.x250.usersplantservice.repository;

import com.x250.usersplantservice.model.UsersPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsersPlantRepository extends JpaRepository<UsersPlant, Long> {

    List<UsersPlant> findByNextWateringIsBefore(LocalDateTime currentTime);
    List<UsersPlant> findByAppUserId(String id);

}
