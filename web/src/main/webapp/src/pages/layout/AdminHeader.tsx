import React from 'react';
import {AppBar, IconButton, Link, Theme, Toolbar, Typography} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import clsx from 'clsx';
import {AccountCircle, Menu} from '@material-ui/icons';
import logo from "../../assets/images/logo_transparent.png";
import HeaderMenu from "./HeaderMenu";

export interface AdminHeaderProps {
  open: boolean;
  handleDrawerOpen: any;
}

const drawerWidth = 240;

const useStyles = makeStyles((theme: Theme) => ({
  root: {
    display: 'flex',
  },
  toolbar: {
    paddingRight: 24, // keep right padding when drawer closed
  },
  toolbarIcon: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: '0 8px',
    ...theme.mixins.toolbar,
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  menuButton: {
    marginRight: 36,
  },
  menuButtonHidden: {
    display: 'none',
  },
  title: {
    flexGrow: 1,
  },
  drawerPaper: {
    position: 'relative',
    whiteSpace: 'nowrap',
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerPaperClose: {
    overflowX: 'hidden',
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    width: theme.spacing(7),
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9),
    },
  },
  appBarSpacer: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    height: '100vh',
    overflow: 'auto',
  },
  container: {
    paddingTop: theme.spacing(4),
    paddingBottom: theme.spacing(4),
  },
  paper: {
    padding: theme.spacing(2),
    display: 'flex',
    overflow: 'auto',
    flexDirection: 'column',
  },
  fixedHeight: {
    height: 240,
  },
  icon: {
    marginRight: theme.spacing(2),
    width: '100px',
    height: '60px',
    marginTop: theme.spacing(2),
    marginBottom: theme.spacing(2)
  },
}));

export const AdminHeader: React.FC<AdminHeaderProps> = ({ open, handleDrawerOpen }) => {
  const classes = useStyles();
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const openMenu = Boolean(anchorEl);

  const handleMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };
  return (
    <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
      <Toolbar className={classes.toolbar}>
        <IconButton
          edge="start"
          color="inherit"
          aria-label="open drawer"
          onClick={handleDrawerOpen}
          className={clsx(classes.menuButton, open && classes.menuButtonHidden)}
        >
          <Menu />
        </IconButton>
        <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
          <Link href={'/campaigns'}><img className={classes.icon} src={logo} alt='logo'/></Link>
        </Typography>
        <IconButton
          aria-label="account of current user"
          aria-controls="menu-appbar"
          aria-haspopup="true"
          onClick={handleMenu}
          color="inherit"
        >
          <AccountCircle/>
        </IconButton>
        <HeaderMenu open={openMenu} handleClose={handleClose} anchorEl={anchorEl} />
      </Toolbar>
    </AppBar>
  );
}