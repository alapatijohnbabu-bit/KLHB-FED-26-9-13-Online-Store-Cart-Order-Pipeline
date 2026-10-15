$ErrorActionPreference = "Stop"
New-Item -ItemType Directory -Force -Path trash | Out-Null
$files = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d trash $files
if ($LASTEXITCODE -ne 0) { throw "Compilation failed." }
java -cp trash com.store.Main
