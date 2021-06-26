package org.jetbrains.plugins.dx

import com.intellij.lang.Language

class DxShellLanguageInjector : AbstractLanguageInjector() {
    val DX_JS_TAGS = arrayOf("$", "\$o", "\$a")
    var shellLanguage: Language? = null

    init {
        shellLanguage = Language.findLanguageByID("Shell Script");
        if (shellLanguage == null) {
            shellLanguage = Language.findLanguageByID("BashPro Shell Script")
        }
    }

    override fun getTags(): Array<String> {
        return DX_JS_TAGS
    }

    override fun getInjectionLanguage(): Language? {
        return shellLanguage;
    }

}