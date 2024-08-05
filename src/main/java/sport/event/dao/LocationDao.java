package sport.event.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sport.event.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long>{

}
