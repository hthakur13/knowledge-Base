const express = require('express');
const mysql = require('mysql2');
const path = require('path');

const app = express();
const port = 3000;

// MySQL connection setup
const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'root',
  database: 'resolutionDB'
});

connection.connect(err => {
  if (err) {
    console.error('Error connecting to the database:', err);
    return;
  }
  console.log('Connected to the MySQL server.');
});

app.use(express.static(path.join(__dirname, 'public')));

app.get('/api/issues', (req, res) => {
  const { issue_id, issue_description } = req.query;
  let query = 'SELECT * FROM issues WHERE 1=1';
  if (issue_id) {
    query += ` AND issue_id = ${connection.escape(issue_id)}`;
  }
  if (issue_description) {
    query += ` AND issue_description LIKE ${connection.escape('%' + issue_description + '%')}`;
  }
  connection.query(query, (error, results) => {
    if (error) {
      return res.status(500).send(error);
    }
    res.json(results);
  });
});

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
