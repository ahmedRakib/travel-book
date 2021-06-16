import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
  root: {
    minWidth: 275,
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
});

export interface InfoCardProp {
  title: string;
  subTitle?: string;
  count: number;
}

export const InfoCard: React.FC<InfoCardProp> = ({ title, subTitle, count = 0 }) => {
  const classes = useStyles();
  return (
    <Card className={classes.root}>
      <CardContent>
        <Typography variant="h5" component="h2">
          {title}
        </Typography>
        <Typography className={classes.pos} color="textSecondary">
          {subTitle}
        </Typography>
        <Typography variant="body2" component="p">
          {count}
        </Typography>
      </CardContent>
    </Card>
  )
}