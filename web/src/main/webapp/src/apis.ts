import { AuthenticationApi, BASE_PATH, Configuration } from './api';

export const configuration = new Configuration({
  basePath: BASE_PATH,
});

const getUsername = () => {
  return localStorage.getItem('email') as string;
};

const getPassword = () => {
  return localStorage.getItem('password') as string;
};
export const PrivateConfiguration = new Configuration({
  basePath: BASE_PATH,
  username: getUsername(),
  password: getPassword()
});

export const authenticationApi = new AuthenticationApi(PrivateConfiguration);

export * from './api';
