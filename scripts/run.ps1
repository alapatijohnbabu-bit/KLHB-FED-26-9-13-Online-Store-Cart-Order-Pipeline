$ErrorActionPreference = "Stop"
New-Item -ItemType Directory -Force -Path bin | Out-Null
$files = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d bin $files
if ($LASTEXITCODE -ne 0) { throw "Compilation failed." }
java -cp bin com.store.Main
