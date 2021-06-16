import React from 'react';
import {Menu, MenuItem} from "@material-ui/core";
import {authenticationsApi} from "../../api/apis/AuthenticationsApi";
import {push} from "connected-react-router";
import {connect, ResolveThunks, useDispatch} from "react-redux";
import {resetAuthAction} from "../auth/authActions";

export interface HeaderMenuProps {
  open: boolean;
  handleClose: any;
  anchorEl: any;
}

export const HeaderMenu: React.FC<Props> = ({ open, handleClose, anchorEl, resetAuth }) => {

  const dispatch = useDispatch();
  const handleDashboardRoute = async () => {
    const admin = await authenticationsApi.hasAdmin();
    if (admin) {
      dispatch(push('/admin/dashboard'));
    } else {
      dispatch(push('/user/dashboard'));
    }
  }
  return (
    <Menu
      id="menu-appbar"
      anchorEl={anchorEl}
      anchorOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      keepMounted
      transformOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      open={open}
      onClose={handleClose}
    >
      <MenuItem onClick={handleDashboardRoute}>Dashboard</MenuItem>
      <MenuItem onClick={resetAuth}>Sign Out</MenuItem>
    </Menu>
  )
}

const mapDispatchToProps = {
  resetAuth: resetAuthAction
}

export type Props = HeaderMenuProps & ResolveThunks<typeof mapDispatchToProps>;

export default connect(null, mapDispatchToProps)(HeaderMenu);
