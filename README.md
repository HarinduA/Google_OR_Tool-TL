# Tyre Scheduler using Google OR-Tools

This Java project uses Google OR-Tools' Constraint Programming (CP-SAT) solver to schedule tyre building tasks across multiple machines. The goal is to assign tyres to machines such that the overall time to build all tyres (makespan) is minimized and no two tyres are built on the same machine at the same time.

## 📦 Prerequisites

- Java 8 or higher
- Maven or Gradle (for dependency management)
- Google OR-Tools Java library

## 🚀 How It Works

- The problem considers:
  - A fixed number of **tyres** to be built.
  - A set of **machines** available to build those tyres.
  - Each tyre takes a fixed amount of time to build.
- The CP model ensures:
  - No two tyre builds overlap on the same machine.
  - The tyre tasks are assigned to machines and scheduled within a time horizon.
  - The objective is to **minimize the total completion time** (makespan).

## ⚙️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/tyre-scheduler.git
   cd tyre-scheduler
