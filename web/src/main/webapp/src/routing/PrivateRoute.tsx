import React from 'react';
import { Redirect, Route, RouteProps } from 'react-router';
import {PASSWORD_KEY, USERNAME_KEY} from "../pages/auth/const";

export const PrivateRoute = ({ component: Component, ...rest }: RouteProps) => (
  <Route
    {...rest}
    render={props =>
      localStorage.getItem(USERNAME_KEY) && localStorage.getItem(PASSWORD_KEY) ? (
        <Route {...props} component={Component} />
      ) : (
        <Redirect
          to={{ pathname: '/signin', state: { from: props.location } }}
        />
      )
    }
  />
);