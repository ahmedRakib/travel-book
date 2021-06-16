import React from "react";
import {Redirect, Route, RouteProps} from "react-router-dom";
import {AUTHORITY_KEY} from "../pages/auth/const";
import {authenticationsApi} from "../api/apis/AuthenticationsApi";

export const AdminRoute = ({ component: Component, ...rest }: RouteProps) => (
  <Route
    {...rest}
    render={props =>
      localStorage.getItem(AUTHORITY_KEY) ? (
        authenticationsApi.hasAdmin() ? (
          <Route {...props} component={Component} />
        ) : (
          <Redirect to={{ pathname: '/', state: { from: props.location } }} />
        )
      ) : (
        <Redirect
          to={{ pathname: '/login', state: { from: props.location } }}
        />
      )
    }
  />
);