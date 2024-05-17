package tech.buildrun.poi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.buildrun.poi.entity.PointOfInterest;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

        
        @Query(value = """
                SELECT p.* FROM tb_points_of_interest p
                WHERE SQRT(POWER((p.x - :refX), 2) + POWER((p.y - :refY), 2)) <= :dMax
                """,
                nativeQuery = true)
        List<PointOfInterest> findNearMe(@Param("refX") long refX,
                        @Param("refY") long refY,
                        @Param("dMax") long dMax);
}
