package org.example._4springdatajparevison.repository;

import org.example._4springdatajparevison.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Long> {
}
