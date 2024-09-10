import React from 'react';
import './AddIssue.css';
import FileUpload from './FileUpload';

const AddIssue = () => {
  return (
    <div>
      <h2>Bulk Add Issue</h2>
      <FileUpload />
    </div>
  );
};

export default AddIssue;
