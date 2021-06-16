import React, {useEffect, useState} from 'react';
import {BasicLayout} from '../layout/BasicLayout';
import {
  Container,
  makeStyles,
  Card,
  CardHeader,
  CardActionArea,
  CardMedia,
  CardContent,
  Typography,
  CardActions,
  Button, Grid, Icon
} from "@material-ui/core";
import {Euro, Event, Room} from "@material-ui/icons";
import {AppState} from "../../store/store";
import {getCampaignAction, joinCampaignAction} from "./campaignActions";
import {connect, ResolveThunks} from "react-redux";
import {useParams} from "react-router";
import {formatDate} from "../../utils";
import {getImage} from "./common";

const useStyles = makeStyles({
  root: {
    maxWidth: '100%',
    marginTop: 10,
    marginBottom: 20
  },
  media: {
    height: 350,
  },
});

const CampaignDetailsView: React.FC<Props> = ({getCampaign, campaign, joinCampaign}) => {
  const classes = useStyles();
  const [disabled, setDisabled] = useState(false);
  let {id} = useParams<{ id: string }>();
  useEffect(() => {
    const get = async () => {
      await getCampaign(parseInt(id));
    };

    get().then(result => console.log(result));
  }, [id, getCampaign]);


  const handleJoinRequest = async () => {
    if(campaign && campaign.id){
      const data = {
        campaignsId : campaign.id
      }
      console.log("hello inside", campaign);
      try{
        await joinCampaign(data);
        setDisabled(true);
      }catch(e){
        setDisabled(false);
      }
    }
  }

  return (
    <BasicLayout>
      <Container maxWidth="md">
        {
          campaign && (
            <Card className={classes.root}>
              {console.log(campaign)}
              <CardHeader
                title={campaign?.title}
                subheader={`Started from ${formatDate(campaign?.startTime as string)}`}
              />
              <CardActionArea>
                <CardMedia
                  className={classes.media}
                  image={getImage(campaign) || "https://source.unsplash.com/random"}
                  title="Contemplative Reptile"
                />
                <CardContent>
                  <Typography variant="body2" color="textSecondary" component="p">
                    {campaign?.description}
                  </Typography>
                </CardContent>
              </CardActionArea>
            <CardActionArea>
              <CardContent>
                <Typography variant="h4" color="textSecondary" component="p">
                  Only {campaign.remainingSeats} seats left.
                 </Typography>
              </CardContent>
              <CardActions>
                <Grid container justify="center">
                  <Button size="large" disabled = {disabled} color="primary" variant="contained" onClick={handleJoinRequest}>
                    Join This Campaign
                  </Button>
                </Grid>
              </CardActions>
            </CardActionArea>
              <CardContent>
                <Grid container spacing={4}>
                  <Grid item xs={12} sm={6} md={4}>
                    <Typography variant="h4" color="textSecondary" component="p">
                      Place
                    </Typography>
                    <Typography variant="body2" color="textSecondary" component="p">
                      <Icon><Room/></Icon> {campaign?.place}
                    </Typography>
                  </Grid>
                  <Grid item xs={12} sm={6} md={4}>
                    <Typography variant="h4" color="textSecondary" component="p">
                      Time
                    </Typography>
                    <Typography variant="body2" color="textSecondary" component="p">
                      <Icon><Event/></Icon> {formatDate(campaign?.startTime as string)}
                    </Typography>
                  </Grid>
                  <Grid item xs={12} sm={6} md={4}>
                    <Typography variant="h4" color="textSecondary" component="p">
                      Budget
                    </Typography>
                    <Typography variant="body2" color="textSecondary" component="p">
                      <Icon><Euro/></Icon> {campaign?.budgets}
                    </Typography>
                  </Grid>
                </Grid>
              </CardContent>
            </Card>
          )
        }
      </Container>
    </BasicLayout>
  )
};

const mapStateToProps = ({campaigns}: AppState) => ({
  campaign: campaigns.currentCampaign
});

const mapDispatchToProps = {
  getCampaign: getCampaignAction,
  joinCampaign: joinCampaignAction
}

export type Props = ResolveThunks<typeof mapDispatchToProps> & ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps, mapDispatchToProps)(CampaignDetailsView);
