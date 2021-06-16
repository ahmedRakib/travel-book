import axios from "axios";
import {asyncLocalStorage} from "../../utils";
import {AUTHORITY_KEY, PASSWORD_KEY, USERNAME_KEY} from "../../pages/auth/const";
import {CampaignsApi} from "./CampaignsApi";
import {BASE_PATH} from "../runtime";

export class AuthenticationsApi {
  async signIn(): Promise<void> {
    const response = await axios.post(`${BASE_PATH}/auth/sign-in`, {}, {
      headers: {
        "Authorization": "Basic " + btoa(await asyncLocalStorage.getItem(USERNAME_KEY) + ":" + await asyncLocalStorage.getItem(PASSWORD_KEY))
      }
    });

    const authorities = response.data.data.authorities?.map((auth: any) => auth.authority).join(",");
    await asyncLocalStorage.setItem(AUTHORITY_KEY, authorities);
  }

  async getAuthorities(): Promise<string[]> {
    const username = await asyncLocalStorage.getItem(USERNAME_KEY);
    const password = await asyncLocalStorage.getItem(PASSWORD_KEY);
    if (username && password) {
      const authorityString = await asyncLocalStorage.getItem(AUTHORITY_KEY);
      if (authorityString) {
        return authorityString.split(",");
      }
      return [];
    }
    throw "Unauthorized User";
  }

  async hasAdmin(): Promise<boolean> {
    const authorities = await this.getAuthorities();
    if(authorities){
      return authorities.includes("admin");
    }else{
      return false;
    }
  }
}
export const authenticationsApi = new AuthenticationsApi();