@echo off
setlocal enabledelayedexpansion

echo STARTING TEST_TASK PROJECT (Docker)

REM Проверка наличия Docker
docker --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo Docker is not installed or not found in PATH.
    pause
    exit /b 1
)

REM Клонирование репозитория (если не существует)
IF NOT EXIST "test_task" (
    echo Repository is cloning...
    git clone https://github.com/dshparko/test_task.git
) ELSE (
    echo Repository is already cloned
)

cd test_task

REM Поднятие сервисов
echo Compile and run docker-compose...
docker-compose down --volumes --remove-orphans
docker-compose build
docker-compose up --detach

echo Waiting for containers to start...
timeout /t 10

REM Проверка работы user-service
echo Availability check http://localhost:8083 ...
powershell -Command "(Invoke-WebRequest http://localhost:8083 -UseBasicParsing).StatusCode" >nul 2>&1

IF %ERRORLEVEL% EQU 0 (
    echo The user-service is running successfully!
) ELSE (
    echo Could not connect to user-service. Check logs.
)


echo Done. To stop services, type:
echo     docker-compose down -v

pause