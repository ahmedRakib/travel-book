import { ApiUser } from '../../api/models/ApiUser';
import { Dispatch } from 'redux';
import { authenticationSuccess, authenticationFailure, resetAuthentication } from './authReducer';
import {authenticationApi} from '../../apis';
import {push} from "connected-react-router";
import {PASSWORD_KEY, USERNAME_KEY} from "./const";
import {asyncLocalStorage} from "../../utils";
import {authenticationsApi} from "../../api/apis/AuthenticationsApi";

export const registrationAction = (apiUser: ApiUser) => async (
  dispatch: Dispatch
) => {
  try {
    await authenticationApi.register({ apiUser });
    dispatch(authenticationSuccess(apiUser));
  } catch (e) {
    dispatch(
      authenticationFailure({
        status: true,
        message: 'Registration Failed',
      })
    );
  }
};

export const loginAction = (apiUser: ApiUser) => async (dispatch: Dispatch) => {
  try {
    await asyncLocalStorage.setItem(USERNAME_KEY, apiUser.email);
    await asyncLocalStorage.setItem(PASSWORD_KEY, apiUser.password);
    await authenticationsApi.signIn();

    dispatch(authenticationSuccess(apiUser));
    dispatch(push('/campaigns'));
  } catch (e) {
    dispatch(
      authenticationFailure({
        status: true,
        message: 'Login Failed',
      })
    );
  }
};

export const resetAuthAction = () => async (dispatch: Dispatch) => {
  try{
    dispatch(resetAuthentication());
    dispatch(push('/signin'));
  }catch (e) {
    console.log(e);
  }
}
