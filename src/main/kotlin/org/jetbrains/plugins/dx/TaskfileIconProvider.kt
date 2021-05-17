package org.jetbrains.plugins.dx

import com.intellij.ide.IconProvider
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import javax.swing.Icon


class TaskfileIconProvider : IconProvider() {
    override fun getIcon(psiElement: PsiElement, flags: Int): Icon? {
        val containingFile: PsiFile = psiElement.containingFile
        val fileName = containingFile.name;
        return if (fileName == "Taskfile.ts" || fileName == "Taskfile.js") {
            IconLoader.getIcon("/dx/icon16.png")
        } else null
    }
}