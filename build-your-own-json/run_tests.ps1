# Compile and run all step tests.
# Usage: .\run_tests.ps1
# Requires: javac and java on PATH, sources compiled to out/

$SRC = "$PSScriptRoot\src"
$OUT = "$PSScriptRoot\out"
$TESTS = "$PSScriptRoot\tests"
$PASS = 0
$FAIL = 0

Write-Host "Compiling..."
New-Item -ItemType Directory -Force $OUT | Out-Null
$sources = Get-ChildItem -Recurse -Filter "*.java" $SRC | ForEach-Object { $_.FullName }
javac -d $OUT $sources
if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed." -ForegroundColor Red
    exit 1
}

function Run-Test($file, $expectedExit) {
    $result = java -cp $OUT Main $file 2>&1
    $actual = $LASTEXITCODE
    if ($actual -eq $expectedExit) {
        Write-Host "  PASS  $file (exit $actual)" -ForegroundColor Green
        return 1
    } else {
        Write-Host "  FAIL  $file (expected exit $expectedExit, got $actual)" -ForegroundColor Red
        if ($result) { Write-Host "         $result" }
        return 0
    }
}

foreach ($step in 1..4) {
    Write-Host "`nStep ${step}:"
    $stepDir = "$TESTS/step$step"
    if (Test-Path "$stepDir/valid.json") {
        $r = Run-Test "$stepDir/valid.json" 0
        $PASS += $r; $FAIL += (1 - $r)
    }
    if (Test-Path "$stepDir/invalid.json") {
        $r = Run-Test "$stepDir/invalid.json" 1
        $PASS += $r; $FAIL += (1 - $r)
    }
}

Write-Host "`nResults: $PASS passed, $FAIL failed"
if ($FAIL -gt 0) { exit 1 } else { exit 0 }
