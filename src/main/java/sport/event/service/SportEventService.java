package sport.event.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sport.event.controller.model.SportEventData;
import sport.event.controller.model.SportEventData.SportEventAttendee;
import sport.event.controller.model.SportEventData.SportEventLocation;
import sport.event.dao.AttendeeDao;
import sport.event.dao.LocationDao;
import sport.event.dao.SportEventDao;
import sport.event.entity.Attendee;
import sport.event.entity.Location;
import sport.event.entity.SportEvent;

@Service
public class SportEventService {
	
@Autowired
private SportEventDao sportEventDao;
@Autowired
private AttendeeDao attendeeDao;
@Autowired
private LocationDao locationDao;


@Transactional(readOnly = false)
public SportEventData saveSportEvent(SportEventData sportEventData, Long locationId) {
	Location location = findLocationById(locationId);
	Long sportEventId = sportEventData.getSportEventId();
	SportEvent sportEvent = findOrCreateSportEventById(sportEventId, locationId);
	
	sportEvent.setLocation(location);
	location.getSportEvents().add(sportEvent);
	
	copySportEventFields(sportEvent, sportEventData);
	return new SportEventData(sportEventDao.save(sportEvent));	
}

private void copySportEventFields(SportEvent sportEvent, SportEventData sportEventData) {
	sportEvent.setSportEventName(sportEventData.getSportEventName());
	sportEvent.setSportEventDate(sportEventData.getSportEventDate());
	sportEvent.setSportEventSport(sportEventData.getSportEventSport());

}

private SportEvent findOrCreateSportEventById(Long sportEventId, Long locationId) {
	SportEvent sportEvent;
	
	if(Objects.isNull(sportEventId)) {
		sportEvent = new SportEvent();
	}
	else {
		sportEvent = findSportEventDataById(sportEventId, locationId);
	}
	return sportEvent;
}

private SportEvent findSportEventDataById(Long sportEventId, Long locationId) {
	SportEvent sportEvent = sportEventDao.findById(sportEventId).orElseThrow(() -> new NoSuchElementException("SportEvent with ID =" + sportEventId + " was not found"));

	if(!sportEvent.getLocation().getLocationId().equals(locationId)) {
		throw new IllegalArgumentException("SportEvent with the ID = " + sportEventId +" not a member of the pet store with the ID");
	}

	return sportEvent;
}


@Transactional(readOnly = false)
public SportEventAttendee saveAttendee(Long sportEventId, SportEventAttendee sportEventAttendee) {
	SportEvent sportEvent = findSportEventDataById(sportEventId);
	Long attendeeId = sportEventAttendee.getAttendeeId();
	Attendee attendee = findOrCreateAttendee(sportEventId, attendeeId);
	
	copyAttendeeFields(attendee, sportEventAttendee);
	
	attendee.getSportEvents().add(sportEvent);
	sportEvent.getAttendees().add(attendee);
	
	return new SportEventAttendee(attendeeDao.save(attendee));
}

private SportEvent findSportEventDataById(Long sportEventId) {
return  sportEventDao.findById(sportEventId).orElseThrow(() -> new NoSuchElementException("SportEvent with ID =" + sportEventId + " was not found"));
}

private Attendee findOrCreateAttendee(Long sportEventId, Long attendeeId) {
if (Objects.isNull(attendeeId)) {
	return new Attendee();
	}
else {
	return findAttendeeById(sportEventId, attendeeId);
}
}

private Attendee findAttendeeById(Long sportEventId, Long attendeeId) {
	Attendee attendee = attendeeDao.findById(attendeeId).orElseThrow(() -> new NoSuchElementException("Attendee with ID =" + attendeeId + " was not found"));
	boolean found = false;
	
	for(SportEvent sportEvent : attendee.getSportEvents()) {
			
			if (sportEvent.getSportEventId() == sportEventId) {
				found = true;
				break;	
			}
		}
		
		if ( !found ){
			throw new IllegalArgumentException("Attendee with the ID = " + attendeeId +" is not attending");
		}

		return attendee;
	}



private void copyAttendeeFields(Attendee attendee, SportEventAttendee sportEventAttendee) {
	attendee.setAttendeeAge(sportEventAttendee.getAttendeeAge());
	attendee.setAttendeeEmail(sportEventAttendee.getAttendeeEmail());
	attendee.setAttendeeName(sportEventAttendee.getAttendeeName());
	attendee.setAttendeeRole(sportEventAttendee.getAttendeeRole());

}

@Transactional(readOnly = false)
public SportEventLocation saveLocation( SportEventLocation sportEventLocation) {
	Long locationId = sportEventLocation.getLocationId();
	Location location = findOrCreateLocation(locationId);
	
	copyLocationFields(location, sportEventLocation);
	
	return new SportEventLocation(locationDao.save(location));
	
}

private void copyLocationFields(Location location, SportEventLocation sportEventLocation) {
		location.setLocationAddress(sportEventLocation.getLocationAddress());
		location.setLocationName(sportEventLocation.getLocationName());
		location.setLocationType(sportEventLocation.getLocationType());
}

private Location findOrCreateLocation(Long locationId) {		
	if(Objects.isNull(locationId)) {
		return new Location();
	}
	else {
		return findLocationById(locationId);
	}
}

private Location findLocationById(Long locationId) {
return locationDao.findById(locationId).orElseThrow(() -> new NoSuchElementException( "Location with ID " + locationId + " was not found"));
	

}
@Transactional(readOnly = true)
public List<SportEventData> retrieveAllSportEvents() {
	List<SportEvent> sportEvents = sportEventDao.findAll();
	List<SportEventData> sportEventDataList = new LinkedList<>();
	for (SportEvent sportEvent : sportEvents) {
		SportEventData data = new SportEventData(sportEvent);
		data.setAttendees(Collections.emptySet());
		sportEventDataList.add(data);
		}
		
		return sportEventDataList;
}


@Transactional
public SportEventData retrieveSportEventById(Long sportEventId) {
	SportEvent sportEvent = findSportEventDataById(sportEventId);
	
	return new SportEventData(sportEvent);
	
}

public void deleteSportEventById(Long sportEventId) {
	SportEvent sportEvent = findSportEventDataById(sportEventId);
	if (sportEvent != null) {
		sportEventDao.delete(sportEvent);
	} else {
		throw new NoSuchElementException("SportEvent with ID " + sportEventId + " was not found");
	}
}



	















}
