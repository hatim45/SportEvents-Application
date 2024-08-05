package sport.event.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import sport.event.entity.Attendee;
import sport.event.entity.Location;
import sport.event.entity.SportEvent;


@Data
@NoArgsConstructor
public class SportEventData {

	private Long sportEventId;
	private String sportEventName;
	private String sportEventSport;
	private String sportEventDate;
	
	private Set<SportEventAttendee> attendees = new HashSet<>();
	private Set<SportEvent> sportEvents = new HashSet<>();

	public SportEventData(SportEvent sportEvent) {
		sportEventId = sportEvent.getSportEventId();
		sportEventDate = sportEvent.getSportEventDate();
		sportEventName = sportEvent.getSportEventName();
		sportEventSport = sportEvent.getSportEventSport();
		
		for(Attendee attendee : sportEvent.getAttendees()) {
			attendees.add(new SportEventAttendee(attendee));
		}
		
	
	}
		
	
	@Data
	@NoArgsConstructor
	public static class SportEventAttendee{
		private Long attendeeId;
		private String attendeeName;
		private String attendeeAge;
		private String attendeeEmail;
		private String attendeeRole;	
		
		public SportEventAttendee(Attendee attendee) {
			attendeeId = attendee.getAttendeeId();
			attendeeName = attendee.getAttendeeName();
			attendeeAge = attendee.getAttendeeAge();
			attendeeEmail = attendee.getAttendeeEmail();
			attendeeRole = attendee.getAttendeeRole();
			
			}	
		}
	
	@Data
	@NoArgsConstructor
	public static class SportEventLocation{
		private Long locationId;
		private String locationName;
		private String locationAddress;
		private String locationType;
		
		private Set<SportEventData> sportEvents = new HashSet<SportEventData>();

		public SportEventLocation(Location location) {
			locationId = location.getLocationId();
			locationName = location.getLocationName();
			locationAddress = location.getLocationAddress();
			locationType = location.getLocationType();
		
			for(SportEvent sportEvent : location.getSportEvents()) {
				sportEvents.add(new SportEventData(sportEvent)); 
			}
		
		}
		
	}
	
}
