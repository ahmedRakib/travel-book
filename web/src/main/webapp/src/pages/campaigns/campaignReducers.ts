import {createSlice, PayloadAction} from '@reduxjs/toolkit';
import {ApiCountry} from "../../api/models/ApiCountry";
import {ApiState} from "../../api/models/ApiState";
import {ApiCity} from "../../api/models/ApiCity";

export interface CampaignFilter {
  location: string;
  MinBudget:number;
  MaxBudget:number;
  countryId: number;
  stateId: number;
  cityId: number;
}

export interface CampaignsState {
  campaigns: any[];
  myCampaigns: any[];
  currentCampaign?: any;
  filters?:CampaignFilter;
  countries?: ApiCountry[];
  states?: ApiState[];
  cities?: ApiCity[];
  totalCampaigns: number;
}

const initialState: CampaignsState = {
  campaigns: [],
  myCampaigns: [],
  totalCampaigns: 0
};

const campaignSlice = createSlice({
  name: 'campaign',
  initialState,
  reducers: {
    saveCampaigns: (
      state: CampaignsState,
      action: any
    ) => {
      if (action.payload) {
        state.campaigns = action.payload;
      }
    },
    setCurrentCampaign: (state: CampaignsState, action: PayloadAction<any>) => {
      if(action.payload){
        state.currentCampaign = action.payload;
      }
    },
    setCountries: (state: CampaignsState, action: PayloadAction<ApiCountry[]>) => {
      if(action.payload){
        state.countries = action.payload;
      }
    },
    setStates: (state: CampaignsState, action: PayloadAction<ApiState[]>) => {
      if(action.payload){
        state.states = action.payload;
      }
    },
    setCities: (state: CampaignsState, action: PayloadAction<ApiCity[]>) => {
      if(action.payload){
        state.cities = action.payload;
      }
    },
    setTotalCampaigns: (state: CampaignsState, action: PayloadAction<number>) => {
      state.totalCampaigns = action.payload;
    },
    updateCampaign: (state: CampaignsState, action: PayloadAction<any>) => {
      const campaigns = state.campaigns.filter((campaign: any) => campaign.id !== action.payload.id);
      state.campaigns = [
        ...campaigns,
        action.payload
      ]
    },
    setFilters: (state: CampaignsState, action: any) => {
      if(action.payload){
        state.filters = action.payload;
      }
    },
    setCurrentCampaignRemainingSeat: (state: CampaignsState) => {
      state.currentCampaign.remainingSeats -= 1;
    }
  },
});

const { actions, reducer } = campaignSlice;

export const { saveCampaigns, setCurrentCampaign, setCountries, setStates, setCities, setTotalCampaigns, updateCampaign, setFilters, setCurrentCampaignRemainingSeat } = actions;

export default reducer;
