import { configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';
import { createBrowserHistory } from 'history';
import {
  connectRouter,
  routerMiddleware,
  RouterState,
} from 'connected-react-router';
import campaignReducer, {CampaignsState} from "../pages/campaigns/campaignReducers";
import authReducer, {AuthState} from "../pages/auth/authReducer";
import alertReducer, {AlertState} from "../components/Alert/AlertReducer";

export const history = createBrowserHistory();

export interface AppState {
  router: RouterState;
  auth: AuthState;
  campaigns: CampaignsState;
  alerts: AlertState;
}

const store  = configureStore<AppState, any>({
  reducer: {
    router: connectRouter(history),
    auth: authReducer,
    campaigns: campaignReducer,
    alerts: alertReducer
  },
  middleware: [
    ...getDefaultMiddleware(),
    routerMiddleware(history)
  ] as any,
});

export default store;