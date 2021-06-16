/* tslint:disable */
/* eslint-disable */

import * as runtime from '../runtime';
import {
  ApiUser,
  ApiUserFromJSON,
  ApiUserToJSON,
} from '../models';
import {asyncLocalStorage} from "../../utils";
import {PASSWORD_KEY, USERNAME_KEY} from "../../pages/auth/const";

export interface RegisterRequest {
  apiUser: ApiUser;
}

/**
 * no description
 */
export class AuthenticationApi extends runtime.BaseAPI {

  /**
   * Login a user
   */
  async loginRaw(): Promise<runtime.ApiResponse<void>> {
    const queryParameters: runtime.HTTPQuery = {};

    const headerParameters: runtime.HTTPHeaders = {};

    if (this.configuration && (this.configuration.username !== undefined || this.configuration.password !== undefined)) {
      headerParameters["Authorization"] = "Basic " + btoa(await asyncLocalStorage.getItem(USERNAME_KEY)+ ":" + await asyncLocalStorage.getItem(PASSWORD_KEY));
    }
    const response = await this.request({
      path: `/auth/sign-in`,
      method: 'POST',
      headers: headerParameters,
      query: queryParameters,
    });

    return new runtime.VoidApiResponse(response);
  }

  /**
   * Login a user
   */
  async login(): Promise<void> {
    await this.loginRaw();
  }

  /**
   * Registers a user
   */
  async registerRaw(requestParameters: RegisterRequest): Promise<runtime.ApiResponse<void>> {
    if (requestParameters.apiUser === null || requestParameters.apiUser === undefined) {
      throw new runtime.RequiredError('apiUser','Required parameter requestParameters.apiUser was null or undefined when calling register.');
    }

    const queryParameters: runtime.HTTPQuery = {};

    const headerParameters: runtime.HTTPHeaders = {};

    headerParameters['Content-Type'] = 'application/json';

    const response = await this.request({
      path: `/auth/sign-up`,
      method: 'POST',
      headers: headerParameters,
      query: queryParameters,
      body: ApiUserToJSON(requestParameters.apiUser)
    });

    return new runtime.VoidApiResponse(response);
  }

  /**
   * Registers a user
   */
  async register(requestParameters: RegisterRequest): Promise<void> {
    await this.registerRaw(requestParameters);
  }

}
