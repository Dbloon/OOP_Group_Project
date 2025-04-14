This group project is designed to allow students to employ key object-oriented programming 
(OOP) concepts to analyse, design, and implement a real-world application using C++ or Java. 
Groups should use composition, inheritance, polymorphism, dynamic binding, encapsulation, 
data abstraction, Graphical User Interface (GUI), persistence through file handling, and 
defensive programming using exception-handling techniques. 
The scenario for this project is as follows: 
The University of Technology, Jamaica (UTech) Medical Centre would like you to automate their 
manual system.  You are tasked with designing and implementing a robust system for the 
Medical and Wellness Management System (MWMS).  The system should capture patient 
records and information on medical professionals (such as doctors, nurses, 
counsellors/psychologists, and dietitians). 
MWMS is an integrated software system that will fully automate UTech Medical Centre 
operations, replacing manual record-keeping with a centralised, digital solution. This system will 
streamline patient management, staff coordination, medication tracking, appointment scheduling, 
and prescription generation, to improve the overall efficiency and accuracy of the medical centre. 
The main features of the MWMS are as follows:  
The system should have a dashboard that allows users to do different tasks based on their access 
privilege.   
1. Login &Authentication 
a. User login with role-based access  
b. Password Encryption using Random Access File. 
c. Role (Admin, Doctor, Nurse, Dietitian, Psychologist/Councillor, Patients and 
Receptionist) 
This section should display a login form or area that asks the user to enter a username 
and a password.  Once the login information is correct, a welcome screen should 
provide information about the user. However, if the login credentials are incorrect, 
then an appropriate message should be provided indicating that the login was 
unsuccessful. All users should be given a maximum of 3 unsuccessful attempts, after 
which the account should be locked. Only the administrator can unlock a user’s 
locked account. The section should enable a role-based dashboard for navigating to 
the different sections based on the type of user, that is, medical professionals, 
administrators, receptionists, and patients. 
2. Staff Records Management 
a. Employee profiles with roles, schedules, and certifications. 
b. Role-based access control for security and compliance. The access for a nurse is 
different from that of a doctor. 
In this section, employees have the following information: employee 
identification number, first name, middle name (optional), last name, date of birth, 
date of employment, assigned department, gender, trn, job title, assigned 
supervisor 
3. Visit & Referral Management 
a. Logging of patient visits with timestamps, doctor assignments, and diagnoses. 
b. For specialist consultations, the system should be able to track status and 
feedback. 
1 
Created by: Rorron A. Clarke, February 2025 | Group Project: Semester 2 AY2024/5 
In this section, each patient's visit will be recorded in the system, which will be 
able to provide historical reports of medical consultations and treatments.  This 
section should collect patient information, visit information, timestamp (which 
includes date and time of visit), doctor-assigned purpose of visit, diagnosis, 
treatment/prescriptions and notes and any other observations made by the doctor. 
4. Patient Management 
a. Add, Edit, Delete and View Patient Records 
b. Use properly formatted GUI. 
c. Digital patient registration with personal details and medical history. 
d. Secure storage and retrieval of patient records using Random Access File. 
e. Automatic updates after each visit or medical procedure. 
This section is used to manage patient records.  It includes adding a new patient record, 
editing, viewing, or deleting a patient record (based on special circumstances e.g., 
duplicated patient records). The patient data will consist of patient number, first name, 
middle name (optional), last name, trn, date of birth, date of first visit, gender, marital 
status, next of kin, next of kin contact number, email address contact number, and any 
prior medical history. 
5. Appointment Scheduling 
a. Doctor and Patient Dashboards 
b. Doctors can schedule & manage appointments 
c. Patients can book appointments. 
d. Patients can cancel or reschedule an appointment. 
e. Displays available time slots 
f. 
Save appointments to Random Access Files. 
g. Online booking system with real-time availability updates. 
h. Queue management for walk-in patients. 
In this section, there should be a dashboard for medical professionals and one for 
patients. The latter should have a list view to show upcoming appointments. 
Patients should also have functionalities that allow them to cancel or reschedule 
appointments. 
6. Medication Records and Tracking 
a. Store patient prescriptions and medication history. 
b. Connect patients with prescribed medications 
c. Store data in a Random-Access File. 
d. Digital inventory of all medications, expiry tracking, and stock alerts. 
e. Prescription management with doctor approvals and pharmacist coordination. 
In this section, there should be a prescription panel.  A table view is required to 
show the prescription history of a patient. It should allow for easy addition, 
editing, and deletion of prescriptions. 
The Core Features of the System include the following: 
Once a doctor generates a prescription in the MWMS, it is sent directly to the pharmacy for the 
pharmacist to fill. Once the medication is ready to be collected, the patient is notified by email 
and a phone call that the medication is filled and that the patient can collect it as soon as 
possible. 
The project should create the following classes; however, it is also left to your interpretation 
where you can create additional classes to make the MWMS as efficient as possible. 
• Define a class called User that contains the following properties: userNumber of type int, 
username of type string, password of type string, and jobRole of type Role. The 
userNumber cannot be more than 5 digits. The roles may be one of the following: Admin, 
Doctor, Receptionist, Nurse, Dietitian, etc. The Role class includes the roleNumber 
datatype int, roleName data type string and roleLevel datatype string.   
• The Patient class should have the following attributes: patientNumber with datatype int, 
patientName with datype string, patientDob with datatype Date, patientGender with 
2 
Created by: Rorron A. Clarke, February 2025 | Group Project: Semester 2 AY2024/5 
datatype string, which is either male or female, and patient phone number with datatype 
Phone.  A Phone class should have the following attributes: areaCode datatype int , 
exchange datatype int, and lineNumber datatype int.  An address has data Address.  The 
Address class has a street number, a street name, parish and country.  
• The Appointment class has the following attributes appointmentNumber with datatype 
int, patientNumber with datatype int, doctorNumber with datatype int, appointmentDate 
with datatype Date, and status with datatype, the status should be one of the following: 
“Scheduled”,”Completed” or “Cancelled.  
• The Prescription class should have the following attributes: prescriptionNumber with 
datatype int, patientNumber with datatype int, doctorNumber with datatype int, 
medication with datatype string, dosage with datatype double, and prescritionDate with 
datatype date. The Date class should have the following attributes: day with datatype int, 
month with datatype int, and year with datatype int. 
Deliverables should be: 
1. Source code (upload to the desired locations based on your practical lecturer's 
instructions). 
2. Provide an executable application. 
3. Provide a user manual for the executable application. 
4. Provide a group report of each member's contribution. 
5. Provide a PowerPoint presentation with snapshots of the running program. 
6. Present the PowerPoint presentation with each group member presenting as part of the 
presentation. 
7. Demonstrate a working group project and be able to answer questions (interview) about 
the overall project. 
