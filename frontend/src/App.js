import "./App.css";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/home/Home";
import TopBar from "./components/TopBar";

function App() {
  return (
    <div>
      <TopBar />

      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
    </div>
  );
}

export default App;
