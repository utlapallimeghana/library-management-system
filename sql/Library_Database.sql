CREATE DATABASE library_management;
USE library_management;
CREATE TABLE books (
     book_id INT PRIMARY KEY AUTO_INCREMENT,
     title VARCHAR(100) NOT NULL,
     author VARCHAR(100) NOT NULL,
     category VARCHAR(50),
     publisher VARCHAR(100),
     publication_year YEAR,
     total_copies INT DEFAULT 1,
     available_copies INT DEFAULT 1
     );
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    year_of_study INT,
    phone VARCHAR(15) NOT NULL,
    registration_date DATE DEFAULT(CURRENT_DATE)
    );
CREATE TABLE book_issues (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    student_id INT NOT NULL,
    issue_date DATE DEFAULT (CURRENT_DATE),
    due_date DATE NOT NULL,
    return_date DATE,
    fine DECIMAL(10,2) DEFAULT 0,

    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id)
); 
INSERT INTO books
(title, author, category, publisher, publication_year, total_copies, available_copies)
VALUES
('C Programming', 'Dennis Ritchie', 'Programming', 'Pearson', 2015, 5, 5),
('Digital Electronics', 'R.P. Jain', 'Electronics', 'McGraw Hill', 2018, 4, 4),
('Database Management Systems', 'Raghu Ramakrishnan', 'Database', 'McGraw Hill', 2017, 3, 3),
('Computer Networks', 'Andrew S. Tanenbaum', 'Networking', 'Pearson', 2020, 4, 4),
('Data Structures', 'Seymour Lipschutz', 'Programming', 'McGraw Hill', 2019, 5, 5); 
SELECT * FROM books;  
INSERT INTO students
(name, department, year_of_study, phone)
VALUES
('Meghana', 'ECE', 3, '9876543210'),
('Rahul', 'CSE', 2, '9876543211'),
('Priya', 'ECE', 3, '9876543212'),
('Kiran', 'EEE', 2, '9876543213'),
('Anjali', 'CSE', 4, '9876543214');
SELECT * FROM students;
INSERT INTO book_issues
(book_id, student_id, issue_date, due_date)
VALUES
(1, 1, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY));
SELECT * FROM book_issues;
UPDATE books
SET available_copies = available_copies - 1
WHERE book_id = 1
AND available_copies > 0;
SELECT book_id, title, total_copies, available_copies
FROM books
WHERE book_id = 1;
ALTER TABLE book_issues
ADD COLUMN status VARCHAR(20) DEFAULT 'Issued';
SELECT * FROM book_issues;
INSERT INTO book_issues
(book_id, student_id, due_date)
VALUES
(2, 2, DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY)),
(3, 3, DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY));
SELECT * FROM book_issues;
UPDATE book_issues
SET return_date=CURRENT_DATE,
status='Returned'
WHERE issue_id=1;
UPDATE books
SET available_copies=available_copies+1
WHERE book_id=1;
SELECT * FROM book_issues
WHERE due_date < CURRENT_DATE
  AND status = 'Issued';
  SET SQL_SAFE_UPDATES=0;
UPDATE book_issues
SET status = 'Overdue'
WHERE due_date < CURRENT_DATE
  AND status = 'Issued';
 SET SQL_SAFE_UPDATES=1; 
 SELECT * FROM book_issues;
  SET SQL_SAFE_UPDATES=0;
 UPDATE book_issues
SET fine = DATEDIFF(CURRENT_DATE, due_date) * 5
WHERE issue_id>0
 AND due_date < CURRENT_DATE
AND status = 'Overdue'
AND return_date IS NULL;
 SET SQL_SAFE_UPDATES=1; 
SELECT * FROM book_issues
WHERE status = 'Overdue';
SELECT s.student_id, s.name, b.title, bi.due_date, bi.fine
FROM students s
JOIN book_issues bi ON s.student_id = bi.student_id
JOIN books b ON b.book_id = bi.book_id
WHERE bi.status = 'Overdue';
SELECT * FROM book_issues
WHERE return_date IS NOT NULL;
SELECT SUM(fine) AS total_fine
FROM book_issues;
SELECT book_id, title, available_copies
FROM books;
SELECT * FROM book_issues
WHERE return_date IS NULL;
COMMIT;


    