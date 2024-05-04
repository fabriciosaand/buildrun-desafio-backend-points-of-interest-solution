package tech.buildrun.gps.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.gps.controller.dto.CreatePOI;
import tech.buildrun.gps.entity.PointOfInterest;
import tech.buildrun.gps.repository.PointOfInterestRepository;

import java.awt.geom.Point2D;
import java.util.List;

@RestController
public class POIController {

    private final PointOfInterestRepository repository;

    public POIController(PointOfInterestRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/points-of-interests")
    public ResponseEntity<Void> create(@RequestBody CreatePOI createPOI) {

        repository.save(new PointOfInterest(null, createPOI.name(), createPOI.x(), createPOI.y()));

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/points-of-interests")
    public ResponseEntity<Page<PointOfInterest>> list(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) {

        var body = repository.findAll(PageRequest.of(page, pageSize));

        return ResponseEntity.ok(body);
    }

    @GetMapping(value = "/near-me")
    public ResponseEntity<List<PointOfInterest>> list(@RequestParam(name = "x") Long x,
                                                      @RequestParam(name = "y") Long y,
                                                      @RequestParam(name = "dmax") Long dmax) {

        var xMax = x + dmax;
        var xMin = x - dmax;
        var yMax = y + dmax;
        var yMin = y - dmax;

        var body = repository.findAllNearMe(xMin, xMax, yMin, yMax)
                .stream()
                .filter(p -> distanceBetweenPoints(p.getX(), p.getY(), x, y) <= dmax)
                .toList();


        return ResponseEntity.ok(body);
    }

    public Double distanceBetweenPoints(Long x1, Long y1, Long x2, Long y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
