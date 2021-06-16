import React from 'react';
import { ConnectedRouter } from 'connected-react-router';
import { Redirect, Route, Switch } from 'react-router-dom';
import { history } from '../store/store';

import SignIn from '../pages/auth/SignIn';
import SignUp from '../pages/auth/SignUp';
import CampaignsView from '../pages/campaigns/CampaignsView';
import CampaignDetailsView from '../pages/campaigns/CampaignDetailsView';
import AdminDashboard from "../pages/admin/Dashboard";
import CampaignFilterView from '../pages/campaigns/CampaignFilterView';
import SimpleRating from "../pages/campaigns/SimpleRating";
import UserDashboard from "../pages/user/UserDashboard";
import {PrivateRoute} from "./PrivateRoute";
import {AdminRoute} from "./AdminRoute";

export const Router: React.FC<any> = () => {
  return (
    <ConnectedRouter history={history}>
      <Switch>
        <Route path='/signin' component={SignIn} />
        <Route path='/signup' component={SignUp} />
        <Route path='/rating' component={SimpleRating} />
        <Route path='/' component={CampaignsView} exact />
        <PrivateRoute path='/campaigns/:id' component={CampaignDetailsView} />
        <AdminRoute path='/admin/dashboard' component={AdminDashboard} />
        <Route path='/campaigns/filter' component={CampaignFilterView} />
        <PrivateRoute path='/user/dashboard' component={UserDashboard} />
        <Redirect from='*' to='/' />
      </Switch>
    </ConnectedRouter>
  )
};
