import "./App.css";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import TopBar from "./components/topBar/TopBar";
import Login from "./pages/Login";
import Categories from "./pages/Categories";
import Books from "./pages/Books";
import Authors from './pages/Authors';

function App() {
  return (
    <div>
      <TopBar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/categories" element={<Categories />} />
        <Route path="/books" element={<Books />} />
        <Route path="/authors" element={<Authors />} />

      </Routes>
    </div>
  );
}

export default App;
