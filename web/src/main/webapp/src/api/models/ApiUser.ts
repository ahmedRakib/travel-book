import {exists} from "../runtime";

export interface ApiUser {
  id?: number;
  username?: string;
  email: string;
  password: string;
  enabled?: boolean;
  blacklisted?: boolean;
  authorities?: any[];
}

export function ApiUserFromJSON(json: any): ApiUser {
  return ApiUserFromJSONTyped(json, false);
}

export function ApiUserFromJSONTyped(json: any, ignoreDiscriminator: boolean): ApiUser {
  if ((json === undefined) || (json === null)) {
    return json;
  }
  return {

    'id': !exists(json, 'id') ? undefined : json['id'],
    'email': json['email'],
    'password': json['password'],
    'username': !exists(json, 'username') ? undefined : json['username'],
  };
}

export function ApiUserToJSON(value?: ApiUser | null): any {
  if (value === undefined) {
    return undefined;
  }
  if (value === null) {
    return null;
  }
  return {

    'id': value.id,
    'email': value.email,
    'password': value.password,
    'username': value.username,
  };
}