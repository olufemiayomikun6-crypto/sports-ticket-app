import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../api/axios";

function EventDetails() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [event, setEvent] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [message, setMessage] = useState("");

  useEffect(() => {
    api
      .get(`/events/${id}`)
      .then((res) => setEvent(res.data))
      .catch(() => setMessage("Could not load event"));
  }, [id]);

  const handleBook = async () => {
    const token = localStorage.getItem("token");
    if (!token) {
      navigate("/login");
      return;
    }
    try {
      await api.post("/bookings", { eventId: id, quantity: Number(quantity) });
      setMessage("Booked successfully!");
    } catch (err) {
      setMessage(err.response?.data?.error || "Booking failed");
    }
  };

  if (!event) return <p>Loading...</p>;

  return (
    <div>
      <h2>{event.name}</h2>
      <p>{event.description}</p>
      <p>{event.sport} — {event.venue}</p>
      <p>{new Date(event.eventDate).toLocaleString()}</p>
      <p>₦{event.ticketPrice} per ticket · {event.availableTickets} available</p>

      <label>
        Quantity:
        <input
          type="number"
          min="1"
          max={event.availableTickets}
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
        />
      </label>
      <button onClick={handleBook}>Book Ticket</button>

      {message && <p>{message}</p>}
    </div>
  );
}

export default EventDetails;
