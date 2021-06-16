import React, {useState} from 'react';
import {createStyles, makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {Button, ButtonGroup, Chip, TablePagination, Theme} from "@material-ui/core";
import {ApiCampaign} from "../api";
import {CampaignApprovalStatus} from "../common/CampaignApprovalStatus";
import {ConfirmationDialog} from "./ConfirmationDialog";
import CampaignCreateView from '../pages/campaigns/CampaignCreateView';
import {NotFound} from "./NotFound";

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    table: {
      minWidth: 650,
    },
    tableHeader: {
      fontWeight: "bold",
      fontSize: "large"
    },
    paper: {
      width: '100%',
      marginBottom: theme.spacing(2),
    },
  })
);

export interface CampaignTableProps {
  rows: ApiCampaign[];
  handleConfirm?: (apiCampaign: any, action: CampaignApprovalStatus) => void;
  page?: number,
  setPage?: (page: number) => void,
  rowsPerPage?: number,
  setRowsPerPage?: (page: number) => void,
  count: number,
  isAdmin: boolean
}

interface CurrentCampaign {
  campaign: any;
  action: CampaignApprovalStatus
}

export const CampaignTable: React.FC<CampaignTableProps> = (
  {
    rows,
    handleConfirm,
    page = 0,
    setPage,
    rowsPerPage = 5,
    setRowsPerPage,
    count = 0,
    isAdmin,
  }) => {
  const classes = useStyles();
  const [open, setOpen] = useState(false);
  const [currentCampaign, setCurrentCampaign] = useState<CurrentCampaign>();
  const [showCreateDialog, setShowCreateModal] = useState(false);

  const handleChangePage = (event: unknown, newPage: number) => {
    if (setPage && setRowsPerPage) {
      setPage(newPage);
    }
  };

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (setPage && setRowsPerPage) {
      setRowsPerPage(parseInt(event.target.value, 10));
      setPage(0);
    }
  };
  const getColor = (statusId: number): "default" | "primary" | "secondary" | undefined => {
    switch (statusId) {
      case 1:
        return "default";
      case 2:
        return "primary";
      case 3:
        return "secondary";
      default:
        return undefined;
    }
  }

  const getStatus = (statusId: number) => {
    const label = CampaignApprovalStatus[statusId];
    const color = getColor(statusId);
    return <Chip label={label} color={color}/>
  }

  const handleAction = (campaign: any, action: CampaignApprovalStatus) => {
    console.log("value of isAdmin", isAdmin)
    setCurrentCampaign({
      campaign,
      action
    });
    setOpen(true);
  }

  const handleEditAction = (campaign: any, action: CampaignApprovalStatus) => {
    setCurrentCampaign(campaign);
    setShowCreateModal(true)
  }

  const handleDeleteAction = (campaign: any, action: CampaignApprovalStatus) => {
    setCurrentCampaign({
      campaign,
      action
    });
    setOpen(true);
  }


  const handleResult = () => {
    console.log("value of isAdmin", isAdmin)
    setOpen(false);
    if (currentCampaign && handleConfirm) {
      handleConfirm(currentCampaign.campaign, currentCampaign.action);
    }
  }

  return (
    <>
      <Paper className={classes.paper}>
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead className={classes.tableHeader}>
              <TableRow>
                <TableCell>Campaign</TableCell>
                <TableCell align="center">Status</TableCell>
                <TableCell align="center">Starting From</TableCell>
                <TableCell align="center">Location</TableCell>
                <TableCell align="center">Actions</TableCell>
              </TableRow>
            </TableHead>
            {
              rows && rows.length > 0 ? (
                <TableBody>
                  {rows
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((row) => (
                      <TableRow key={row.id}>
                        <TableCell component="th" scope="row">
                          {row.title}
                        </TableCell>
                        <TableCell align="center">
                          {getStatus(row.campaignsApprovalStatusId)}
                        </TableCell>
                        <TableCell align="center">{new Date(row.startTime).toLocaleDateString()}</TableCell>
                        <TableCell align="center">{row.place}</TableCell>
                        <TableCell align="center">
                          {isAdmin && <div>
                              <ButtonGroup variant="contained" aria-label="contained primary button group"
                                           disabled={row.campaignsApprovalStatusId !== 1}>
                                  <Button color="primary"
                                          onClick={() => handleAction(row, CampaignApprovalStatus.Approved)}>Approve</Button>
                                  <Button color="secondary"
                                          onClick={() => handleAction(row, CampaignApprovalStatus.Rejected)}>Reject</Button>
                              </ButtonGroup>
                          </div>
                          }

                          {!isAdmin && <div>
                              <ButtonGroup variant="contained" aria-label="contained primary button group"
                                           disabled={row.campaignsApprovalStatusId !== 1}>
                                  <Button color="primary"
                                          onClick={() => handleEditAction(row, CampaignApprovalStatus.Rejected)}>Edit</Button>
                                  <Button color="secondary"
                                          onClick={() => handleDeleteAction(row, CampaignApprovalStatus.Rejected)}>Delete</Button>
                              </ButtonGroup>
                          </div>
                          }
                        </TableCell>
                      </TableRow>
                    ))}
                </TableBody>
              ) : (
                <NotFound/>
              )
            }
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[5, 10, 25]}
          component="div"
          count={count}
          rowsPerPage={rowsPerPage}
          page={page}
          onChangePage={handleChangePage}
          onChangeRowsPerPage={handleChangeRowsPerPage}
        />
        <ConfirmationDialog title="Are you sure?" open={open} setOpen={setOpen} handleApprove={handleResult}/>
      </Paper>
      {
        showCreateDialog &&
        <CampaignCreateView open={showCreateDialog} setOpen={setShowCreateModal} campaign={currentCampaign}/>
      }
    </>
  );
}
