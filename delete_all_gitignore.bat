@echo off
setlocal

REM 현재 디렉토리의 루트 .gitignore 파일 절대 경로 저장
set "ROOT_DIR=%cd%"
set "ROOT_GITIGNORE=%ROOT_DIR%\.gitignore"

echo ?? 하위 폴더 내 .gitignore 파일 정리 중...

for /r %%f in (*.gitignore) do (
    REM 현재 .gitignore 파일이 루트에 있는 경우는 제외
    if /I not "%%~f"=="%ROOT_GITIGNORE%" (
        echo ?? 삭제 중: %%f
        del "%%f"
    )
)

echo ? 루트는 보존하고 하위 폴더 .gitignore만 삭제 완료!
pause
