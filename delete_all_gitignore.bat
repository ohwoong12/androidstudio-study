@echo off
setlocal

REM ���� ���丮�� ��Ʈ .gitignore ���� ���� ��� ����
set "ROOT_DIR=%cd%"
set "ROOT_GITIGNORE=%ROOT_DIR%\.gitignore"

echo ?? ���� ���� �� .gitignore ���� ���� ��...

for /r %%f in (*.gitignore) do (
    REM ���� .gitignore ������ ��Ʈ�� �ִ� ���� ����
    if /I not "%%~f"=="%ROOT_GITIGNORE%" (
        echo ?? ���� ��: %%f
        del "%%f"
    )
)

echo ? ��Ʈ�� �����ϰ� ���� ���� .gitignore�� ���� �Ϸ�!
pause
