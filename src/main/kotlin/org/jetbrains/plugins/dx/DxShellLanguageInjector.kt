package org.jetbrains.plugins.dx

import com.intellij.lang.Language
import com.intellij.lang.javascript.psi.JSReferenceExpression
import com.intellij.lang.javascript.psi.ecma6.ES6TaggedTemplateExpression
import com.intellij.lang.javascript.psi.ecma6.JSStringTemplateExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.InjectedLanguagePlaces
import com.intellij.psi.LanguageInjector
import com.intellij.psi.PsiLanguageInjectionHost

class DxShellLanguageInjector : LanguageInjector {
    val DX_JS_TAGS = arrayOf("$", "\$o", "\$a")
    var shellLanguage: Language? = null

    init {
        shellLanguage = Language.findLanguageByID("Shell Script");
        if (shellLanguage == null) {
            shellLanguage = Language.findLanguageByID("BashPro Shell Script")
        }
    }

    override fun getLanguagesToInject(host: PsiLanguageInjectionHost, injectionPlacesRegistrar: InjectedLanguagePlaces) {
        if (host is JSStringTemplateExpression && shellLanguage != null) {
            val taggedTemplate = host.parent
            if (taggedTemplate is ES6TaggedTemplateExpression) {
                if (taggedTemplate.tag is JSReferenceExpression) {
                    val tag = taggedTemplate.tag as JSReferenceExpression
                    val tagName = tag.text
                    if (DX_JS_TAGS.contains(tagName)) {
                        injectionPlacesRegistrar.addPlace(shellLanguage!!, TextRange.create(0, host.getTextLength()), "", "");
                    }
                }
            }
        }
    }
}