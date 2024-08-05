package sport.event.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sport.event.entity.SportEvent;

public interface SportEventDao extends JpaRepository<SportEvent, Long>{

}
