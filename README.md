
# 🤖AI Code Reviewer

Built a GenAI-powered full-stack code review platform 🤖 that allows users to upload files 📂 or paste source code directly for AI-based analysis and optimization. Developed with HTML, CSS, JavaScript 🎨 on the frontend and Spring Boot ☕ on the backend, the system integrates Grok AI with the Qwen 32B model 🧠 to detect bugs, suggest fixes, and generate optimized code solutions. Implemented version tracking and code comparison 🔄 by storing original and improved code versions in a NoSQL database ♻, enabling users to maintain complete code history and review changes over time.
## 💥 All Documented API's

#### 🔐 Rest API's

```http
  POST - /api/code/upload
  POST - /api/code/analyze/{submissionId}
  GET -  /api/code/version/{submissionId}
  POST - /api/code/version/{submissionId}/new
  GET -  /api/code/submissions/{userId}
  POST - /api/code/campare
```
## 🔄 Project Complete Workflow
**👨‍💻 User Workflow**
- Upload code file 📂 or paste source code ✍
- Submit code for AI analysis 🤖
- AI reviews the code and detects issues ⚠
- Optimized and corrected code is generated ✅
- User can copy the improved solution 📋

**🧠 AI Processing Workflow**
- Receive source code from frontend 🌐
- Analyze code using **Grok AI + Qwen 32B model 🧠**
- Detect bugs, bad practices, and optimization opportunities 🔍
- Generate fixed and optimized code 🚀

**🗄 Version Management Workflow**
- Store original code in NoSQL database ♻
- Store optimized/fixed code as a new version 🔄
- Maintain complete code history 📜
- Compare original and updated code versions anytime ⚡

**⚙ Backend Workflow**
- Frontend sends code to Spring Boot backend ☕
- Backend processes API requests and AI communication 🔗
- Responses are returned to the frontend with suggestions and fixes 📩
- All versions and history are managed securely in the database 🔐



## 👀 Screenshots:

**Front Page**
***Drag code files:***
<p align="center">
   <img width="720" height="400" alt="Image" src="https://github.com/user-attachments/assets/94535eb4-0ea5-40da-afe8-10a39fc28167" />
</p>

***Copy-paste code:***
<p align="center">
  <img width="720" height="400" alt="Screenshot (44)" src="https://github.com/user-attachments/assets/8afbc326-88aa-4cb6-8c52-dbc09dc47a55" />
</p>

***AI-generated code:***
<p align="center">
  <img width="720" height="400" alt="Screenshot (40)" src="https://github.com/user-attachments/assets/9b726841-4b2c-47bf-aba3-4655038bf95b" />
</p>

***Original code:***
<p align="center">
  <img width="720" height="400" alt="Screenshot (41)" src="https://github.com/user-attachments/assets/0992e0c9-61d8-4388-b42e-eb1cae3067ea" />
</p>

***All code submissions:***
<p align="center">
  <img width="720" height="400" alt="Screenshot (42)" src="https://github.com/user-attachments/assets/c8ecf043-6a43-497e-8c10-56af7dfb4593" />
</p>

***Code comparison:***
<p align="center">
  <img width="720" height="400" alt="Screenshot (43)" src="https://github.com/user-attachments/assets/78e9cc2d-143b-4629-b3e2-c38c0da66df0" />
</p>





## 🕹 Technologies Used

**⚙ Backend**
- Java
- Spring Boot
- REST APIs
- Spring Data JPA

**🎨 Frontend**
- HTML
- CSS
- JavaScript

**🗄 Database**
- NoSQL Database

**👾 AI Integration**
- Grok AI
- Qwen 32B Model



## 🔗 Links
[![portfolio](https://img.shields.io/badge/github-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/IamPawan777)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/pawanbisht2000/)



## Happy Coding with AI Code Reviewer! 🚀🤖


