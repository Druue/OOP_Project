package nl.tudelft.oopp.server.repositories;

import nl.tudelft.oopp.server.models.Details;
import nl.tudelft.oopp.server.models.TimeSlot;

public interface BuildingsDetails {

    Long getNumber();

    Details getDetails();

    TimeSlot getOpeningHours();
}
