package com.faiez.repository;

import com.faiez.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by faiez.elleuch on 19/02/14.
 */
public interface PetsRepository extends JpaRepository<Pet,Integer> {
}
