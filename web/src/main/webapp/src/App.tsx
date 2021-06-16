import React from 'react';
import { Provider } from 'react-redux';
import store from './store/store';
import './App.css';
import { Router } from './routing/routes';
import Alert from './components/Alert/Alert';

const App: React.FC = () => {
  return (
    <Provider store={store}>
      <Router />
      <Alert />
    </Provider>
  );
}

export default App;
