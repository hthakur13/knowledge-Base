import React, { useContext, useEffect, useState } from 'react';
import { NavLink, Route, Routes, useNavigate, Navigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import axios from 'axios';
import { AuthContext } from '../context/AuthContext';
import About from './About';
import SearchIssue from './SearchIssue';
import AddIssue from './AddIssue';
import './Home.css';

const Home = () => {
  const { logout } = useContext(AuthContext);
  const navigate = useNavigate();
  const empId = localStorage.getItem('empId');
  const [empName, setEmpName] = useState('');

  useEffect(() => {
    if (!empId) {
      navigate('/');
    } else {
      toast.success('Successfully logged in!');
      fetchEmployeeDetails();
    }
  }, [empId, navigate]);

  const fetchEmployeeDetails = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/employees/${empId}`);
      if (response.data) {
        setEmpName(response.data.empName);
      }
    } catch (error) {
      console.error('Error fetching employee details:', error);
      toast.error('Failed to fetch employee details');
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('empId');
    logout();
    navigate('/');
  };

  return (
    <div className="home-container">
      <div className="sidebar">
        <h3>Welcome, {empName}</h3>
        <NavLink to="/home/about" className={({ isActive }) => (isActive ? 'active-link' : '')}>About</NavLink>
        <NavLink to="/home/search-issue" className={({ isActive }) => (isActive ? 'active-link' : '')}>Search Issue</NavLink>
        <NavLink to="/home/add-issue" className={({ isActive }) => (isActive ? 'active-link' : '')}>Add Issue</NavLink>
        <button onClick={handleLogout}>Logout</button>
      </div>
      <div className="content">
        <Routes>
          <Route path="/" element={<Navigate to="/home/about" />} />
          <Route path="/about" element={<About />} />
          <Route path="/search-issue" element={<SearchIssue />} />
          <Route path="/add-issue" element={<AddIssue />} />
        </Routes>
      </div>
    </div>
  );
};

export default Home;
