import React, { useEffect, useState } from "react";
import CategoryCard from "../components/categoryCard/CategoryCard";
import axios from "axios";
import { Box } from "@mui/material";

export default function Categories() {
  const [categories, setCategories] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/categories")
      .then((res) => res.data)
      .then((res) => setCategories(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <Box sx={{ display: "flex", justifyContent: "center" }}>
      {categories.map((category) => (
        <CategoryCard key={category.id} category={category} />
      ))}
    </Box>
  );
}
