package com.mangajj.mangacontrol.gateway.repositories;

import com.mangajj.mangacontrol.entity.VolumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolumeRepository extends JpaRepository<VolumeEntity, Long> {
}
