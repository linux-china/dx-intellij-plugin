package org.jetbrains.plugins.dx

import com.intellij.lang.Language

class DxAwkLanguageInjector : AbstractLanguageInjector() {
    val DX_JS_TAGS = arrayOf("awk")
    var awkLanguage: Language? = null

    init {
        awkLanguage = Language.findLanguageByID("AWK")
    }

    override fun getTags(): Array<String> {
        return DX_JS_TAGS
    }

    override fun getInjectionLanguage(): Language? {
        return awkLanguage
    }
}