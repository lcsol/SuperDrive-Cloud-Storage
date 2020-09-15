# Super*Drive Cloud Storage
Super*Drive is a file storage system with features:

1. **Simple File Storage:** Upload/download/remove files
2. **Note Management:** Add/update/remove text notes
3. **Password Management:** Save, edit, and delete website credentials.   

## Key Technologies

1. The back-end with Spring Boot
2. The front-end with Thymeleaf
3. Application tests with Selenium

### The Back-End

1. Managing user access with Spring Security
 - Unauthorized users can only access the login and signup pages. 
 - Passwords are hashed before storing in the database.  


2. Handling front-end calls with controllers
 - Controllers bind application data and functionality to the front-end. Use Spring MVC's application model to identify the templates served for different requests and populating the view model with data needed by the template. 
 - Handle exceptions and display error messages to the user. 


3. Making calls to the database with MyBatis mappers
 - Use POJOs (Plain Old Java Objects) with fields that match the names and data types in the database schema. 
 - Use MyBatis mapper interfaces to connect model classes with database data. The mappers have methods that represent specific SQL queries and statements required by the functionality of the application. They support CRUD (Create, Read, Update, Delete) operations for their respective models.


### The Front-End
Implemented with Thymeleaf and Bootstrap 4:

1. Login page
 - Everyone can access to this page, and users can use this page to login to the application. 
 - Show login errors, like invalid username/password, when arise. 


2. Sign Up page
 - Everyone can access to this page, and potential users can use this page to sign up for a new account. 
 - Show signup errors on the page when they arise.


3. Home page
The home page hosts the three main functions of the application, which are presented as three tabs that can be clicked through by the user:


 i. Files
  - The user can upload files and see any files they previously uploaded. 
  - The user can view/download or delete previously-uploaded files.
  - Display any errors related to file actions.


 ii. Notes
  - The user can create notes and see a list of the notes they have previously created.
  - The user can edit or delete previously-created notes.

 iii. Credentials
 - The user can store credentials for specific websites and see a list of the credentials they've previously stored. The displayed credentials passwords are encrypted.
 - The user can view/edit or delete individual credentials. When the user views the credential, they can see the unencrypted password.


### Testing
 Use Junit and Selenium to test user-facing functionalities. Tests including:

1. Tests for user signup, login, and unauthorized access restrictions.

2. Tests for note creation, viewing, editing, and deletion.

3. Tests for credential creation, viewing, editing, and deletion.

