import React from 'react';
import Header from './Header';
import { Footer } from './Footer';

export interface BasicLayoutProps {
  children: any;
}

export const BasicLayout: React.FC<BasicLayoutProps> = ({ children }) => {
  return (
    <React.Fragment>
      {/*@ts-ignore*/}
      <Header />
      <main>
        {children}
      </main>
      <Footer />
    </React.Fragment>
  );
}