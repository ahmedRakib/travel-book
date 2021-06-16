import React, {useEffect, useState} from 'react';
import {
  Button,
  Container,
  createStyles,
  Dialog,
  Grid,
  IconButton,
  TextField,
  Theme,
  Toolbar,
  Typography
} from "@material-ui/core";
import CloseIcon from '@material-ui/icons/Close';
import Slide from '@material-ui/core/Slide';
import {TransitionProps} from '@material-ui/core/transitions';
import {makeStyles} from "@material-ui/core/styles";
import {
  Place,
  TextFields,
  LibraryBooks,
  Image,
  Euro,
  Event,
  Schedule,
  Flag,
  LocationSearching,
  Person
} from "@material-ui/icons";
import Autocomplete from '@material-ui/lab/Autocomplete';
import LuxonUtils from '@date-io/luxon';
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from '@material-ui/pickers';
import './CampaignCreateView.css';
import {useForm} from "react-hook-form";
import {yupResolver} from '@hookform/resolvers/yup';
import {CreateCampaignFormSchema} from './validations';
import {
  getCampaignCountriesAction,
  getCitiesByStateAction,
  getStatesByCountryAction,
  saveCampaignAction
} from './campaignActions';
import {AppState} from "../../store/store";
import {connect, ResolveThunks, useDispatch} from "react-redux";
import {ApiCountry} from "../../api/models/ApiCountry";
import {ApiState} from "../../api/models/ApiState";
import {ApiCity} from "../../api/models/ApiCity";
import {setCities, setStates} from "./campaignReducers";
import {BASE_PATH} from "../../api";
import {campaignApi} from "../../api/apis/CampaignsApi";
import { TIMEOUT } from 'dns';
import { push } from 'connected-react-router';

interface CampaignCreateViewProps {
  open: boolean;
  setOpen: (value: boolean) => void;
  campaign?: any;
}

const Transition = React.forwardRef(function Transition(
  props: TransitionProps & { children?: React.ReactElement },
  ref: React.Ref<unknown>,
) {
  return <Slide direction="left" ref={ref} {...props} />;
});

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    title: {
      marginLeft: theme.spacing(2),
      flex: 1,
    },
    header: {
      marginBottom: theme.spacing(4)
    },
    extraMargin: {
      marginTop: theme.spacing(2)
    }
  }),
);

const CampaignCreateView: React.FC<CampaignCreateViewProps & CampaignCreateProps> = (
  {
    open,
    setOpen,
    countries,
    getCountries,
    states,
    getStates,
    cities,
    getCities,
    campaign,
    saveCampaign,
  }) => {
  const classes = useStyles();
  const handleClose = () => setOpen(false);
  const [country, setCountry] = useState<ApiCountry | null | undefined>(null);
  const [state, setState] = useState<ApiState | null | undefined>(null);
  const [city, setCity] = useState<ApiCity | null | undefined>(null);
  const [selectedDate, setSelectedDate] = useState<Date | null>(
    new Date(),
  );
  const {register, handleSubmit, errors, setValue, formState} = useForm({
    resolver: yupResolver(CreateCampaignFormSchema),
    defaultValues: campaign
  });
  const [uploads, setUploads] = useState<string[] | null>(null);
  const dispatch = useDispatch();


  useEffect(() => {
    if (!countries || countries.length === 0) {
      getCountries();
    }else{
      if(campaign){
        setSelectedDate(new Date(campaign.startTime))
      }
      if(campaign?.countriesId){
        const result = countries.find((country: ApiCountry) => country.id === campaign.countriesId);
        if(result){
          handleCountry(null, result);
        }

        if(campaign?.statesId){
          getStates(campaign.countriesId).then((result: ApiState[]) => {
            const state = result.find(x => x.id === campaign.statesId);
            if(state)
              setState(state)

            if(campaign?.citiesId){
              getCities(campaign.statesId).then((cities: ApiCity[]) => {
                const city = cities.find(x => x.id === campaign.citiesId);
                if(city)
                  setCity(city);
              })
            }
          });
        }
      }
    }
  }, [countries, getCountries]);

  useEffect(() => {
    if (campaign && campaign.campaignsImagesList && campaign.campaignsImagesList.length > 0) {
      const imageUrls = campaign.campaignsImagesList.map((item: any) => {
        return BASE_PATH.split("/api")[0].replace("3000", "8080") + item.image;
      })
      console.log(imageUrls);
      setUploads(imageUrls);
      setValue('images', null);
    }
  }, [campaign, setValue]);

  const onSubmit = async (data: any) => {
    let updatedData = {
      ...data,
      countriesId: country?.id,
      citiesId: city?.id,
      statesId: state?.id,
    }
    if(campaign && campaign.id){
      updatedData = {
        ...campaign,
        ...updatedData,
        id: campaign.id
      }
    }

    await saveCampaign(updatedData);
    setTimeout(() => {
      setOpen(false);
      dispatch(push("/user/dashboard"));
    }, 3000);
   
  }

  const handleDateChange = (date: any | null) => {
    setSelectedDate(date);
  };

  const handleUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event && event.target && event.target.files) {
      const images = Array.from(event.target.files).map((img: File) => URL.createObjectURL(img));
      setUploads(images);
    }
  }

  const handleCountry = (event: any, value: ApiCountry | null) => {
    if (value && value.id) {
      getStates(value.id)
      setCountry(value);
    } else {
      dispatch(setStates([]));
      dispatch(setCities([]));
      setValue('statesId', null);
      setValue('citiesId', null);
    }
  }

  const handleState = (event: any, value: ApiState | null) => {
    if (value && value.id) {
      getCities(value.id)
      setValue('statesId', value.id)
      setState(value)
    } else {
      dispatch(setCities([]));
      setValue('citiesId', null);
    }
  }

  const handleCity = (event: any, value: ApiCity | null) => {
    if (value && value.id) {
      setValue('citiesId', value.id)
      setCity(value)
    } else {
      dispatch(setCities([]));
      setValue('citiesId', null);
    }
  }

  return (
    <Dialog fullScreen open={open} onClose={handleClose} TransitionComponent={Transition}>
      <form noValidate onSubmit={handleSubmit(onSubmit)}>
        <Toolbar>
          <IconButton edge="end" color="secondary" onClick={handleClose} aria-label="close">
            <CloseIcon fontSize='large'/>
          </IconButton>
          <Typography variant="h6" className={classes.title}>

          </Typography>
          <Button id="button" autoFocus disabled={formState.isSubmitSuccessful || formState.isSubmitting} color="primary" variant='contained' size='large' type='submit'>
            Save
          </Button>
        </Toolbar>
        <Container maxWidth='sm'>
          <Typography variant="h3" className={classes.header}>
            {campaign ? 'Edit ' : 'New '}Campaign
          </Typography>
          <Grid container spacing={3}>
            <Grid item xs={1}>
              <TextFields/>
            </Grid>
            <Grid item xs={11}>
              <TextField
                id="title"
                label="Title"
                fullWidth
                name='title'
                inputRef={register}
              />
              {
                errors && errors.title && (
                  <Typography color={"error"}>
                    {errors.title?.message}
                  </Typography>
                )
              }
            </Grid>
          </Grid>
          <Grid container spacing={3}>
            <Grid item xs={1}>
              <Person/>
            </Grid>
            <Grid item xs = {11}>
              <TextField
              id="participantNumber"
              label = "Total Number of Participants"
              fullWidth
              name = "participantNumber"
              inputRef = {register}
              />
              {
                errors && errors.participantNumber && (
                  <Typography color = {"error"}>
                    {errors.participantNumber?.message}
                  </Typography>
                )
              }
            </Grid>
          </Grid>
          <Grid container spacing={3}>
            <Grid item xs={1}>
              <LibraryBooks/>
            </Grid>
            <Grid item xs={11}>
              <TextField
                id="description"
                label="Description"
                multiline
                rows={4}
                fullWidth
                name='description'
                inputRef={register}
              />
              {
                errors && errors.description && (
                  <Typography color={"error"}>
                    {errors.description?.message}
                  </Typography>
                )
              }
            </Grid>
          </Grid>
          <Grid container spacing={3}>
            <Grid item xs={1}>
              <Flag/>
            </Grid>
            <Grid item xs={5}>
              <Autocomplete
                options={countries as ApiCountry[]}
                getOptionLabel={(country: ApiCountry) => `${country.name} (${country.shortName})`}
                id="country"
                renderInput={(params) =>
                  <TextField {...params} label="Country" margin="normal" name='countriesId' inputRef={register}/>
                }
                onChange={handleCountry}
                value={country}
              />
            </Grid>
            <Grid item xs={1}>
              <LocationSearching/>
            </Grid>
            <Grid item xs={5}>
              <Autocomplete
                options={states as ApiState[]}
                getOptionLabel={(state: ApiState) => `${state.name}`}
                id="state"
                renderInput={(params) =>
                  <TextField {...params} label="State" margin="normal" name='statesId' inputRef={register}/>
                }
                onChange={handleState}
                value={state}
              />
            </Grid>
          </Grid>
          <Grid container spacing={3}>
            <Grid item xs={1}>
              <LocationSearching/>
            </Grid>
            <Grid item xs={5}>
              <Autocomplete
                options={cities as ApiCity[]}
                getOptionLabel={(city: ApiCity) => `${city.name}`}
                id="city"
                renderInput={(params) =>
                  <TextField {...params} label="City" margin="normal" name='citiesId' inputRef={register}/>
                }
                onChange={handleCity}
                value={city}
              />
            </Grid>
            <Grid item xs={1}>
              <Place/>
            </Grid>
            <Grid item xs={5} className={classes.extraMargin}>
              <TextField
                id="place"
                label="Place"
                fullWidth
                name='place'
                inputRef={register}
              />
              {
                errors && errors.place && (
                  <Typography color={"error"}>
                    {errors.place?.message}
                  </Typography>
                )
              }
            </Grid>
          </Grid>
          <MuiPickersUtilsProvider utils={LuxonUtils}>
            <Grid container spacing={3}>
              <Grid item xs={1}>
                <Event/>
              </Grid>
              <Grid item xs={5}>
                <KeyboardDatePicker
                  margin="normal"
                  id="date-picker-dialog"
                  label="Starting Date"
                  format="MM/dd/yyyy"
                  value={selectedDate}
                  onChange={handleDateChange}
                  KeyboardButtonProps={{
                    'aria-label': 'change date',
                  }}
                  name='startTime'
                  inputRef={register}
                />
                {
                  errors && errors.startTime && (
                    <Typography color={"error"}>
                      {errors.startTime?.message}
                    </Typography>
                  )
                }
              </Grid>
              <Grid item xs={1}>
                <Schedule/>
              </Grid>
              <Grid item xs={5} className={classes.extraMargin}>
                <TextField
                  id="standard-basic"
                  label="Duration in days"
                  fullWidth
                  name='duration'
                  inputRef={register}
                />
                {
                  errors && errors.duration && (
                    <Typography color={"error"}>
                      {errors.duration?.message}
                    </Typography>
                  )
                }
              </Grid>
            </Grid>
          </MuiPickersUtilsProvider>
          <Grid container spacing={3}>
            <Grid item xs={1}>
              <Euro/>
            </Grid>
            <Grid item xs={11}>
              <TextField
                id="standard-basic"
                label="Budget"
                fullWidth
                name={'budgets'}
                inputRef={register}
              />
              {
                errors && errors.budgets && (
                  <Typography color={"error"}>
                    {errors.budgets?.message}
                  </Typography>
                )
              }
            </Grid>
          </Grid>
          <Grid container spacing={3}>
            <Grid item xs={1}>
              <Image/>
            </Grid>
            <Grid item xs={11}>
              <input type='file' multiple onChange={handleUpload} name='images' ref={register}/>
              {
                errors && errors.images && (
                  <Typography color={"error"}>
                    {errors.images?.message}
                  </Typography>
                )
              }
            </Grid>
          </Grid>
          {
            uploads && uploads.map((url: string, index: number) => (
                <img
                  src={url}
                  alt="..."
                  width="160"
                  height="90"
                  className='img-grid'
                  key={index}
                />
              )
            )
          }
        </Container>
      </form>
    </Dialog>
  )
}

const mapDispatchToProp = {
  getCountries: getCampaignCountriesAction,
  getStates: getStatesByCountryAction,
  getCities: getCitiesByStateAction,
  saveCampaign: saveCampaignAction
};

const mapStateToProps = ({campaigns}: AppState) => ({
  countries: campaigns.countries,
  states: campaigns.states,
  cities: campaigns.cities
});

export type CampaignCreateProps = ResolveThunks<typeof mapDispatchToProp> & ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps, mapDispatchToProp)(CampaignCreateView);