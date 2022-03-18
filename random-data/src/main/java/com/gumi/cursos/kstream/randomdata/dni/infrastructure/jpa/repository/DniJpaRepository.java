package com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.repository;

import com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.entity.DniEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DniJpaRepository extends CrudRepository<DniEntity, Long> {

    Optional<DniEntity> findByNumberAndCharacter(Integer number, String character);

    List<DniEntity> findAllByUsedFalse();

    @Modifying
    @Query("Update DniEntity de set de.used = true where de.id = :id")
    Integer markAsUsed(@Param("id") Long id);
}
