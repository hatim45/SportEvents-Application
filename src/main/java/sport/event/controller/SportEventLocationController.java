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
import sport.event.controller.model.SportEventData.SportEventAttendee;
import sport.event.controller.model.SportEventData.SportEventLocation;
import sport.event.service.SportEventService;

@RestController
@RequestMapping("/sport_event")
@Slf4j
public class SportEventLocationController {

	@Autowired
	private SportEventService sportEventService;
	
	@PostMapping("/location")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SportEventLocation addLocation(
			@RequestBody SportEventLocation sportEventLocation) {
		log.info("Creating Location () ", sportEventLocation);
		return sportEventService.saveLocation(sportEventLocation);
	}
	
}
