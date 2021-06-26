package org.jetbrains.plugins.dx

import com.intellij.lang.Language

class DxRegexpLanguageInjector : AbstractLanguageInjector() {
    val DX_JS_TAGS = arrayOf("grep")
    var regexpLanguage: Language? = null

    init {
        regexpLanguage = Language.findLanguageByID("RegExp")
    }

    override fun getTags(): Array<String> {
        return DX_JS_TAGS
    }

    override fun getInjectionLanguage(): Language? {
        return regexpLanguage
    }
}