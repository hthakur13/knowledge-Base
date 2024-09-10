// src/components/Header.js
import React from 'react';
import Logo from './Logo';
import './Header.css'; // Import the CSS for styling

const Header = () => {
  return (
    <header className="header">
      <div className="logo-container">
        <Logo />
      </div>
      <h1 className="header-title">KnowledgeBaseAI</h1>
    </header>
  );
};

export default Header;
