package org.jetbrains.plugins.dx

import com.intellij.ide.FileIconProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon


class TaskfileFileIconProvider : FileIconProvider {
    private val icon = IconLoader.getIcon("/dx/icon16.png")
    private val taskFiles = arrayOf("Taskfile.ts", "Taskfile.js")

    override fun getIcon(file: VirtualFile, flags: Int, project: Project?): Icon? {
        val fileName = file.name
        return if (taskFiles.contains(fileName)) {
            icon
        } else null
    }
}