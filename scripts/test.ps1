$ErrorActionPreference = "Stop"
New-Item -ItemType Directory -Force -Path bin | Out-Null
$files = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d bin $files
if ($LASTEXITCODE -ne 0) { throw "Production compilation failed." }
javac -cp bin -d bin src/test/java/com/store/StoreSmokeTest.java
if ($LASTEXITCODE -ne 0) { throw "Test compilation failed." }
java -cp bin com.store.StoreSmokeTest
