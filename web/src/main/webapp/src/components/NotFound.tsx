import React from "react";
import {Box, Grid} from "@material-ui/core";
import {ReactComponent as NotFoundSVG} from "../assets/not-found.svg";

export const NotFound: React.FC = () => {
  return (
    <Box m={5} textAlign="center">
      <NotFoundSVG style={{
        height: '25rem',
        width: '25rem',
      }}/>
    </Box>
  )
}