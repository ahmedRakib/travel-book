import React, { useEffect, useState } from 'react';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { BasicLayout } from '../layout/BasicLayout';
import { Fab, IconButton } from '@material-ui/core';
import { Add, Visibility } from '@material-ui/icons';
import { getCampaignsAction } from "./campaignActions";
import { connect, ResolveThunks } from "react-redux";
import { AppState, history } from "../../store/store";
import CampaignFilterView from './CampaignFilterView';
import CampaignCreateView from "./CampaignCreateView";
import { getImage } from "./common";
import {NotFound} from "../../components/NotFound";


const useStyles = makeStyles((theme) => ({
  icon: {
    marginRight: theme.spacing(2),
  },
  heroContent: {
    backgroundColor: theme.palette.background.paper,
    padding: theme.spacing(8, 0, 6),
  },
  heroButtons: {
    marginTop: theme.spacing(4),
  },
  cardGrid: {
    paddingTop: theme.spacing(8),
    paddingBottom: theme.spacing(8),
  },
  card: {
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
  },
  cardMedia: {
    paddingTop: '56.25%', // 16:9
  },
  cardContent: {
    flexGrow: 1,
  },
  footer: {
    backgroundColor: theme.palette.background.paper,
    padding: theme.spacing(6),
  },
  fab: {
    position: 'fixed',
    bottom: theme.spacing(5),
    right: theme.spacing(5),
  },
  title: {
    marginBottom: 50,
  },
}));

const CampaignsView: React.FC<Props> = ({ getCampaigns, campaigns, loggedIn }) => {
  const classes = useStyles();
  const [showCreateDialog, setShowCreateModal] = useState(false);
  const [currentCampaign, setCurrentCampaign] = useState(undefined);
  const fab = {
    color: 'primary' as 'primary',
    className: classes.fab,
    icon: <Add />,
    label: 'Add',
  };

  useEffect(() => {
    const get = async () => {
      await getCampaigns(0, 12);
    };

    get().then(result => console.log(result));
  }, [getCampaigns]);

  const handleAdd = () => {
    setCurrentCampaign(undefined);
    setShowCreateModal(true)
  }

  return (
    <>
      <BasicLayout>
        <CampaignFilterView />
        <Container className={classes.cardGrid} maxWidth="md">
          <Typography className={classes.title} variant="h2" color="inherit" noWrap>
            Campaigns
          </Typography>
          {
            campaigns && campaigns.length > 0 ? (
              <Grid container spacing={4}>
                {campaigns && campaigns.map((campaign) => (
                  <Grid item key={campaign.id} xs={12} sm={6} md={4}>
                    <Card className={classes.card}>
                      <CardMedia
                        className={classes.cardMedia}
                        image={getImage(campaign) || "https://source.unsplash.com/random"}
                        title={campaign.title}
                      />
                      <CardContent className={classes.cardContent}>
                        <Typography gutterBottom variant="h5" component="h2">
                          {campaign.title}
                        </Typography>
                        <Typography noWrap>
                          {campaign.description}
                        </Typography>
                      </CardContent>
                      {
                        loggedIn &&
                        <CardActions>
                          <IconButton
                            size="small"
                            color="primary"
                            onClick={() => history.push(`/campaigns/${campaign.id}`)}
                          >
                            <Visibility />
                          </IconButton>
                        </CardActions>
                      }
                    </Card>
                  </Grid>
                ))}
              </Grid>
            ) : (
                /*<Box m={5} textAlign="center">
                  <h2>No Campaign Found!</h2>
                </Box>*/
              <NotFound />

            )
          }
          {
            loggedIn && (
              <Fab aria-label={fab.label} className={fab.className} color={fab.color}
                   onClick={handleAdd}>
                {fab.icon}
              </Fab>
            )
          }
        </Container>
      </BasicLayout>
      {
        showCreateDialog && <CampaignCreateView open={showCreateDialog} setOpen={setShowCreateModal} campaign={currentCampaign} />
      }
    </>
  );
};

const mapDispatchToProps = {
  getCampaigns: getCampaignsAction
}

const mapStateToProps = ({ campaigns, auth }: AppState) => ({
  campaigns: campaigns.campaigns,
  loggedIn: auth.loggedIn
})

export type Props = ResolveThunks<typeof mapDispatchToProps> & ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps, mapDispatchToProps)(CampaignsView);