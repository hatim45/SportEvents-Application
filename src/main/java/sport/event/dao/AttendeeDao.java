package sport.event.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sport.event.entity.Attendee;

public interface AttendeeDao extends JpaRepository<Attendee, Long>{

}
