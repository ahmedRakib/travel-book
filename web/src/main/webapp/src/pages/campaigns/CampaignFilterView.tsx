import React, { useEffect, useState } from 'react';

import {
  Button,
  Container,
  Grid,
  TextField
} from "@material-ui/core";
import Autocomplete from '@material-ui/lab/Autocomplete';
import { useForm } from "react-hook-form";
import { AppState } from "../../store/store";
import { connect, ResolveThunks, useDispatch } from 'react-redux';
import { setFiltersAction } from './campaignActions';
import { ApiCountry } from "../../api/models/ApiCountry";
import { ApiState } from "../../api/models/ApiState";
import { ApiCity } from "../../api/models/ApiCity";
import { setCities, setStates } from "./campaignReducers";
import Switch from '@material-ui/core/Switch';
import Collapse from '@material-ui/core/Collapse';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import { getCampaignCountriesAction, getCitiesByStateAction, getStatesByCountryAction } from './campaignActions';

const CampaignFilterView: React.FC<Props> = (
  {
    countries,
    getCountries,
    states,
    getStates,
    cities,
    getCities,
    setFilters }) => {

  const { register, handleSubmit, setValue } = useForm();
  const [Minvalue, setMinVal] = useState(1);
  const [Maxvalue, setMaxVal] = useState(999999999);
  const [country, setCountry] = useState<ApiCountry | null | undefined>(null);
  const [state, setState] = useState<ApiState | null | undefined>(null);
  const [city, setCity] = useState<ApiCity | null | undefined>(null);
  const dispatch = useDispatch();

  useEffect(() => {
    if (!countries || countries.length === 0) {
      getCountries();
    }
  }, []);

  const handleCountry = async (event: any, value: ApiCountry | null) => {
    if (value && value.id) {
      await getStates(value.id)
      setCountry(value)
    } else {
      dispatch(setStates([]));
      dispatch(setCities([]));
      setValue('state', null);
      setValue('city', null);
    }
  }

  const handleState = async (event: any, value: ApiState | null) => {
    if (value && value.id) {
      await getCities(value.id)
      setValue('state', value.id)
      setState(value)
    } else {
      dispatch(setCities([]));
      setValue('city', null);
    }
  }

  const handleCity = async (event: any, value: ApiCity | null) => {
    if (value && value.id) {
      setValue('city', value.id)
      setCity(value)
    } else {
      dispatch(setCities([]));
      setValue('city', null);
    }
  }

  const onChange1 = async (e: any) => {
    console.log("e.validity", e.target.validity.valid);
    if (e.target.validity.valid || e.target.value === "")
      setMinVal(e.target.value);
  };

  const onChange2 = async (e: any) => {
    console.log("e.validity", e.target.validity.valid);
    if (e.target.validity.valid || e.target.value === "")
      setMaxVal(e.target.value);
  };

  const onSubmit = async (data: any) => {
    debugger
    const campaignFIlter = {
      ...data,
      countryId: country?.id,
      stateId: state?.id,
      cityId: city?.id
    }
    await setFilters(campaignFIlter);
  }

  const [checked, setChecked] = React.useState(false);

  const handleChange = () => {
    setChecked((prev) => !prev);
  };

  return (

    <Container maxWidth="md">

      <FormControlLabel
        control={<Switch checked={checked} onChange={handleChange} />}
        label="Search Filters"
      />
      <Collapse in={checked}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <Grid container spacing={3}>
            <Grid item xs={12}>
              <TextField id="standard-basic" label="Location" name='location' inputRef={register} fullWidth />
            </Grid>
            <Grid item xs={12} md={6}>
              <TextField
                variant="standard"
                label="Min Budget"
                name="MinBudget"
                value={null}
                fullWidth
                onChange={onChange1}
                type="tel"
                inputRef={register}
                error={Minvalue < 1}
                helperText={Minvalue < 1 ? "This should not be less than 1" : ""}
                inputProps={{
                  id: 'MinBudget-native-label',
                  pattern: "[0-9]*"
                }}
              />
            </Grid>
            <Grid item xs={12} md={6}>
              <TextField
                variant="standard"
                label="Max Budget"
                name="MaxBudget"
                value={null}
                fullWidth
                onChange={onChange2}
                type="tel"
                inputRef={register}
                error={Maxvalue < Minvalue}
                helperText={Maxvalue < 1 || Minvalue ? "This should not be less than 1 or Minimum budget" : ""}

                inputProps={{
                  id: 'MaxBudget-native-label',
                  pattern: "[0-9]*"
                }}
              />
            </Grid>
            <Grid item xs={12} md={4}>
              <Autocomplete
                options={countries as ApiCountry[]}
                getOptionLabel={(country: ApiCountry) => `${country.name} (${country.shortName})`}
                id="country"
                renderInput={(params) =>
                  <TextField {...params} label="Country" margin="normal" name='country' inputRef={register} />
                }
                onChange={handleCountry}
                value={country}
              />
            </Grid>
            <Grid item xs={12} md={4}>
              <Autocomplete
                options={states as ApiState[]}
                getOptionLabel={(state: ApiState) => `${state.name}`}
                id="state"
                renderInput={(params) =>
                  <TextField {...params} label="State" margin="normal" name='state' inputRef={register} />
                }
                onChange={handleState}
                value={state}
              />
            </Grid>
            <Grid item xs={12} md={4}>
              <Autocomplete
                options={cities as ApiCity[]}
                getOptionLabel={(city: ApiCity) => `${city.name}`}
                id="city"
                renderInput={(params) =>
                  <TextField {...params} label="City" margin="normal" name='city' inputRef={register} />
                }
                onChange={handleCity}
                value={city}
              />
            </Grid>
            <Grid item xs={12}>
              <Button type="submit" variant="contained" color="primary">Search</Button>
            </Grid>
          </Grid>
        </form>
      </Collapse>
    </Container>

  )

};

const mapDispatchToProps =
{
  getCountries: getCampaignCountriesAction,
  getStates: getStatesByCountryAction,
  getCities: getCitiesByStateAction,
  setFilters: setFiltersAction
};

const mapStateToProps = ({ campaigns }: AppState) => ({
  countries: campaigns.countries,
  states: campaigns.states,
  cities: campaigns.cities
});

export type Props = ResolveThunks<typeof mapDispatchToProps> & ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps, mapDispatchToProps)(CampaignFilterView);
