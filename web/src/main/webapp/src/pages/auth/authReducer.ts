import {createSlice, PayloadAction} from '@reduxjs/toolkit';

export interface AuthError {
  status: boolean;
  message: string;
}

export interface AuthState {
  loggedIn: boolean;
  authError: AuthError | undefined;
}

const initialState: AuthState = {
  loggedIn: false,
  authError: undefined,
};


const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    authenticationSuccess: (
      state: AuthState,
      action: PayloadAction<any>
    ) => {
      if (action.payload) {
        state.loggedIn = true;
      }
    },
    authenticationFailure: (
      state: AuthState,
      action: PayloadAction<AuthError>
    ) => {
      if (action.payload) {
        state.authError = action.payload;
        state.loggedIn = false;
      }
      localStorage.clear();
    },
    resetAuthentication: (
      state: AuthState
    ) => {
      state.loggedIn = false;
      localStorage.clear();
    }
  },
});

const { actions, reducer } = authSlice;

export const { authenticationSuccess, authenticationFailure, resetAuthentication } = actions;

export default reducer;
