@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0
setlocal
set GRADLE_OPTS="-Dorg.gradle.appname=%~n0"

if not exist "%HOME%\.gradle\wrapper\dists" mkdir "%HOME%\.gradle\wrapper\dists"

java -jar "%HOME%\.gradle\wrapper\gradle-wrapper.jar" %*
endlocal
