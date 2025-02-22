# PMS-ProjectManagementSystem 
 
A web-based Project Management System built with Spring Boot, Spring Security, and Thymeleaf. Features role-based access control: admins can manage projects, tasks, and employees, while users can only view them. Includes secure form-based login and a polished UI. 
 
## Features 
- **Role-Based Access**: Admins manage all; users view only. 
- **Secure Login**: Form-based authentication with BCrypt encryption. 
- **UI**: Responsive Thymeleaf templates with CSS styling. 
- **Database**: JPA with configurable backend (e.g., H2, MySQL). 
 
## Technologies 
- **Backend**: Spring Boot, Spring Security, Spring Data JPA 
- **Frontend**: Thymeleaf, HTML, CSS 
- **Database**: H2 (default), adaptable to MySQL/PostgreSQL 
 
## Setup Instructions 
1. **Prerequisites**: Java 17+, Maven, Git 
2. **Clone the Repository**: 
   ```bash 
   git clone https://github.com/AshokMulinti/PMS-ProjectManagementSystem.git 
   cd PMS-ProjectManagementSystem 
   ``` 
3. **Configure Database**: Update `application.properties` with your database settings (e.g., H2 or MySQL). 
4. **Build and Run**: 
   ```bash 
   mvn clean install 
   mvn spring-boot:run 
   mvn spring-boot:run 
   ``` 
5. **Access**: Open `http://localhost:8080` in your browser. 
   - Default Admin: `admin@gmail.com` / `admin@123` 
   - Register users at `/register`. 
 
## Usage 
- **Admin**: Log in with admin credentials to manage resources. 
- **Users**: Log in to view projects, tasks, and employees. 
 
## License 
MIT License (or specify your preferred license). 
