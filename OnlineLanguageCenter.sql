-- =========================================
-- DATABASE: ONLINE_LANGUAGE_CENTER
-- SQL SERVER VERSION
-- =========================================

CREATE DATABASE ONLINE_LANGUAGE_CENTER;
GO

USE ONLINE_LANGUAGE_CENTER;
GO

-- ======================
-- USERS
-- ======================
CREATE TABLE users (
    user_id CHAR(8) PRIMARY KEY,
    email NVARCHAR(255) NOT NULL UNIQUE,
    password_hash NVARCHAR(255) NOT NULL,
    role NVARCHAR(20) NOT NULL CHECK (role IN ('student','lecturer','admin')),
    created_at DATETIME DEFAULT GETDATE()
);

-- ======================
-- STUDENTS
-- ======================
CREATE TABLE students (
    user_id CHAR(8) PRIMARY KEY,
    full_name NVARCHAR(255) NOT NULL,
    date_of_birth DATE,
    phone NVARCHAR(20),
    address NVARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ======================
-- LECTURERS
-- ======================
CREATE TABLE lecturers (
    user_id CHAR(8) PRIMARY KEY,
    full_name NVARCHAR(255) NOT NULL,
    specialization NVARCHAR(255),
    qualification NVARCHAR(255),
    phone NVARCHAR(20),
    bio NVARCHAR(MAX),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ======================
-- ADMINS
-- ======================
CREATE TABLE admins (
    user_id CHAR(8) PRIMARY KEY,
    full_name NVARCHAR(255) NOT NULL,
    position NVARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ======================
-- COURSES
-- ======================
CREATE TABLE courses (
    course_id CHAR(8) PRIMARY KEY,
    course_name NVARCHAR(255) NOT NULL,
    language NVARCHAR(100) NOT NULL,
    level NVARCHAR(20) CHECK (level IN ('beginner','intermediate','advanced')),
    description NVARCHAR(MAX),
    fee DECIMAL(12,0) NOT NULL,
    duration_weeks INT
);

-- ======================
-- CLASSES
-- ======================
CREATE TABLE classes (
    class_id CHAR(8) PRIMARY KEY,
    course_id CHAR(8) NOT NULL,
    lecturer_id CHAR(8) NOT NULL,
    start_date DATE,
    end_date DATE,
    max_students INT DEFAULT 15 CHECK (max_students <= 15),
    mode NVARCHAR(10) DEFAULT 'online' CHECK (mode = 'online'),
    meeting_link NVARCHAR(255),
    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(user_id)
);

-- ======================
-- SCHEDULES
-- ======================
CREATE TABLE schedules (
    schedule_id CHAR(8) PRIMARY KEY,
    class_id CHAR(8) NOT NULL,
    day_of_week NVARCHAR(10) CHECK (day_of_week IN ('Mon','Tue','Wed','Thu','Fri','Sat','Sun')),
    start_time TIME,
    end_time TIME,
    FOREIGN KEY (class_id) REFERENCES classes(class_id)
);

-- ======================
-- PAYMENTS
-- ======================
CREATE TABLE payments (
    payment_id CHAR(8) PRIMARY KEY,
    user_id CHAR(8) NOT NULL,
    amount DECIMAL(12,0) NOT NULL,
    payment_method NVARCHAR(20) CHECK (payment_method IN ('visa','mastercard','bank_transfer')),
    transaction_code NVARCHAR(255),
    status NVARCHAR(20) DEFAULT 'pending' CHECK (status IN ('pending','completed','failed')),
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ======================
-- ENROLLMENTS
-- ======================
CREATE TABLE enrollments (
    enrollment_id CHAR(8) PRIMARY KEY,
    student_id CHAR(8) NOT NULL,
    class_id CHAR(8) NOT NULL,
    status NVARCHAR(20) DEFAULT 'active' CHECK (status IN ('active','completed','cancelled')),
    enrolled_at DATETIME DEFAULT GETDATE(),
    payment_id CHAR(8),
    FOREIGN KEY (student_id) REFERENCES students(user_id),
    FOREIGN KEY (class_id) REFERENCES classes(class_id),
    FOREIGN KEY (payment_id) REFERENCES payments(payment_id)
);

-- ======================
-- ASSIGNMENTS
-- ======================
CREATE TABLE assignments (
    assignment_id CHAR(8) PRIMARY KEY,
    class_id CHAR(8) NOT NULL,
    title NVARCHAR(255),
    description NVARCHAR(MAX),
    due_date DATETIME,
    FOREIGN KEY (class_id) REFERENCES classes(class_id)
);

-- ======================
-- SUBMISSIONS
-- ======================
CREATE TABLE submissions (
    submission_id CHAR(8) PRIMARY KEY,
    assignment_id CHAR(8) NOT NULL,
    student_id CHAR(8) NOT NULL,
    submitted_at DATETIME,
    grade DECIMAL(5,2),
    feedback NVARCHAR(MAX),
    FOREIGN KEY (assignment_id) REFERENCES assignments(assignment_id),
    FOREIGN KEY (student_id) REFERENCES students(user_id)
);

-- ======================
-- ATTENDANCE
-- ======================
CREATE TABLE attendance (
    attendance_id CHAR(8) PRIMARY KEY,
    class_id CHAR(8) NOT NULL,
    student_id CHAR(8) NOT NULL,
    session_date DATE,
    status NVARCHAR(20) CHECK (status IN ('present','absent','late')),
    FOREIGN KEY (class_id) REFERENCES classes(class_id),
    FOREIGN KEY (student_id) REFERENCES students(user_id)
);


USE ONLINE_LANGUAGE_CENTER;
GO

-- ======================
-- USERS
-- ======================
INSERT INTO users (user_id, email, password_hash, role) VALUES
-- Students
('US000011', 'an.nguyen1@gmail.com', 'hash1', 'student'),
('US000012', 'binh.tran1@gmail.com', 'hash2', 'student'),
('US000013', 'cuong.le@gmail.com', 'hash3', 'student'),
('US000014', 'dung.pham@gmail.com', 'hash4', 'student'),
('US000015', 'giang.vo@gmail.com', 'hash5', 'student'),
('US000016', 'ha.do@gmail.com', 'hash6', 'student'),
('US000017', 'khanh.bui@gmail.com', 'hash7', 'student'),
('US000018', 'lam.dang@gmail.com', 'hash8', 'student'),
('US000019', 'linh.hoang@gmail.com', 'hash9', 'student'),
('US000020', 'long.ngo@gmail.com', 'hash10', 'student'),
('US000021', 'mai.trinh@gmail.com', 'hash11', 'student'),
('US000022', 'nam.phung@gmail.com', 'hash12', 'student'),
('US000023', 'oanh.nguyen@gmail.com', 'hash13', 'student'),
('US000024', 'phuc.tran@gmail.com', 'hash14', 'student'),
('US000025', 'quynh.le@gmail.com', 'hash15', 'student'),
('US000026', 'son.vo@gmail.com', 'hash16', 'student'),
('US000027', 'thao.do@gmail.com', 'hash17', 'student'),
('US000028', 'trang.bui@gmail.com', 'hash18', 'student'),
('US000029', 'tuan.dang@gmail.com', 'hash19', 'student'),
('US000030', 'yen.hoang@gmail.com', 'hash20', 'student'),

-- Lecturers
('US000001', 'tuan.le@gmail.com', 'hash1', 'lecturer'),
('US000002', 'hoa.pham@gmail.com', 'hash2', 'lecturer'),
('US000003', 'minh.nguyen@gmail.com', 'hash3', 'lecturer'),
('US000004', 'linh.tran@gmail.com', 'hash4', 'lecturer'),
('US000005', 'huy.vo@gmail.com', 'hash5', 'lecturer'),
('US000006', 'trang.do@gmail.com', 'hash6', 'lecturer'),
('US000007', 'son.bui@gmail.com', 'hash7', 'lecturer'),
('US000008', 'yen.dang@gmail.com', 'hash8', 'lecturer'),
('US000009', 'phuc.hoang@gmail.com', 'hash9', 'lecturer'),
('US000010', 'mai.ngo@gmail.com', 'hash10', 'lecturer'),


-- Admins
('US000031', 'admin@trungtam.com', 'adminhash', 'admin');

-- ======================
-- STUDENTS
-- ======================
INSERT INTO students (user_id, full_name, date_of_birth, phone, address) VALUES
('US000011', N'Nguyễn Văn An', '2004-01-10', '0901000001', N'Hà Nội'),
('US000012', N'Trần Thị Bình', '2003-02-15', '0901000002', N'TP.HCM'),
('US000013', N'Lê Minh Cường', '2002-03-20', '0901000003', N'Đà Nẵng'),
('US000014', N'Phạm Quốc Dũng', '2004-04-12', '0901000004', N'Hải Phòng'),
('US000015', N'Võ Thị Giang', '2003-05-18', '0901000005', N'Cần Thơ'),
('US000016', N'Đỗ Minh Hà', '2002-06-25', '0901000006', N'Huế'),
('US000017', N'Bùi Khánh Khanh', '2004-07-30', '0901000007', N'Nghệ An'),
('US000018', N'Đặng Văn Lâm', '2003-08-05', '0901000008', N'Bình Dương'),
('US000019', N'Hoàng Thị Linh', '2002-09-14', '0901000009', N'Đồng Nai'),
('US000020', N'Ngô Văn Long', '2004-10-22', '0901000010', N'Thanh Hóa'),
('US000021', N'Trịnh Thị Mai', '2003-11-11', '0901000011', N'Quảng Nam'),
('US000022', N'Phùng Văn Nam', '2002-12-19', '0901000012', N'Quảng Ninh'),
('US000023', N'Nguyễn Thị Oanh', '2004-01-28', '0901000013', N'Hà Nam'),
('US000024', N'Trần Đức Phúc', '2003-02-09', '0901000014', N'Bắc Ninh'),
('US000025', N'Lê Thị Quỳnh', '2002-03-17', '0901000015', N'Vĩnh Phúc'),
('US000026', N'Võ Thanh Sơn', '2004-04-26', '0901000016', N'Khánh Hòa'),
('US000027', N'Đỗ Thị Thảo', '2003-05-03', '0901000017', N'Gia Lai'),
('US000028', N'Bùi Thị Trang', '2002-06-15', '0901000018', N'Lâm Đồng'),
('US000029', N'Đặng Minh Tuấn', '2004-07-21', '0901000019', N'Bình Thuận'),
('US000030', N'Hoàng Ngọc Yến', '2003-08-08', '0901000020', N'An Giang');


-- ======================
-- LECTURERS
-- ======================
INSERT INTO lecturers (user_id, full_name, specialization, qualification, phone, bio) VALUES
('US000001', N'Lê Minh Tuấn', N'Tiếng Anh giao tiếp', N'Thạc sĩ Ngôn ngữ Anh', '0901111111', N'5 năm kinh nghiệm giảng dạy'),
('US000002', N'Phạm Thu Hoa', N'Tiếng Anh thương mại', N'Cử nhân Quốc tế học', '0902222222', N'Chuyên đào tạo doanh nghiệp'),
('US000003', N'Nguyễn Văn Minh', N'Tiếng Nhật sơ cấp', N'N2 JLPT', '0903333333', N'Giảng viên tiếng Nhật 4 năm'),
('US000004', N'Trần Thị Linh', N'Tiếng Hàn TOPIK', N'TOPIK 6', '0904444444', N'Đã học tập tại Hàn Quốc'),
('US000005', N'Võ Quốc Huy', N'Tiếng Trung giao tiếp', N'HSK 5', '0905555555', N'Kinh nghiệm giảng dạy 6 năm'),
('US000006', N'Đỗ Thùy Trang', N'Tiếng Anh IELTS', N'IELTS 8.0', '0906666666', N'Luyện thi IELTS chuyên sâu'),
('US000007', N'Bùi Thanh Sơn', N'Tiếng Nhật N3-N2', N'N1 JLPT', '0907777777', N'Chuyên luyện thi JLPT'),
('US000008', N'Đặng Ngọc Yến', N'Tiếng Hàn giao tiếp', N'TOPIK 5', '0908888888', N'Giảng viên trẻ năng động'),
('US000009', N'Hoàng Đức Phúc', N'Tiếng Trung HSK', N'HSK 6', '0909999999', N'Chuyên đào tạo HSK cấp tốc'),
('US000010', N'Ngô Thanh Mai', N'Tiếng Pháp cơ bản', N'DELF B2', '0910000000', N'Giảng viên tiếng Pháp 3 năm');


-- ======================
-- ADMIN
-- ======================
INSERT INTO admins (user_id, full_name, position) VALUES
('US000031', N'Nguyễn Hoàng Nam', N'Quản trị hệ thống');

-- ======================
-- COURSES
-- ======================
INSERT INTO courses (course_id, course_name, language, level, description, fee, duration_weeks) VALUES

-- ENGLISH
('CR000001', N'Tiếng Anh giao tiếp cơ bản', N'Tiếng Anh', 'beginner', N'Học giao tiếp tiếng Anh cho người mới bắt đầu', 1500000, 8),
('CR000002', N'Tiếng Anh giao tiếp nâng cao', N'Tiếng Anh', 'intermediate', N'Nâng cao kỹ năng nghe nói trong môi trường thực tế', 2500000, 10),
('CR000003', N'Luyện thi IELTS 6.5+', N'Tiếng Anh', 'advanced', N'Chuẩn bị cho kỳ thi IELTS từ 6.5 trở lên', 3500000, 12),

-- JAPANESE
('CR000004', N'Tiếng Nhật N5', N'Tiếng Nhật', 'beginner', N'Khóa học tiếng Nhật sơ cấp trình độ N5', 2000000, 10),
('CR000005', N'Tiếng Nhật N3', N'Tiếng Nhật', 'intermediate', N'Nâng cao trình độ ngữ pháp và giao tiếp N3', 2800000, 12),
('CR000006', N'Tiếng Nhật N2', N'Tiếng Nhật', 'advanced', N'Luyện thi JLPT N2 chuyên sâu', 3500000, 14),

-- KOREAN
('CR000007', N'Tiếng Hàn TOPIK I', N'Tiếng Hàn', 'beginner', N'Luyện thi TOPIK I cho người mới', 1800000, 10),
('CR000008', N'Tiếng Hàn TOPIK II', N'Tiếng Hàn', 'intermediate', N'Nâng cao kỹ năng thi TOPIK II', 2600000, 12),
('CR000009', N'Tiếng Hàn nâng cao', N'Tiếng Hàn', 'advanced', N'Giao tiếp và học thuật tiếng Hàn nâng cao', 3200000, 14),

-- CHINESE
('CR000010', N'Tiếng Trung HSK 2', N'Tiếng Trung', 'beginner', N'Khóa học tiếng Trung cơ bản HSK 2', 1800000, 8),
('CR000011', N'Tiếng Trung HSK 4', N'Tiếng Trung', 'intermediate', N'Luyện thi HSK 4 toàn diện', 2500000, 10),
('CR000012', N'Tiếng Trung HSK 5', N'Tiếng Trung', 'advanced', N'Luyện thi HSK 5 chuyên sâu', 3200000, 12),

-- FRENCH
('CR000013', N'Tiếng Pháp A1', N'Tiếng Pháp', 'beginner', N'Khóa học tiếng Pháp cơ bản A1', 2000000, 8),
('CR000014', N'Tiếng Pháp B1', N'Tiếng Pháp', 'intermediate', N'Nâng cao trình độ tiếng Pháp B1', 2800000, 10),
('CR000015', N'Tiếng Pháp B2', N'Tiếng Pháp', 'advanced', N'Luyện thi DELF B2', 3500000, 12);

-- ======================
-- CLASSES
-- ======================
INSERT INTO classes (class_id, course_id, lecturer_id, max_students, start_date, end_date, meeting_link) VALUES
('CL000001', 'CR000001', 'US000001', 15, '2026-03-10', '2026-05-05', 'https://meet.google.com/eng-basic-01'),
('CL000002', 'CR000004', 'US000003', 15, '2026-03-12', '2026-05-20', 'https://meet.google.com/jpn-n5-01'),
('CL000003', 'CR000007', 'US000004', 15, '2026-03-15', '2026-05-25', 'https://meet.google.com/kor-topik1-01');

