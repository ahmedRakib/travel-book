import {DateTime} from "luxon";

export const formatDate = (date: string, format: string = 'DDDD') => {
  let dt = new Date(date)
  let year = dt.getFullYear();
  let month = ("0" + (dt.getMonth() + 1)).slice(-2);
  let day = ("0" + dt.getDate()).slice(-2);
  let d = `${year}-${month}-${day}`;
  return DateTime.fromISO(d).toFormat(format)
}
