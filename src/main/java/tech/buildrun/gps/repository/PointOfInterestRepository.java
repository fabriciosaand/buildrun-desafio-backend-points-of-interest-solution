package tech.buildrun.gps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.buildrun.gps.entity.PointOfInterest;

import java.util.List;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {


    @Query("""
        SELECT p FROM PointOfInterest p
        WHERE (p.x >= :xMin and p.x <= :xMax and p.y >= :yMin and p.y <= :yMax)
    """)
    List<PointOfInterest> findAllNearMe(@Param("xMin") Long xMin,
                                        @Param("xMax") Long xMax,
                                        @Param("yMin") Long yMin,
                                        @Param("yMax") Long yMax);

}
