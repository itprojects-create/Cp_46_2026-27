# 🚀 SmartHire AI: Automated Multi-Stage Recruitment & Proctoring Ecosystem

<p align="center">
  <img src="https://img.shields.io/badge/Angular-17.0.0-DD0031?style=for-the-badge&logo=angular&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Python-3.13-3776AB?style=for-the-badge&logo=python&logoColor=white" />
  <img src="https://img.shields.io/badge/OpenCV-5.0.0-5C3EE8?style=for-the-badge&logo=opencv&logoColor=white" />
  <img src="https://img.shields.io/badge/FastAPI-0.110+-009688?style=for-the-badge&logo=fastapi&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Google_Gemini-GenAI-4285F4?style=for-the-badge&logo=google&logoColor=white" />
</p>

---

## 📌 Project Overview
**SmartHire AI** is an enterprise-grade, cloud-ready automated hiring and talent assessment platform developed as a **Final Year B.E. Capstone Project (Group 46)**. 

Traditional technical hiring workflows suffer from high recruiter overhead (30+ hours per hire), leaked static question banks, and rampant cheating during remote testing. **SmartHire AI** solves these challenges by combining:
1. **Automated ATS Resume Parsing & Keyword Gap Analytics**
2. **Dynamic MCQ Generation via Google Gemini LLM**
3. **Real-Time Computer Vision Proctoring (OpenCV + FastAPI)**
4. **Native Multi-Language Code Execution Sandbox (Java, Python, C++, JS)**
5. **In-Browser WebRTC Peer-to-Peer Technical Video Interviews**

---

## 👥 Academic & Project Details
* **Project Name:** SmartHire AI
* **Academic Year:** 2026 – 2027
* **Group Number:** Group 46
* **Institute:** Department of Information Technology, Vivekanand Education Society's Institute of Technology (VESIT), University of Mumbai
* **Project Guide:** Prof. Rohini Sawant Ma'am
* **Team Members:**
  * **Gitesh Peswani**
  * **Leena Hinduja**
  * **Chirag Krishnani**
  * **Dipesh Nawani**

---

## 🏗️ System Architecture

```text
                               +--------------------------------------------+
                               |         Angular 17 Client (SPA)            |
                               |  - Candidate Portal   - Recruiter Console  |
                               |  - Live Coding IDE    - WebRTC Video Room  |
                               +---------------------+----------------------+
                                                     |
                                   +-----------------+-----------------+
               WebSocket Live Frames |                                   | HTTPS / REST (JSON)
                                     v                                   v
+------------------------------------+-----------+   +-------------------+--------------------+
|  PythonProctor Microservice (Port 5000)        |   |  Spring Boot Core API (Port 8082)       |
|  - FastAPI & Uvicorn                           |   |  - Spring Security 6 & BCrypt           |
|  - Low-Light Histogram Equalization            |   |  - Round 1: ATS Parser (PDFBox & POI)   |
|  - Dual Haar Cascades (Face & Profile)         |   |  - Round 2: Gemini LLM MCQ Coordinator  |
|  - 22% Gaze Offset Heuristic (Looking Away)    |   |  - Round 3: Polyglot Execution Sandbox  |
|  - Canny Edge Phone Contour Detection (1.5-2.5)|   |  - Round 4: WebRTC REST Signaling       |
+------------------------------------------------+   +-------------------+--------------------+
                                                                         |
                                                   +---------------------+--------------------+
                                                   |                                          |
                                                   v                                          v
                                    +--------------+-------------+             +--------------+-------------+
                                    |     MySQL 8.0 Database     |             |    Google Gemini Pro API   |
                                    |  - 14 Normalized Tables    |             |  - Dynamic Question        |
                                    |  - Relational Funnel State |             |    Synthesis on the Fly    |
                                    +----------------------------+             +----------------------------+
```

---

## ✨ Four-Stage Automated Recruitment Funnel

### 📄 Round 1: Automated ATS Resume Analysis
* **Engine:** Apache PDFBox (PDF) & Apache POI (DOCX) text extractors.
* **Algorithm:** Lowercases, sanitizes, and applies regex tokenization (`\W+`) to candidate CVs.
* **Matching:** Computes set-theoretic intersection against recruiter job requirements.
* **Output:** Generates a real-time match percentage along with missing critical technical keyword suggestions.

### 🧠 Round 2: Dynamic GenAI MCQ Assessment & OpenCV Proctoring
* **Question Generation:** Integrated with **Google Gemini LLM** to synthesize fresh, topic-specific questions on demand, eliminating memorized question bank leaks. Includes deterministic local fallbacks.
* **Proctoring Engine (`PythonProctor`):**
  * **Face Tracking:** Dual-cascade detection (Frontal + Profile fallback).
  * **Candidate Absence / Multi-Person:** Flags `NO_PERSON` and `MULTIPLE_PEOPLE`.
  * **Gaze Deviation:** Evaluates eye ROI; if eyes are missing and face center deviates $>22\%$ from frame width, alerts `LOOKING_AWAY`.
  * **Device Detection:** Canny edge detection + quadrilateral contour aspect-ratio filtering ($1.5 \le \text{AR} \le 2.5$) flags mobile phones instantly.

### 💻 Round 3: Live Sandboxed Multi-Language Code Execution
* **Supported Languages:** Java 21, Python 3, C++17 (`g++`), JavaScript (`node`).
* **Deadlock-Free I/O:** Uses an asynchronous `ExecutorService` thread pool to drain `stdout` and `stderr` streams in parallel, eliminating OS pipe buffer deadlocks.
* **Defensive Sandboxing:**
  * Strict millisecond timeout handling via `process.destroyForcibly()`.
  * 64 KB maximum output buffer cap (blocks infinite print attacks).
  * JVM memory capped at `-Xmx256m`.

### 🎥 Round 4: WebRTC Live Technical Interview & Excel Reporting
* **Conferencing:** Native in-browser audio/video streaming, desktop screen sharing, and real-time chat.
* **Signaling:** Custom REST-based SDP offer/answer and ICE candidate negotiation in Spring Boot.
* **Background Security:** Proctoring microservice continues facial tracking during the live call.
* **Recruiter Export:** Recruiter dashboard exports complete multi-round scorecards into formatted Excel (`.xlsx`) spreadsheets via Apache POI.

---

## 🛠️ Technology Stack

| Layer | Technologies Used |
| :--- | :--- |
| **Frontend** | Angular 17, TypeScript 5.2, HTML5 Canvas, WebRTC MediaStream API, RxJS |
| **Backend API** | Java 21, Spring Boot 3.3.5, Spring Security 6, Spring Data JDBC, JavaMailSender |
| **Execution Sandbox** | Java `ProcessBuilder`, Polyglot CLI compilers (`javac`, `g++`, `python`, `node`) |
| **Proctoring Microservice** | Python 3.13, FastAPI, Uvicorn, OpenCV (`cv2`), NumPy, WebSockets |
| **Artificial Intelligence** | Google Gemini Generative AI (Google AI Studio REST API) |
| **Document Processing** | Apache PDFBox 2.0.30 (PDF), Apache POI 5.2.5 (DOCX / XLSX) |
| **Database** | MySQL 8.0 Community Server (InnoDB, 14 Normalized Tables) |

---

## 🚀 Getting Started & Local Setup

### Prerequisites
* **Java:** JDK 21+
* **Node.js:** v18 or v20 & Angular CLI (`npm install -g @angular/cli@17`)
* **Python:** 3.10+ (Recommended 3.11 - 3.13)
* **Database:** MySQL 8.0 Server running on port `3306`

---

### Step 1: Database Initialization
1. Open MySQL workbench or CLI and execute the root schema script:
   ```bash
   mysql -u root -p < smarthire_ai.sql
   ```
2. Verify that the `smarthire_ai` database is created with 14 tables.

---

### Step 2: Configure & Start Backend (Spring Boot)
1. Open `Backend/src/main/resources/application.properties` and verify your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/smarthire_ai
   spring.datasource.username=root
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```
2. Start the Spring Boot application:
   ```bash
   cd Backend
   ./gradlew bootRun
   ```
   *Backend server runs on:* `http://localhost:8082`

---

### Step 3: Start Python Proctoring Microservice
1. Navigate to `PythonProctor` directory:
   ```bash
   cd PythonProctor
   ```
2. Create and activate a virtual environment:
   ```bash
   python -m venv venv
   # On Windows:
   .\venv\Scripts\activate
   # On Linux/Mac:
   source venv/bin/activate
   ```
3. Install dependencies and start the microservice:
   ```bash
   pip install -r requirements.txt
   python main.py
   ```
   *Proctoring service runs on:* `http://localhost:5000` *(WebSocket on `ws://localhost:5000/ws/proctor`)*

---

### Step 4: Start Frontend (Angular SPA)
1. Navigate to the `Frontend` directory:
   ```bash
   cd Frontend
   ```
2. Install npm dependencies:
   ```bash
   npm install
   ```
3. Start the Angular dev server with proxy:
   ```bash
   ng serve --proxy-config proxy.conf.json
   ```
   *Application UI accessible at:* `http://localhost:4200`

---

## 🔒 Security & Privacy Notice
* Sensitive developer tokens, `.env` files, build caches (`target/`, `build/`, `.gradle/`, `node_modules/`, `venv/`), and academic drafts (`Blackbook/`, `copyright_audit/`) are explicitly excluded via `.gitignore` to maintain clean repository hygiene.
* Video feeds processed during proctoring are analyzed in-memory as ephemeral frame buffers and are not persisted to disk without candidate consent.

---

## 📜 License & Intellectual Property
This software has been developed as an original academic capstone project at **VESIT, University of Mumbai**. All rights reserved by the project team. Distributed under academic fair use and permissive open-source framework guidelines.
