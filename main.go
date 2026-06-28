package main

import (
	"fmt"
	"os/exec"
	"strings"
)

func must(cmd *exec.Cmd) string {
	b, err := cmd.CombinedOutput()
	s := strings.TrimSpace(string(b))
	if err != nil {
		panic(fmt.Sprintf("%v => %s", err, s))
	}
	return s
}

func initJava() {
	abs := func(p string) string { p, _ = exec.Command("readlink", "-f", p).Output(); return strings.TrimSpace(string(p)) }
	javaHome := abs("/data/data/com.termux/files/usr/lib/jvm/java-21")
	_ = must(exec.Command("bash", "-lc", fmt.Sprintf(`export JAVA_HOME=%q; export PATH="$PATH:$JAVA_HOME/bin"; "%s/bin/gomobile" init`, javaHome, javaHome)))
}

func main() {}
