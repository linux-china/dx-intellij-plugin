package org.jetbrains.plugins.dx

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.lineMarker.RunLineMarkerProvider
import com.intellij.ide.actions.runAnything.activity.RunAnythingCommandProvider
import com.intellij.lang.javascript.psi.JSFunction
import com.intellij.openapi.actionSystem.impl.SimpleDataContext
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiElement
import javax.swing.Icon


class DxRunnerMarkerProvider : RunLineMarkerProvider() {
    override fun getName(): String {
        return "run-dx-task"
    }

    override fun getIcon(): Icon {
        return IconLoader.getIcon("/dx/dino-run.png")
    }

    override fun getLineMarkerInfo(psiElement: PsiElement): LineMarkerInfo<*>? {
        val fileName = psiElement.containingFile.name
        if (fileName == "Taskfile.ts" || fileName == "Taskfile.js") {
            if (psiElement is JSFunction) {
                if (psiElement.isExported) {
                    val functionName = psiElement.name!!
                    return LineMarkerInfo(
                        psiElement,
                        psiElement.textRange,
                        icon,
                        { _: PsiElement? ->
                            "Run dx task"
                        },
                        { e, elt ->
                            runDxTaskByRunAnything(psiElement.project, psiElement, functionName)
                        },
                        GutterIconRenderer.Alignment.CENTER
                    )
                }
            }
        }
        return null
    }

    private fun runDxTaskByRunAnything(project: Project, psiElement: PsiElement, taskName: String) {
        RunAnythingCommandProvider.runCommand(
            psiElement.containingFile.virtualFile.parent,
            "dx $taskName",
            DefaultRunExecutor.getRunExecutorInstance(),
            SimpleDataContext.getProjectContext(project)
        )
    }

}