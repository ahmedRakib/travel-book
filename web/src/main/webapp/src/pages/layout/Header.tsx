import React, {useEffect} from 'react';

import {Button, IconButton, Link} from '@material-ui/core';
import {AccountCircle} from '@material-ui/icons';
import AppBar from '@material-ui/core/AppBar';
import CssBaseline from '@material-ui/core/CssBaseline';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import {makeStyles} from '@material-ui/core/styles';
import logo from '../../assets/images/logo_transparent.png';
import {AppState, history} from "../../store/store";
import {connect, useDispatch} from "react-redux";
import {asyncLocalStorage} from "../../utils";
import {PASSWORD_KEY, USERNAME_KEY} from "../auth/const";
import {authenticationSuccess} from '../auth/authReducer';
import HeaderMenu from "./HeaderMenu";


const useStyles = makeStyles((theme) => ({
  icon: {
    marginRight: theme.spacing(2),
    width: '100px',
    height: '60px',
    marginTop: theme.spacing(2),
    marginBottom: theme.spacing(2)
  },
  title: {
    flexGrow: 1,
  },
}));


const HeaderView: React.FC<Props> = ({loggedIn}) => {

  const classes = useStyles();

  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);

  const handleMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const dispatch = useDispatch();

  useEffect(() => {
    const get = async () => {
      if (!loggedIn) {
        const email = await asyncLocalStorage.getItem(USERNAME_KEY);
        const password = await asyncLocalStorage.getItem(PASSWORD_KEY);

        if (email && password) {
          dispatch(authenticationSuccess(true));
        }
      }
    }

    get().then(_ => console.log('auth called'));
  });

  const getUserName = () => {
    return localStorage.getItem(USERNAME_KEY);
  }

  return (
    <React.Fragment>
      <CssBaseline/>
      <AppBar position="sticky">
        <Toolbar>
          <Link href={'/campaigns'}><img className={classes.icon} src={logo} alt='logo'/></Link>
          <Typography className={classes.title} variant="h6" color="inherit" noWrap>

          </Typography>
          {
            loggedIn &&
            <div>
                <IconButton
                    aria-label="account of current user"
                    aria-controls="menu-appbar"
                    aria-haspopup="true"
                    onClick={handleMenu}
                    color="inherit"
                >
                    <Typography style={{ marginRight: 4 }}>Hi, {getUserName()}</Typography><AccountCircle/>
                </IconButton>
                <HeaderMenu open={open} handleClose={handleClose} anchorEl={anchorEl}/>
            </div>
          }
          {
            !loggedIn &&
            <div>
                <Button variant={"contained"} color={"secondary"} onClick={() => history.push('/signin')}>
                    Sign In
                </Button>
            </div>
          }
        </Toolbar>
      </AppBar>
    </React.Fragment>
  )
}

const mapStateToProps = ({auth}: AppState) => ({
  loggedIn: auth.loggedIn
});

export type Props = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps, null)(HeaderView);