Java Selenium Test Automation Framework
A Production‑Ready Hybrid Framework | Java • Selenium • TestNG • Maven • Jenkins
This repository contains a complete, scalable, industry‑standard automation framework built using Java, Selenium WebDriver, TestNG, Maven, Page Object Model (POM), Data‑Driven Testing, Extent Reports, and Jenkins CI/CD.
Designed with clean architecture and reusability in mind, this framework automates an E‑commerce application end‑to‑end and demonstrates real‑world automation engineering skills.

Key Highlights
Java + Selenium WebDriver Automation
- WebDriverManager integration
- Explicit waits
- Streams‑based element filtering
- End‑to‑end purchase flow automation
TestNG Framework Implementation
- TestNG annotations
- Groups, priorities, dependencies
- Parallel execution
- DataProvider (JSON + HashMap)
- TestNG XML suite configuration
Page Object Model (POM) Architecture
- Page Factory
- Abstract Components for reusable logic
- Clean separation of concerns
- Easy scalability for new modules
Robust Reporting & Debugging
- Extent Reports integration
- Automatic screenshots on failure
- TestNG Listeners for reporting hooks
Thread‑Safe Parallel Execution
- ThreadLocal WebDriver
- Eliminates concurrency issues
- Supports multi‑browser scaling
 CI/CD Ready
- Jenkins job integration
- Headless execution
- Parameterized builds
  
Framework Architecture
                         ┌────────────────────────────────────┐
                         │        Test Execution Layer         │
                         │────────────────────────────────────│
                         │   • TestNG Test Classes             │
                         │   • Test Methods (E2E, Smoke, etc.)│
                         │   • TestNG XML (suites, groups)     │
                         └────────────────────────────────────┘
                                        │
                                        ▼
                         ┌────────────────────────────────────┐
                         │        Page Object Layer            │
                         │────────────────────────────────────│
                         │   • Page Classes (Login, Products) │
                         │   • Page Factory Elements           │
                         │   • Action Methods                  │
                         └────────────────────────────────────┘
                                        │
                                        ▼
                         ┌────────────────────────────────────┐
                         │     Abstract Components Layer       │
                         │────────────────────────────────────│
                         │   • Reusable Methods (waits, clicks│
                         │     dropdowns, navigation)          │
                         │   • Common WebDriver utilities      │
                         └────────────────────────────────────┘
                                        │
                                        ▼
                         ┌────────────────────────────────────┐
                         │        Utilities Layer              │
                         │────────────────────────────────────│
                         │   • JSON Reader                     │
                         │   • DataProvider (HashMap)          │
                         │   • Screenshot Utility              │
                         │   • WebDriverManager Setup          │
                         │   • ThreadLocal Driver Factory      │
                         └────────────────────────────────────┘
                                        │
                                        ▼
                         ┌────────────────────────────────────┐
                         │         Core Engine Layer           │
                         │────────────────────────────────────│
                         │   • Selenium WebDriver              │
                         │   • Browser Initialization          │
                         │   • Driver Lifecycle (Before/After) │
                         └────────────────────────────────────┘
                                        │
                                        ▼
                         ┌────────────────────────────────────┐
                         │        Reporting Layer              │
                         │────────────────────────────────────│
                         │   • Extent Reports                  │
                         │   • Screenshots on Failure          │
                         │   • TestNG Listeners                │
                         └────────────────────────────────────┘
                                        │
                                        ▼
                         ┌────────────────────────────────────┐
                         │         CI/CD Layer (Jenkins)       │
                         │────────────────────────────────────│
                         │   • Headless Execution              │
                         │   • Parameterized Builds            │
                         │   • GitHub Webhook Triggers         │
                         │   • Scheduled Jobs (Nightly Runs)   │
                         └────────────────────────────────────┘


How to Run the Tests
1. Run via Maven
mvn clean test


2. Run a specific TestNG suite
mvn clean test -DsuiteXmlFile=testng.xml


3. Run in headless mode
mvn clean test -Dheadless=true



Reports
After execution, Extent Reports are generated at:
/reports/index.html


Includes:
- Pass/Fail summary
- Screenshots on failure
- Execution logs




