import * as Yup from 'yup';

const today = new Date();
today.setHours(0, 0, 0, 0);

export const CreateCampaignFormSchema = Yup.object().shape({
  title: Yup.string().required("required"),
  participantNumber: Yup.number().positive().required("required"),
  description: Yup.string().required("required"),
  place: Yup.string().required("required"),
  budgets: Yup.number().positive().required("required"),
  duration: Yup.number().positive().required("required"),
  startTime: Yup.date()
    .min(today, "Starting date must be in future")
    .required("required"),
  images: Yup.mixed()
    .test("file","required",(value) => (value && value.length && value.length >= 1)),
});