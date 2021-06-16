import { Dispatch } from 'redux';
import { CampaignFilter, saveCampaigns, setCurrentCampaign, setCountries, setStates, setCities, setTotalCampaigns, updateCampaign, setFilters, setCurrentCampaignRemainingSeat } from './campaignReducers';
import { campaignApi } from "../../api/apis/CampaignsApi";
import {setAlert} from "../../components/Alert/AlertReducer";
import {ApiState} from "../../api/models/ApiState";

export const getCampaignsAction = (page = 0, size = 6, admin = false) => async (
  dispatch: Dispatch
) => {
  try {
    const response = await campaignApi.getCampaigns(page, size, admin);
    dispatch(saveCampaigns(response.data));
    dispatch(setTotalCampaigns(response.totalItems));
  } catch (e) {
    console.log(e);
  }
};

export const getCampaignAction = (id: number) => async (
  dispatch: Dispatch
) => {
  try {
    const response = await campaignApi.getCampaignById(id);
    dispatch(setCurrentCampaign(response));
  } catch (e) {
    console.log(e);
  }
};

export const getUserCampaignsAction = (page = 0, size = 6) => async (
  dispatch: Dispatch
) => {
  try {
    const response = await campaignApi.getCampaignByUserName(page, size);
    dispatch(saveCampaigns(response.data));
    dispatch(setTotalCampaigns(response.totalItems));
  } catch (e) {
    console.log(e);
  }
};

export const getCampaignCountriesAction = () => async (
  dispatch: Dispatch
) => {
  try {
    const response = await campaignApi.getCountries();
    if(response)
      dispatch(setCountries(response));
    else
      dispatch(setCountries([]));
  } catch (e) {
    dispatch(setCountries([]));
  }
}

export const getStatesByCountryAction = (countryId: number) => async (
  dispatch: Dispatch
): Promise<any> => {
  try {
    const response = await campaignApi.getStatesByCountry(countryId);
    if(response)
      dispatch(setStates(response));
    else
      dispatch(setStates([]));
    return response;
  } catch (e) {
    dispatch(setStates([]));
  }
}

export const getCitiesByStateAction = (stateId: number) => async (
  dispatch: Dispatch
): Promise<any> => {
  try {

    const response = await campaignApi.getCitiesByState(stateId);
    if (response)
      dispatch(setCities(response));
    else
      dispatch(setCities([]));
    return response;
  } catch (e) {
    dispatch(setCities([]));
  }
}

export const saveCampaignAction = (campaign: any) => async (
  dispatch: Dispatch
) => {
  try {
    await campaignApi.saveCampaign(campaign);
    dispatch(setAlert({
      message: "Campaign Saved Successfully",
      variant: "success"
    }));
  } catch (e) {
    console.log(e);
    dispatch(setAlert({
      message: e?.response?.data?.message || "Something went wrong",
      variant: "error"
    }));
  }
}

export const updateCampaignAction = (campaign: any) => async (
  dispatch: Dispatch
) => {
  try {
    await campaignApi.updateCampaign(campaign);
    dispatch(updateCampaign(campaign));
  } catch (e) {
    console.log(e);
  }
}


export const setFiltersAction = (filters: CampaignFilter) => async (dispatch: Dispatch) => {
  try {
    const response = await campaignApi.getFilterCampaign({
      location: filters.location,
      MinBudget: filters.MinBudget,
      MaxBudget: filters.MaxBudget,
      countryId: filters.countryId,
      stateId: filters.stateId,
      cityId: filters.cityId
    });

    if (response)
    {
      dispatch(saveCampaigns(response));

    } else {
      dispatch(saveCampaigns([]));

    }

    dispatch(setFilters(filters));
  }
  catch (e) {
    dispatch(saveCampaigns([]));
  }

}

export const joinCampaignAction = (joinCampaignData: any) => async (
  dispatch: Dispatch
) => {
  try {
    await campaignApi.joinCampaign(joinCampaignData);
    dispatch(setCurrentCampaignRemainingSeat());
    dispatch(setAlert({
      message: "Joined Campaign Successfully",
      variant: "success"
    }));
  } catch (e) {
    console.log(e);
    dispatch(setAlert({
      message: e?.response?.data?.message || "Something went wrong",
      variant: "error"
    }));
  }
}
export const deleteCampaignAction = (id: number) => async (
  dispatch: Dispatch
) => {
  try {
    const response = await campaignApi.deleteCampaign(id);
  } catch (e) {
    console.log(e);
  }
};

