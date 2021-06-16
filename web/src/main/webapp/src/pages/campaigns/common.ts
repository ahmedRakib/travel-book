export const getImage = (campaign: any) => {
  if(campaign && campaign.campaignsImagesList && campaign.campaignsImagesList.length > 0){
    return campaign.campaignsImagesList[0].image;
  }
  else return null;
}