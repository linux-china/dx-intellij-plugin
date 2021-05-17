package org.jetbrains.plugins.dx

import com.intellij.ide.IconProvider
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import javax.swing.Icon


class TaskfileIconProvider : IconProvider() {
    private val taskFiles = arrayOf("Taskfile.ts", "Taskfile.js")
    private val icon = IconLoader.getIcon("/dx/icon16.png")

    override fun getIcon(psiElement: PsiElement, flags: Int): Icon? {
        val containingFile: PsiFile = psiElement.containingFile
        val fileName = containingFile.name;
        return if (taskFiles.contains(fileName)) {
            icon
        } else null
    }
}