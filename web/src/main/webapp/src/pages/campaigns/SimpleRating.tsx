import React, { useState } from "react";
import Rating from "@material-ui/lab/Rating";
import {
  Box,
  Button,
  Container,
  TextareaAutosize,
  Typography,
} from "@material-ui/core";

type valueType = {
  starCount: number;
  comment: string;
};

export default function SimpleRating() {
  const [value, setValue] = useState<valueType>({
    starCount: 1,
    comment: "",
  });
  const handleSubmit = (event: React.ChangeEvent<{}>) => {
    // send db code from here
  };

  return (
    <Container>
      <form onSubmit={handleSubmit}>
        <Box>
          <Typography component="legend">Star Rating</Typography>
          <Rating
            name="simple-controlled"
            value={value.starCount ?? 1}
            onChange={() => setValue({...value, starCount: value.starCount})}
          />
        </Box>
        <br />
        <Typography component="legend">Write your comment</Typography>
        <TextareaAutosize
          aria-label="minimum height"
          rowsMin={5}
          placeholder="Write your comment"
          onChange={() => setValue({...value, comment: value.comment})}
        />
        <br />
        <Button variant="contained" color="primary">
          Submit
        </Button>
      </form>
    </Container>
  );
}
