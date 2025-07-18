Table: patients id: INT, Primary Key, AUTO_INCREMENT

first_name: VARCHAR(50), NOT NULL

last_name: VARCHAR(50), NOT NULL

email: VARCHAR(100), NOT NULL, UNIQUE (email format validation will be handled in application code)

phone_number: VARCHAR(20), UNIQUE (phone format validation will be handled in application code)

date_of_birth: DATE

gender: VARCHAR(10) (e.g., 'Male', 'Female', 'Other')

address: VARCHAR(255)

password_hash: VARCHAR(255), NOT NULL (stores hashed passwords)

registration_date: DATETIME, NOT NULL, Default CURRENT_TIMESTAMP

Table: doctors id: INT, Primary Key, AUTO_INCREMENT

first_name: VARCHAR(50), NOT NULL

last_name: VARCHAR(50), NOT NULL

email: VARCHAR(100), NOT NULL, UNIQUE (email format validation will be handled in application code)

phone_number: VARCHAR(20), UNIQUE (phone format validation will be handled in application code)

specialization: VARCHAR(100), NOT NULL

license_number: VARCHAR(50), NOT NULL, UNIQUE

bio: TEXT (optional, for doctor's biography)

password_hash: VARCHAR(255), NOT NULL (stores hashed passwords)

registration_date: DATETIME, NOT NULL, Default CURRENT_TIMESTAMP

Table: appointments id: INT, Primary Key, AUTO_INCREMENT

doctor_id: INT, Foreign Key → doctors(id), NOT NULL (If a doctor is deleted, their appointments should ideally be marked as 'Cancelled' or the doctor_id set to NULL to retain historical data, rather than deleting the appointments. A SET NULL or NO ACTION on ON DELETE is often preferred over CASCADE for historical integrity.)

patient_id: INT, Foreign Key → patients(id), NOT NULL (If a patient is deleted, their past appointments should probably remain for historical/auditing purposes, but future appointments could be cancelled. SET NULL or NO ACTION on ON DELETE is generally safer.)

clinic_location_id: INT, Foreign Key → clinic_locations(id), NOT NULL (If clinic_locations are added)

appointment_time: DATETIME, NOT NULL (Validation for overlapping appointments will be handled in application logic, not directly in the database schema. This allows for more complex rules, like specific doctor overrides or emergency bookings.)

duration_minutes: INT, NOT NULL (e.g., 60 for an hour-long appointment)

status: ENUM('Scheduled', 'Completed', 'Cancelled', 'No-Show', 'Rescheduled'), NOT NULL, Default 'Scheduled'

reason_for_visit: TEXT

created_at: DATETIME, NOT NULL, Default CURRENT_TIMESTAMP

updated_at: DATETIME, NOT NULL, Default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

Table: admin_users id: INT, Primary Key, AUTO_INCREMENT

username: VARCHAR(50), NOT NULL, UNIQUE

email: VARCHAR(100), NOT NULL, UNIQUE (email format validation will be handled in application code)

password_hash: VARCHAR(255), NOT NULL (stores hashed passwords)

role: VARCHAR(50) (e.g., 'Super Admin', 'Support Staff')

last_login_at: DATETIME

created_at: DATETIME, NOT NULL, Default CURRENT_TIMESTAMP

MongoDB Collection Design
{ "_id": "ObjectId('6699a2c3d4e5f6a7b8c9d0e1')",
"senderId": 123, // Corresponds to patient.id or doctor.id in MySQL
"senderType": "patient", // "patient" or "doctor"
"receiverId": 456, // Corresponds to patient.id or doctor.id in MySQL 
"receiverType": "doctor", // "patient" or "doctor"
"appointmentId": 789, // Optional: Links message to a specific appointment for context
"timestamp": ISODate("2025-07-18T15:30:00.000Z"),
"messageContent": { "type": "text", "text": "Hi Doctor, I'm feeling a bit unwell after my last visit. Can we discuss my medication?" },
"readBy": [ // Array to track who has read the message { "userId": 456, "userType": "doctor", "readAt": ISODate("2025-07-18T15:31:05.000Z") } ], 
"attachments": [ // Optional: Array for file attachments { "filename": "symptoms_log.pdf", "fileType": "application/pdf",
"url": "https://cdn.clinicapp.com/attachments/abc.pdf", "sizeKB": 120 } ], 
"tags": ["urgent", "medication", "follow-up"] // Optional: for categorization or search }
