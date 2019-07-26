package com.everis.d4i.tutorial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.TvShowAwards;

@Repository
public interface TvShowAwardsRepository extends JpaRepository<TvShowAwards, Long> {
	
    List<TvShowAwards> findByTvShowId(Long tvShowId);
    
}
