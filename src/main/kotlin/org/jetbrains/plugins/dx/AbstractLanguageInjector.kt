package org.jetbrains.plugins.dx

import com.intellij.lang.Language
import com.intellij.lang.javascript.psi.JSReferenceExpression
import com.intellij.lang.javascript.psi.ecma6.ES6TaggedTemplateExpression
import com.intellij.lang.javascript.psi.ecma6.JSStringTemplateExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.InjectedLanguagePlaces
import com.intellij.psi.LanguageInjector
import com.intellij.psi.PsiLanguageInjectionHost


abstract class AbstractLanguageInjector : LanguageInjector {
    protected abstract fun getTags(): Array<String>
    protected abstract fun getInjectionLanguage(): Language?

    override fun getLanguagesToInject(host: PsiLanguageInjectionHost, injectionPlacesRegistrar: InjectedLanguagePlaces) {
        if (host is JSStringTemplateExpression && getInjectionLanguage() != null) {
            val taggedTemplate = host.parent
            if (taggedTemplate is ES6TaggedTemplateExpression) {
                if (taggedTemplate.tag is JSReferenceExpression) {
                    val tag = taggedTemplate.tag as JSReferenceExpression
                    val tagName = tag.text
                    if (getTags().contains(tagName)) {
                        injectionPlacesRegistrar.addPlace(getInjectionLanguage()!!, TextRange.create(1, host.getTextLength() - 1), "", "");
                    }
                }
            }
        }
    }
}