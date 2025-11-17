# Patient Portal Management System 
The **"Doctor App"** is a Java-based software application designed to facilitate communication and appointment coordination between patients and healthcare providers. The system provides a structured enviroment where users (patients, doctors, and admins) can manage appointments securely and efficiently. 

The primary aim of this project is to demonstrate the application of object-oriented programming principles and layered software architecture within a healthcare-oriented domain. 

---

## System Overview
The Doctor App incorporates a modular architecture consisting of:  
- Models (User, Patient, Doctor, Appointment)
- Controllers(UserContoller, AppoinmentController)
- Services(UserService, AppointmentService)

Each module satisfys a distinct responsibility, which ultimately contributes to a maintainable and expandable system. 

---

## Objectives 
- To implement secure user and appointment management.
- To demonstrate inheritance and polymorphism through entities
- Provide services that handle business logi separately from controllers.
- Simulate healthcare workflows such as scheduling, cancellations, and appointment views.

---

## System Architecture
This project follows a Model-Service Controller (MSC) architecture: 
- Models represent data entities and include attributes such as user information or appointment details.
- Services contain core business logic such as user authentication or appointment scheduling.
- Controllers act as middle-men between models/services and the external interface, processing requests and returning responses.

### Models
- **User** - base class with username, password, email, and role.
- **Patient** - extends user, adds patient-specific information
- **Doctor** - extends user, adds doctor-specific information
- **Appointment** - contains appointment metadata, assigned patient, doctor, and time.
  
### Services 
- **UserService**
  - Register users
  - Authenticate users by hashing
  - Prevent duplicate users

- **AppointmentService**
  - Create, update, and cancel appointments
  - Check availability
  - Associate appointments with specific doctors/patients

### Controllers
- **UserController**
  - Routes user operations to UserService
  - Handles registration and login

- **AppointmentController**
  - Routes scheduling requests to AppointmentService
  - Provides interfaces for creating, editing, and deleting appointments
    
### Project Structure
      src/
       └── edu.secourse.patientportal
            ├── models/
            │     ├── User.java
            │     ├── Patient.java
            │     ├── Doctor.java
            │     └── Appointment.java
            │
            ├── services/
            │     ├── UserService.java
            │     └── AppointmentService.java
            │
            └── controllers/
                  ├── UserController.java
                  └── AppointmentController.java

---

## Key Features 
- Secure user authentication/verification
- Role-based user modeling
- Appointment creation, modification, and cancellation
- Automatic assignment of unique identifiers
- Date and time management 
- Hashing-based login verification

---

## Conclusion
The Doctor App demonstrates fundamental software developent principles, including modular design, encapsulation, and service abstraction. It serves both as a learning tool and as a foundation for more complex applicaitons. 
