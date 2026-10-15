# Online Store Cart & Order Pipeline

A Java-based semester project demonstrating an online shopping workflow, including product and inventory management, shopping-cart operations, simulated checkout, and order-status tracking.

## Project information

- **Project:** Online Store Cart & Order Pipeline
- **Language:** Java
- **JDK:** 17 or later
- **Interface:** Java Swing desktop application (to be integrated by the team)

## Repository structure

```text
src/       Java source and tests
bin/       Local compiled Java classes; do not commit .class files
docs/      Requirements, architecture, diagrams and testing plan
data/      Sample data and data-source notes
reports/   Project reports and status
results/   Verified outputs and test evidence
scripts/   Run and test scripts
```

## Setup

Install JDK 17+ and verify:

```bash
java -version
javac -version
```

Clone the repository:

```bash
git clone https://github.com/alapatijohnbabu-bit/KLHB-FED-26-9-13-Online-Store-Cart-Order-Pipeline.git
cd KLHB-FED-26-9-13-Online-Store-Cart-Order-Pipeline
```

Compile and run after the Java source files have been added:

**Linux/macOS**
```bash
mkdir -p bin
find src/main/java -name '*.java' -print > /tmp/cartflow-sources.txt
javac -d bin @/tmp/cartflow-sources.txt
java -cp bin com.store.Main
```

**Windows PowerShell**
```powershell
New-Item -ItemType Directory -Force -Path bin | Out-Null
$files = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d bin $files
java -cp bin com.store.Main
```

You can also use `scripts/run.sh` or `scripts/run.ps1`. The test scripts are `scripts/test.sh` and `scripts/test.ps1`; they can be used once the corresponding Java source and test files are present.

## Team workflow

Each team member should work on an assigned module in their own branch, commit meaningful changes using their own GitHub account, and open a pull request or merge their branch according to the team's workflow.

## Project scope

This is an educational prototype. Payment processing, if included, is simulated; do not treat it as a production e-commerce system. Keep documentation aligned with the code that is actually implemented.

## Academic use

This repository is maintained for a semester project. Keep required project documents, verified test evidence, and team contributions up to date.
