package sport.event.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sport.event.controller.model.SportEventData;
import sport.event.controller.model.SportEventData.SportEventLocation;
import sport.event.service.SportEventService;

@RestController
@RequestMapping("/sport_event")
@Slf4j
public class SportEventController {
	@Autowired
	private SportEventService sportEventService;
	
	@PostMapping("/{locationId}/sportEvent")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SportEventData saveSportEvent(
			@RequestBody SportEventData sportEventData, @PathVariable Long locationId) {
		log.info("Creating sportEventData{}", sportEventData);
		return sportEventService.saveSportEvent(sportEventData, locationId);
		
	}
	@PutMapping("/{locationId}/{sportEventId}")
	public SportEventData updateSportEvent(@PathVariable Long sportEventId,
			@RequestBody SportEventData sportEventData, @PathVariable Long locationId) {
		sportEventData.setSportEventId(sportEventId);
		log.info("Updating contributor {}", sportEventData);
		return sportEventService.saveSportEvent(sportEventData, locationId);
	}
	


@GetMapping("/{sportEventId}")
public SportEventData retrieveSportEventById(@PathVariable Long sportEventId) {
	log.info("Retrieving pet sportEvent{}", sportEventId);
	return sportEventService.retrieveSportEventById(sportEventId);
	
}


@GetMapping
public List<SportEventData> getAllSportEvents(){
	log.info("Retrieving all pet sportEvent");
	return sportEventService.retrieveAllSportEvents();
}

@DeleteMapping("/{sportEventId}")
public Map<String, String> deleteSportEventById(@PathVariable Long sportEventId){
	log.info("Deleting pet sportEvent{}", sportEventId);
	sportEventService.deleteSportEventById(sportEventId);
	return Collections.singletonMap("message", "Delete successful");
}

}