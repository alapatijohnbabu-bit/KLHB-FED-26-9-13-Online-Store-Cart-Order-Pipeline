$ErrorActionPreference = "Stop"
New-Item -ItemType Directory -Force -Path trash | Out-Null
$files = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d trash $files
if ($LASTEXITCODE -ne 0) { throw "Production compilation failed." }
javac -cp trash -d trash src/test/java/com/store/StoreSmokeTest.java
if ($LASTEXITCODE -ne 0) { throw "Test compilation failed." }
java -cp trash com.store.StoreSmokeTest
