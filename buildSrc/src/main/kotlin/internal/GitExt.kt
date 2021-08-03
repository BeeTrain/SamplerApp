package internal

import java.util.Date
import org.codehaus.groovy.runtime.ProcessGroovyMethods.execute
import org.codehaus.groovy.runtime.ProcessGroovyMethods.getText

internal fun getGitVersionCode(): Int {
    return ((Date().time / 1000 - 1451606400) / 10).toInt()
}

internal fun getGitVersionName(): String {
    val tag = getText(execute("git describe --tags")).split("-").first()
    if (tag.startsWith("v.") || tag.startsWith("d.")) {
        return tag.substring(1)
    }
    return "1.0.0"
}