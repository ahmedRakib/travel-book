import React from 'react';
import {SideBar} from "./SideBar";
import {CssBaseline, ListItem, ListItemIcon, ListItemText, Theme} from "@material-ui/core";
import {Dashboard} from '@material-ui/icons';
import {AdminHeader} from "./AdminHeader";
import {makeStyles} from "@material-ui/core/styles";

export interface AdminLayoutProps {
  children: any;
}

const useStyles = makeStyles((theme: Theme) => ({
  root: {
    display: 'flex',
  },
  content: {
    flexGrow: 1,
    height: '100vh',
    overflow: 'auto',
  },
  appBarSpacer: theme.mixins.toolbar,
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
}));

export const AdminLayout: React.FC<AdminLayoutProps> = ({children}) => {
  const classes = useStyles();
  const [open, setOpen] = React.useState(true);
  const handleDrawerOpen = () => {
    setOpen(true);
  };
  const handleDrawerClose = () => {
    setOpen(false);
  };

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AdminHeader open={open} handleDrawerOpen={handleDrawerOpen}/>
      <SideBar
        menuItems={
          <div>
            <ListItem button>
              <ListItemIcon>
                <Dashboard/>
              </ListItemIcon>
              <ListItemText primary="Dashboard"/>
            </ListItem>
          </div>
        }
        open={open}
        handleDrawerClose={handleDrawerClose}
      />
      <main className={classes.content}>
        <div className={classes.appBarSpacer} />
        {children}
      </main>
    </div>
  );
}