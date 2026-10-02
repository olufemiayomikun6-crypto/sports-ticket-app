import { Routes, Route, Link } from "react-router-dom";
import "./App.css";
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import Events from "./pages/Events";
import EventDetails from "./pages/EventDetails";
import MyBookings from "./pages/MyBookings";

function App() {
  return (
    <div>
      <nav>
        <Link to="/events">Events</Link>
        <Link to="/bookings">My Bookings</Link>
        <Link to="/login">Login</Link>
        <Link to="/signup">Sign Up</Link>
      </nav>

      <div className="page">
        <Routes>
          <Route path="/signup" element={<Signup />} />
          <Route path="/login" element={<Login />} />
          <Route path="/events" element={<Events />} />
          <Route path="/events/:id" element={<EventDetails />} />
          <Route path="/bookings" element={<MyBookings />} />
          <Route path="/" element={<Events />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;