import React, { useState } from 'react';
import axios from 'axios';
import './FileUpload.css'

const FileUpload = () => {
    const [file, setFile] = useState(null);
    const [message, setMessage] = useState('');

    const onFileChange = (event) => {
        setFile(event.target.files[0]);
    };

    const onFileUpload = async () => {
        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await axios.post('http://localhost:8080/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            setMessage(response.data);
        } catch (error) {
            setMessage('Error uploading file');
        }
    };

    return (
        <div>
            <h1>Add the issue to resolution DB</h1>
            <h2>Upload CSV File</h2>
            <input type="file" onChange={onFileChange} />
            <button onClick={onFileUpload}>Upload</button>
            <p>{message}</p>
        </div>
    );
};

export default FileUpload;
