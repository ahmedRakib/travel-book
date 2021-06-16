import React from 'react';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Link from '@material-ui/core/Link';

const useStyles = makeStyles((theme) => ({
  icon: {
    marginRight: theme.spacing(2),
  },
  footer: {
    backgroundColor: theme.palette.background.paper,
    padding: theme.spacing(6),
  },
}));

const Copyright = () => {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="/">
        Travel Book
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

export const Footer: React.FC = () => {
  const classes = useStyles();

  return (
    <footer className={classes.footer} >
      <Typography variant="h6" align="center" gutterBottom>
        Footer
      </Typography>
      {/* TODO: Will add Something later */}
      {/* <Typography variant="subtitle1" align="center" color="textSecondary" component="p">
        Something here to give the footer a purpose!
      </Typography> */}
      <Copyright />
    </footer >
  );
}