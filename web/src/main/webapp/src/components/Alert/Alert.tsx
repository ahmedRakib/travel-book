import React, {useEffect, useState} from 'react';
import Snackbar from "@material-ui/core/Snackbar";
import {Alert as MuiAlert} from "@material-ui/lab";
import {AppState} from "../../store/store";
import {connect} from "react-redux";

const Alert: React.FC<any> = ({ message="ss", variant="success" }) => {
  const TIMEOUT = 3000;
  const [open, setOpen] = useState(false);
  const handleClose = () => setOpen(false);

  useEffect(() => {
    if(message && message !== ""){
      setOpen(true);
    }
  }, [message]);

  return (
    <Snackbar
      anchorOrigin={{vertical: 'bottom', horizontal: 'left'}}
      open={open}
      onClose={handleClose}
      autoHideDuration={TIMEOUT}
    >
      <MuiAlert onClose={handleClose} severity={variant}>
        {message}
      </MuiAlert>
    </Snackbar>
  )
}

const mapStateToProps = ({ alerts }: AppState) => ({
  message: alerts.message,
  variant: alerts.variant
});

export type AlertProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps, null)(Alert);