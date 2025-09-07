# 🚀 SeleniumUsingGenAI

A robust **Selenium + TestNG + ExtentReports** automation framework designed for:
- **Cross-browser testing** (Chrome, Firefox, extendable to Edge)
- **Local and Selenium Grid execution** (via Docker)
- **CI/CD integration with Jenkins + GitHub**
- **Detailed HTML reports (ExtentReports)**

---

## 📂 Project Structure

```
SeleniumUsingGenAI/
│── src/
│   ├── main/java/com/company/framework/
│   │   ├── base/        # Base classes (WebDriver setup, TestBase)
│   │   ├── drivers/     # DriverFactory, Grid setup
│   │   ├── pages/       # Page Object Models
│   │   ├── reporting/   # Extent Report manager
│   │   └── utils/       # Helpers (config, waits, logger, etc.)
│   ├── test/java/com/company/tests/
│   │   ├── ui/          # UI tests
│   │   └── regression/  # Regression suite
│── reports/             # Extent reports + screenshots
│── docker-compose.yml   # Selenium Grid setup
│── pom.xml              # Maven dependencies
│── testng.xml           # TestNG suite runner
│── Jenkinsfile          # CI/CD pipeline
│── README.md            # Project documentation
```

---

## ⚙️ Setup & Installation

### 🔧 Prerequisites
- **Java 11+**  
- **Maven 3.8+**  
- **Docker + Docker Compose** (for Grid execution)  
- **Jenkins (optional, for CI/CD)**  

### 📥 Clone the repository
```bash
git clone https://github.com/<your-username>/SeleniumUsingGenAI.git
cd SeleniumUsingGenAI
```

### 📦 Install dependencies
```bash
mvn clean install -DskipTests
```

---

## ▶️ Running Tests

### 🔹 Run on Local Browser
```bash
mvn clean test -Dbrowser=chrome -Dexecution.mode=LOCAL
mvn clean test -Dbrowser=firefox -Dexecution.mode=LOCAL
```

### 🔹 Run on Selenium Grid (Docker)
1. Start Selenium Grid:
   ```bash
   docker-compose up -d
   ```
   This starts:
   - Selenium Hub → `http://localhost:4444`
   - Chrome Node
   - Firefox Node

2. Run tests on Grid:
   ```bash
   mvn clean test -Dbrowser=chrome -Dexecution.mode=GRID -Dgrid.url=http://localhost:4444/wd/hub
   mvn clean test -Dbrowser=firefox -Dexecution.mode=GRID -Dgrid.url=http://localhost:4444/wd/hub
   ```

3. Stop Grid:
   ```bash
   docker-compose down
   ```

---

## 📊 Reporting

### ✅ Extent Report
- Generated under:  
  ```
  reports/ExtentReport_<timestamp>.html
  ```
- Includes:
  - Test pass/fail status  
  - Screenshots on failure  
  - Pass percentage summary  
  - Environment details (browser, execution mode, date/time)  

### ✅ Jenkins Report Publishing
- Uses `publishHTML` plugin  
- Report is available under **Build → Extent Report**  

---

## ⚡ CI/CD with Jenkins

### Jenkinsfile Pipeline
- **Checkout** code from GitHub  
- **Start Selenium Grid** via Docker  
- **Run tests** (`mvn test`)  
- **Publish JUnit & Extent Reports**  
- **Stop Selenium Grid**  

### GitHub → Jenkins Flow
1. Push code:
   ```bash
   git push origin main
   ```
2. GitHub Webhook / SCM Polling triggers Jenkins build  
3. Jenkins executes pipeline on remote agent (AWS/Azure, etc.)  
4. Results available in Jenkins UI  

---

## ☁️ Jenkins Remote Agent Setup (AWS Example)

### 1️⃣ Launch EC2 Instance
- Amazon Linux 2 / Ubuntu  
- Security Group → allow **port 22 (SSH)**, **8080 (Jenkins)**, and **4444 (Grid)**  
- Install:
  ```bash
  sudo yum update -y
  sudo yum install -y java-11-openjdk git docker
  sudo usermod -aG docker ec2-user
  ```

### 2️⃣ Install Maven
```bash
wget https://downloads.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
tar xzvf apache-maven-3.8.6-bin.tar.gz
sudo mv apache-maven-3.8.6 /opt/maven
echo "export PATH=/opt/maven/bin:$PATH" >> ~/.bashrc
source ~/.bashrc
```

### 3️⃣ Install Jenkins Agent
- In Jenkins master: **Manage Jenkins → Nodes → New Node**  
- Choose **Permanent Agent**  
- Assign label: `remote-aws-agent`  
- Configure Remote root dir: `/home/ec2-user/jenkins`  
- Launch method: SSH → provide EC2 private key  

### 4️⃣ Validate Node
- Node comes online in Jenkins  
- Check with:
  ```bash
  java -version
  mvn -v
  docker -v
  ```

### 5️⃣ Run Pipeline
- Jenkinsfile will pick agent:  
  ```groovy
  agent { label 'remote-aws-agent' }
  ```

---

## 🌐 Cross-Browser Execution
Configure `browser` parameter:
- `-Dbrowser=chrome`
- `-Dbrowser=firefox`
- (Extendable: Edge, Safari, etc.)

Execution mode:
- `-Dexecution.mode=LOCAL` → run directly on developer machine  
- `-Dexecution.mode=GRID` → run on Dockerized Selenium Grid  

---

## 🛠️ Future Enhancements
- Integrate with **Allure Reports** alongside Extent  
- Support **parallel test execution** via TestNG + Grid  
- Cloud execution support (BrowserStack, Sauce Labs, LambdaTest)  
- Add **API + DB testing modules**  

---

## 🤝 Contributing
1. Fork repo  
2. Create feature branch  
3. Commit changes  
4. Push branch & raise PR  

---

## 📜 License
Licensed under [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0).  
