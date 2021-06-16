import {exists} from "../runtime";

export interface ApiCampaign {
  id?: number;
  title: string;
  place: string;
  description?: string;
  participantNumber? : number
  startTime: any;
  duration: number;
  budgets: any;
  userId: number;
  createdDate?: any;
  campaignsApprovalStatusId: number;
  overallRating: any;
}

export function ApiCampaignFromJSON(json: any): ApiCampaign {
  return ApiCampaignFromJSONTyped(json, false);
}

export function ApiCampaignFromJSONTyped(json: any, ignoreDiscriminator: boolean): ApiCampaign {
  if ((json === undefined) || (json === null)) {
    return json;
  }
  return {

    'id': !exists(json, 'id') ? undefined : json['id'],
    'title': json['title'],
    'place': json['place'],
    'description': json['description'],
    'participantNumber': json['participantNumber'],
    'startTime': json['startTime'],
    'duration': json['duration'],
    'budgets': json['budgets'],
    'userId': json['userId'],
    'createdDate': !exists(json, 'createdDate') ? undefined : json['createdDate'],
    'campaignsApprovalStatusId': json['campaignsApprovalStatusId'],
    'overallRating': json['overallRating']
  };
}

export function ApiCampaignToJSON(value?: ApiCampaign | null): any {
  if (value === undefined) {
    return undefined;
  }
  if (value === null) {
    return null;
  }
  return {

    'id': value.id,
    'title': value.title,
    'place': value.place,
    'startTime': value.startTime,
    'description': value.description,
    'participantNumber': value.participantNumber,
    'duration': value.duration,
    'budgets': value.budgets,
    'userId': value.userId,
    'createdDate': value.createdDate,
    'campaignsApprovalStatusId': value.campaignsApprovalStatusId,
    'overallRating': value.overallRating,
  };
}