
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





## 👀 Screenshots

***Description of the project:***
<p align="center">
  <img src="https://github.com/user-attachments/assets/27573c54-cd4e-478e-801f-46f5d4430a93" width="600" height="700">
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


