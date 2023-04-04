call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startBrowser
echo runcrud error
goto fail

:startBrowser
start chrome http://localhost:8080/crud/v1/task/tasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.