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
import {getCampaignCountriesAction, getCitiesByStateAction, getStatesByCountryAction} from './campaignActions';
import {AppState} from "../../store/store";
import {connect, ResolveThunks, useDispatch} from "react-redux";
import {ApiCountry} from "../../api/models/ApiCountry";
import {ApiState} from "../../api/models/ApiState";
import {ApiCity} from "../../api/models/ApiCity";
import { setCities, setStates } from "./campaignReducers";
import Snackbar from '@material-ui/core/Snackbar';
import {Alert} from "@material-ui/lab";

interface CampaignEditViewProps {
  open: boolean;
  setOpen: (value: boolean) => void;
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

const CampaignEditView: React.FC<CampaignEditViewProps & CampaignEditProps> = (
  {
    open,
    setOpen,
    countries,
    getCountries,
    states,
    getStates,
    cities,
    getCities
  }) => {
  const classes = useStyles();
  const TIMEOUT = 3000;
  const handleClose = () => setOpen(false);
  const [ sbOpen, setSBOpen ] = useState(false);
  const handleSBClose = () => setSBOpen(false);

  const [selectedDate, setSelectedDate] = useState<Date | null>(
    new Date(),
  );

  const [uploads, setUploads] = useState<string[] | null>(null);
  const dispatch = useDispatch();

  useEffect(() => {
    if(!countries || countries.length === 0){
      getCountries();
    }
  }, [countries, getCountries]);

  const {register, handleSubmit, errors, setValue} = useForm({
    resolver: yupResolver(CreateCampaignFormSchema)
  });

  const onSubmit = (data: any) => {
    console.log(data);
    setSBOpen(true);
    setTimeout(() => {
      setOpen(false);
    }, TIMEOUT);
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

  const handleCountry = async (event: any, value: ApiCountry | null) => {
    if(value && value.id){
      await getStates(value.id)
    }
    else {
      dispatch(setStates([]));
      dispatch(setCities([]));
      setValue('state', '');
      setValue('city', '');
    }
  }

  const handleState = async (event: any, value: ApiState | null) => {
    if(value && value.id){
      await getCities(value.id)
    }
    else {
      dispatch(setCities([]));
      setValue('city', '');
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
          <Button autoFocus color="primary" variant='contained' size='large' type='submit'>
            Save
          </Button>
        </Toolbar>
        <Container maxWidth='sm'>
          <Typography variant="h3" className={classes.header}>
            Edit Campaign
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
              id="participant-number"
              label = "Total Number of Participants"
              fullWidth
              name = "participantNumber"
              inputRef = {register}
              />
              {
                errors && errors.title && (
                  <Typography color = {"error"}>
                    {errors.title?.message}
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
                  <TextField {...params} label="Country" margin="normal" name='country' inputRef={register}/>
                }
                onChange={handleCountry}
              />
            </Grid>
            <Grid item xs={1}>
              <LocationSearching />
            </Grid>
            <Grid item xs={5}>
              <Autocomplete
                options={states as ApiState[]}
                getOptionLabel={(state: ApiState) => `${state.name}`}
                id="state"
                renderInput={(params) =>
                  <TextField {...params} label="State" margin="normal" name='state' inputRef={register}/>
                }
                onChange={handleState}
              />
            </Grid>
          </Grid>
          <Grid container spacing={3}>
            <Grid item xs={1}>
              <LocationSearching />
            </Grid>
            <Grid item xs={5}>
              <Autocomplete
                options={cities as ApiCity[]}
                getOptionLabel={(country: ApiCity) => `${country.name}`}
                id="city"
                renderInput={(params) =>
                  <TextField {...params} label="City" margin="normal" name='city' inputRef={register}/>
                }
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
            uploads && uploads.map((url: string) => (
              <img
                src={url}
                alt="..."
                width="160"
                height="90"
                className='img-grid'/>)
            )
          }
          <Snackbar
            anchorOrigin={{ vertical: 'bottom', horizontal: 'left' }}
            open={sbOpen}
            onClose={handleSBClose}
            autoHideDuration={TIMEOUT}
          >
            <Alert onClose={handleSBClose} severity="success">
              Campaign submitted for review
            </Alert>
          </Snackbar>
        </Container>
      </form>
    </Dialog>
  )
}

const mapDispatchToProp = {
  getCountries: getCampaignCountriesAction,
  getStates: getStatesByCountryAction,
  getCities: getCitiesByStateAction
};

const mapStateToProps = ({ campaigns }: AppState) => ({
  countries: campaigns.countries,
  states: campaigns.states,
  cities: campaigns.cities
});

export type CampaignEditProps = ResolveThunks<typeof mapDispatchToProp> & ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps, mapDispatchToProp)(CampaignEditView);