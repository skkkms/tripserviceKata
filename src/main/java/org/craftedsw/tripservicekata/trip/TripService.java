package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {

	@Autowired
	private TripDAO tripDAO;

	public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
		validate(loggedUser);

		return user.isFriendsWith(loggedUser) ? tripsByUser(user) : noTrips();
	}

	private void validate(User loggedUser) {
		if (loggedUser == null)
			throw new UserNotLoggedInException();
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	private List<Trip> tripsByUser(User user) {
		return tripDAO.tripsBy(user);
	}

}
