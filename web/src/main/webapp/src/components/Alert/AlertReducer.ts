import {createSlice, PayloadAction} from '@reduxjs/toolkit';

export interface AlertState {
  message: string;
  variant: "success" | "info" | "warning" | "error" | undefined;
}

const initialState: AlertState = {
  message: "",
  variant: undefined
};


const alertSlice = createSlice({
  name: 'alert',
  initialState,
  reducers: {
    setAlert: (
      state: AlertState,
      action: PayloadAction<AlertState>
    ) => {
      if (action.payload) {
        state.message = action.payload.message;
        state.variant = action.payload.variant;
      }
    },
    resetAlert: (
      state: AlertState
    ) => {
      state.message = initialState.message;
    },
  },
});

const { actions, reducer } = alertSlice;

export const { setAlert, resetAlert } = actions;

export default reducer;
