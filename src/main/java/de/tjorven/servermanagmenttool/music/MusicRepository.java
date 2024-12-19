package de.tjorven.servermanagmenttool.music;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Song, Long> {
}
