import axios from 'axios';
import {BASE_PATH} from "../runtime";
import {ApiCountry} from "../models/ApiCountry";
import {asyncLocalStorage} from "../../utils";
import {PASSWORD_KEY, USERNAME_KEY} from "../../pages/auth/const";

export class CampaignsApi {
  async getCountries(): Promise<Array<ApiCountry> | undefined>{
    try {
      const result = await axios.get(`${BASE_PATH}/countries`);
      return result.data.data;
    }catch (e){
      console.log(e);
    }
  }

  async getStatesByCountry(countryId: number) {
    try {
      const result = await axios.get(`${BASE_PATH}/states/countries/${countryId}`);
      return result.data.data;
    }catch (e){
      console.log(e);
    }
  }

  async getCitiesByState(stateId: number) {
    try {
      const result = await axios.get(`${BASE_PATH}/cities/states/${stateId}`);
      return result.data.data;
    }catch (e){
      console.log(e);
    }
  }

  async getCampaigns(page: number, size: number, admin = false) {
    try {
      let apiPath = `${BASE_PATH}/campaigns?page=${page}&size=${size}`
      if(admin){
        apiPath += `&campaignsStatusId=-1&campaignsApprovalStatusId=-1`
      }
      const result = await axios.get(apiPath);
      return result.data;
    }catch (e){
      console.log(e);
    }
  }

  async getCampaignByUserName(page: number, size: number) {
    try {
      let apiPath = `${BASE_PATH}/user-campaigns?page=${page}&size=${size}`

      const result = await axios.get(apiPath, {
        headers: {
          "Authorization": "Basic " + btoa(await asyncLocalStorage.getItem(USERNAME_KEY)+ ":" + await asyncLocalStorage.getItem(PASSWORD_KEY)),
        }
      });
      return result.data;
    }catch (e){
      console.log(e);
    }
  }


  async getCampaignById(id: number) {
    try {
      let apiPath = `${BASE_PATH}/campaigns/${id}`;
      const result = await axios.get(apiPath);
      return result.data.data;
    }catch (e){
      console.log(e);
    }
  }

  async saveCampaign(campaign: any): Promise<any> {
    try {
      let data = this.createFormData(campaign);
      const result = await axios.post(`${BASE_PATH}/campaigns`, data, {
        headers: {
          "Authorization": "Basic " + btoa(await asyncLocalStorage.getItem(USERNAME_KEY)+ ":" + await asyncLocalStorage.getItem(PASSWORD_KEY)),
        }
      });

      console.log(result);
      return result;
    }catch (e) {
      console.log(e);
    }
  }

  async joinCampaign(joinCampaign: any): Promise<any> {
    try {
      const response =  await axios.post(`${BASE_PATH}/campaigns-participants`, joinCampaign, {
        headers: {
          "Authorization": "Basic " + btoa(await asyncLocalStorage.getItem(USERNAME_KEY) + ":" + await asyncLocalStorage.getItem(PASSWORD_KEY)),
        }
      });
      return response.data.data;
    }catch (e) {
      throw e;
    }
  }

  createFormData(campaign: any): FormData {
    let formData = new FormData();
    Array.from(campaign.images).forEach((img: any) => formData.append('campaignsImagesList', img as any));
    delete campaign.images;
    delete campaign.campaignsImagesList;
    if(!campaign.countriesId || campaign.countriesId === ""){
      delete campaign.countriesId;
    }
    if(!campaign.statesId || campaign.statesId === ""){
      delete campaign.statesId;
    }
    if(!campaign.citiesId || campaign.citiesId === ""){
      delete campaign.citiesId;
    }

    formData.append('campaigns', new Blob([JSON.stringify(campaign)], {
      type: "application/json"
    }));
    return formData;
  }

  getFormattedDate(date: Date) {
    let year = date.getFullYear();
    let month = (1 + date.getMonth()).toString().padStart(2, '0');
    let day = date.getDate().toString().padStart(2, '0');

    return year + '-' + month + '-' + day;
  }

  async updateCampaign(campaign: any): Promise<any> {
    try {
      const id = campaign.id;
      if(!id){
        throw new Error("Campaign ID does not exist");
      }
      const result = await axios.put(`${BASE_PATH}/campaigns/${id}`, campaign, {
        headers: {
          "Authorization": "Basic " + btoa(await asyncLocalStorage.getItem(USERNAME_KEY)+ ":" + await asyncLocalStorage.getItem(PASSWORD_KEY)),
        }
      });

      console.log(result);
      return result;
    }catch (e) {
      console.log(e);
    }
  }

  async getFilterCampaign(filters: any) {
    try
    {
      const params = {
        place: filters.location === "" ? null : filters.location,
        maxBudget: filters.MaxBudget === "" ? null : filters.MaxBudget,
        MinBudget: filters.MinBudget === "" ? null : filters.MinBudget,
        countryId: filters.countryId === "" ? null : filters.countryId,
        stateId: filters.stateId === "" ? null : filters.stateId,
        cityId: filters.cityId === "" ? null : filters.cityId
      };
      const result = await axios.get(`${BASE_PATH}/campaigns/filter`,{ params })
      return result.data.data;
    }
    catch (e)
    {
      console.log(e);
    }
  }

  async deleteCampaign(campaignId: number): Promise<any> {
    try {
      if(!campaignId){
        throw new Error("Campaign ID does not exist");
      }
      const result = await axios.delete(`${BASE_PATH}/campaigns/${campaignId}`, {
        headers: {
          "Authorization": "Basic " + btoa(await asyncLocalStorage.getItem(USERNAME_KEY)+ ":" + await asyncLocalStorage.getItem(PASSWORD_KEY)),
        }
      });

      console.log(result);
      return result;
    }catch (e) {
      console.log(e);
    }
  }

  async getCountryById(countryId: number): Promise<any> {
    try {
      const response = await axios.get(`${BASE_PATH}/countries/${countryId}`);
      return response.data.data;
    }catch (e) {
      console.log(e);
    }
  }

  async getStateById(stateId: number): Promise<any> {
    try {
      return await axios.get(`${BASE_PATH}/states/${stateId}`)
    }catch (e) {
      console.log(e);
    }
  }

  async getCityById(cityId: number): Promise<any> {
    try {
      return await axios.get(`${BASE_PATH}/cities/${cityId}`)
    }catch (e) {
      console.log(e);
    }
  }
}

export const campaignApi = new CampaignsApi()
