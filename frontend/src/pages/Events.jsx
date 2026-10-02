import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../api/axios";

function Events() {
  const [events, setEvents] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    api
      .get("/events")
      .then((res) => setEvents(res.data))
      .catch(() => setError("Could not load events"));
  }, []);

  return (
    <div>
      <h2>Sports Events</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <div style={{ display: "grid", gap: "1rem" }}>
        {events.map((event) => (
          <div key={event.id} style={{ border: "1px solid #ccc", padding: "1rem" }}>
            <h3>{event.name}</h3>
            <p>{event.sport} — {event.venue}</p>
            <p>{new Date(event.eventDate).toLocaleString()}</p>
            <p>₦{event.ticketPrice} per ticket · {event.availableTickets} left</p>
            <Link to={`/events/${event.id}`}>View Details</Link>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Events;