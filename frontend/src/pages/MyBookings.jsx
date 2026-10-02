import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";

function MyBookings() {
  const [bookings, setBookings] = useState([]);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      navigate("/login");
      return;
    }
    api
      .get("/bookings/my")
      .then((res) => setBookings(res.data))
      .catch(() => setError("Could not load your bookings"));
  }, [navigate]);

  return (
    <div>
      <h2>My Booked Tickets</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {bookings.length === 0 && !error && <p>You haven't booked any tickets yet.</p>}
      <div style={{ display: "grid", gap: "1rem" }}>
        {bookings.map((booking) => (
          <div key={booking.id} style={{ border: "1px solid #ccc", padding: "1rem" }}>
            <p>Event ID: {booking.eventId}</p>
            <p>Quantity: {booking.quantity}</p>
            <p>Total Paid: ₦{booking.totalPrice}</p>
            <p>Booked on: {new Date(booking.bookingDate).toLocaleString()}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default MyBookings;