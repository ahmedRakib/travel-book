import React, {useEffect, useState} from 'react';
import {AdminLayout} from "../layout/AdminLayout";
import {Container, Grid, Theme} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import {CampaignFilterTabs} from "../../components/CampaignFilterTabs";
import {CampaignTable} from "../../components/CampaignTable";
import {AppState} from "../../store/store";
import {getCampaignsAction, updateCampaignAction} from "../campaigns/campaignActions";
import {connect, ResolveThunks} from "react-redux";
import {CampaignApprovalStatus} from "../../common/CampaignApprovalStatus";

const useStyles = makeStyles((theme: Theme) => ({
  container: {
    paddingTop: theme.spacing(4),
    paddingBottom: theme.spacing(4),
  },
}))

const Dashboard: React.FC<AdminDashboardProps> = ({ getCampaigns, campaigns, updateCampaign, total }) => {
  const classes = useStyles();

  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [filteredCampaigns, setFilteredCampaigns] = useState<any[]>([]);
  const [filter, setFilter] = useState<CampaignApprovalStatus>(CampaignApprovalStatus.All);

  useEffect(() => {
    const getAllCampaigns = async () => {
      await getCampaigns(0, (page+1)*rowsPerPage, true)
    };

    getAllCampaigns();
  }, [getCampaigns, page, rowsPerPage]);

  useEffect(() => {
    if(campaigns){
      setFilteredCampaigns(campaigns);
    }
  }, [campaigns]);

  useEffect(() => {
    if(filter){
      const filterCampaigns = campaigns.filter((campaign: any) => campaign.campaignsApprovalStatusId === filter.valueOf())
      setFilteredCampaigns(filterCampaigns);
    }
    else {
      setFilteredCampaigns(campaigns);
    }
  }, [filter, campaigns]);

  const handleUpdateCampaign = async (apiCampaign: any, action: CampaignApprovalStatus) => {
    if(apiCampaign.id){
      const approvedCampaign = {
        ...apiCampaign,
        campaignsApprovalStatusId: action.valueOf()
      }
      await updateCampaign(approvedCampaign);
    }
  }

  return (
    <AdminLayout>
      <Container maxWidth="lg" className={classes.container}>
        <CampaignFilterTabs filter={filter} setFilter={setFilter}/>
        <Grid item>
          <CampaignTable
            rows={filteredCampaigns}
            handleConfirm={handleUpdateCampaign}
            setPage={setPage}
            setRowsPerPage={setRowsPerPage}
            page={page}
            rowsPerPage={rowsPerPage}
            count={total}
            isAdmin = {true}
          />
        </Grid>
      </Container>
    </AdminLayout>
  )
};

const mapStateToProps = ({ campaigns }: AppState) => ({
  campaigns: campaigns.campaigns,
  total: campaigns.totalCampaigns
});

const mapDispatchToProps = {
  getCampaigns: getCampaignsAction,
  updateCampaign: updateCampaignAction
};

export type AdminDashboardProps = ResolveThunks<typeof mapDispatchToProps> & ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);