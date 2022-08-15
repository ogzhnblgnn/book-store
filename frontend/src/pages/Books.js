import React, { useEffect, useState } from "react";
import BookCard from "../components/bookCard/BookCard";
import axios from "axios";
import { Grid } from "@mui/material";

export default function Books() {
  const [books, setBooks] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/books")
      .then((res) => res.data)
      .then((res) => setBooks(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      <Grid sx={{ mt: 3, padding: 2 }} container spacing={3}>
        {books?.map((book) => (
          <Grid spacing={3} item xs={8} md={5} lg={4}>
            <BookCard key={book.id} book={book} />
          </Grid>
        ))}
      </Grid>
    </div>
  );
}
