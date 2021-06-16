import React from 'react';
import Paper from '@material-ui/core/Paper';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import {CampaignApprovalStatus} from "../common/CampaignApprovalStatus";

interface CampaignFilterTabsProps {
  filter: CampaignApprovalStatus,
  setFilter: (campaignFilter: CampaignApprovalStatus) => void;
}

export const CampaignFilterTabs:React.FC<CampaignFilterTabsProps> = ({ filter, setFilter }) => {

  const handleChange = (event: React.ChangeEvent<{}>, newValue: number) => {
    setFilter(newValue);
  };

  return (
    <Paper square>
      <Tabs
        value={filter}
        indicatorColor="primary"
        textColor="primary"
        onChange={handleChange}
        aria-label="disabled tabs example"
        variant="fullWidth"
      >
        {/*<Tab label="All" />
        <Tab label="Pending" />
        <Tab label="Approved" />*/}
      </Tabs>
    </Paper>
  );
}